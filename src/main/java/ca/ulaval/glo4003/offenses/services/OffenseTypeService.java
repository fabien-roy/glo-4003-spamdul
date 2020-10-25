package ca.ulaval.glo4003.offenses.services;

import ca.ulaval.glo4003.accounts.services.AccountService;
import ca.ulaval.glo4003.funds.domain.BillId;
import ca.ulaval.glo4003.funds.services.BillService;
import ca.ulaval.glo4003.offenses.api.dto.OffenseTypeDto;
import ca.ulaval.glo4003.offenses.api.dto.OffenseValidationDto;
import ca.ulaval.glo4003.offenses.assemblers.OffenseTypeAssembler;
import ca.ulaval.glo4003.offenses.assemblers.OffenseValidationAssembler;
import ca.ulaval.glo4003.offenses.domain.*;
import ca.ulaval.glo4003.parkings.domain.ParkingAreaRepository;
import ca.ulaval.glo4003.parkings.domain.ParkingSticker;
import ca.ulaval.glo4003.parkings.domain.ParkingStickerRepository;
import ca.ulaval.glo4003.parkings.exceptions.NotFoundParkingStickerException;
import java.util.ArrayList;
import java.util.List;

public class OffenseTypeService {
  private final ParkingAreaRepository parkingAreaRepository;
  private final ParkingStickerRepository parkingStickerRepository;
  private final OffenseValidationAssembler offenseValidationAssembler;
  private final OffenseTypeAssembler offenseTypeAssembler;
  private final OffenseTypeRepository offenseTypeRepository;
  private final OffenseTypeFactory offenseTypeFactory;
  private final BillService billService;
  private final AccountService accountService;
  private final OffenseNotifier offenseNotifier;

  public OffenseTypeService(
      ParkingAreaRepository parkingAreaRepository,
      ParkingStickerRepository parkingStickerRepository,
      OffenseValidationAssembler offenseValidationAssembler,
      OffenseTypeAssembler offenseTypeAssembler,
      OffenseTypeRepository offenseTypeRepository,
      OffenseTypeFactory offenseTypeFactory,
      BillService billService,
      AccountService accountService,
      OffenseNotifier offenseNotifier) {
    this.parkingAreaRepository = parkingAreaRepository;
    this.parkingStickerRepository = parkingStickerRepository;
    this.offenseValidationAssembler = offenseValidationAssembler;
    this.offenseTypeAssembler = offenseTypeAssembler;
    this.offenseTypeRepository = offenseTypeRepository;
    this.offenseTypeFactory = offenseTypeFactory;
    this.billService = billService;
    this.accountService = accountService;
    this.offenseNotifier = offenseNotifier;
  }

  public List<OffenseTypeDto> getAllOffenseTypes() {
    return offenseTypeAssembler.assembleMany(offenseTypeRepository.getAll());
  }

  public List<OffenseTypeDto> validateOffense(OffenseValidationDto offenseValidationDto) {
    OffenseValidation offenseValidation = offenseValidationAssembler.assemble(offenseValidationDto);
    parkingAreaRepository.get(offenseValidation.getParkingAreaCode());
    List<OffenseType> offenseTypes = new ArrayList<>();
    ParkingSticker parkingSticker = null;

    if (offenseValidation.getParkingStickerCode() == null) {
      OffenseType absentStickerOffense = offenseTypeFactory.createAbsentStickerOffense();
      offenseTypes.add(absentStickerOffense);
      offenseNotifier.notifyOffenseWithoutParkingSticker(absentStickerOffense);
    } else {
      try {
        parkingSticker = parkingStickerRepository.get(offenseValidation.getParkingStickerCode());
      } catch (NotFoundParkingStickerException exception) {
        OffenseType invalidStickerOffense = offenseTypeFactory.createInvalidStickerOffense();
        offenseTypes.add(invalidStickerOffense);
        offenseNotifier.notifyOffenseWithoutParkingSticker(invalidStickerOffense);
      }
    }

    if (parkingSticker != null
        && !parkingSticker.validateParkingStickerAreaCode(offenseValidation.getParkingAreaCode())) {
      OffenseType offense = offenseTypeFactory.createWrongZoneOffense();
      offenseTypes.add(offense);

      BillId billId = billService.addBillOffense(offense.getAmount(), offense.getCode());
      accountService.addOffenseToAccount(parkingSticker.getAccountId(), billId);
    }

    return offenseTypeAssembler.assembleMany(offenseTypes);
  }
}
