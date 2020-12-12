package ca.ulaval.glo4003.offenses.services;

import ca.ulaval.glo4003.accounts.services.AccountService;
import ca.ulaval.glo4003.funds.domain.BillId;
import ca.ulaval.glo4003.funds.services.BillService;
import ca.ulaval.glo4003.offenses.console.OffenseNotifierSystemPrint;
import ca.ulaval.glo4003.offenses.domain.*;
import ca.ulaval.glo4003.offenses.services.assemblers.OffenseTypeAssembler;
import ca.ulaval.glo4003.offenses.services.converters.OffenseValidationConverter;
import ca.ulaval.glo4003.offenses.services.dto.OffenseTypeDto;
import ca.ulaval.glo4003.offenses.services.dto.OffenseValidationDto;
import ca.ulaval.glo4003.parkings.domain.ParkingAreaRepository;
import ca.ulaval.glo4003.parkings.domain.ParkingSticker;
import ca.ulaval.glo4003.parkings.domain.ParkingStickerCode;
import ca.ulaval.glo4003.parkings.domain.exceptions.NotFoundParkingStickerException;
import java.util.ArrayList;
import java.util.List;

public class OffenseTypeService {
  private final ParkingAreaRepository parkingAreaRepository;
  private final OffenseValidationConverter offenseValidationConverter;
  private final OffenseTypeAssembler offenseTypeAssembler;
  private final OffenseTypeRepository offenseTypeRepository;
  private final OffenseTypeFactory offenseTypeFactory;
  private final BillService billService;
  private final AccountService accountService;
  private final OffenseNotifier offenseNotifier;

  public OffenseTypeService(
      ParkingAreaRepository parkingAreaRepository,
      OffenseTypeRepository offenseTypeRepository,
      OffenseTypeFactory offenseTypeFactory,
      BillService billService,
      AccountService accountService) {
    this(
        parkingAreaRepository,
        new OffenseValidationConverter(),
        new OffenseTypeAssembler(),
        offenseTypeRepository,
        offenseTypeFactory,
        billService,
        accountService,
        new OffenseNotifierSystemPrint());
  }

  public OffenseTypeService(
      ParkingAreaRepository parkingAreaRepository,
      OffenseValidationConverter offenseValidationConverter,
      OffenseTypeAssembler offenseTypeAssembler,
      OffenseTypeRepository offenseTypeRepository,
      OffenseTypeFactory offenseTypeFactory,
      BillService billService,
      AccountService accountService,
      OffenseNotifier offenseNotifier) {
    this.parkingAreaRepository = parkingAreaRepository;
    this.offenseValidationConverter = offenseValidationConverter;
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
    OffenseValidation offenseValidation = offenseValidationConverter.convert(offenseValidationDto);
    parkingAreaRepository.get(offenseValidation.getParkingAreaCode());
    List<OffenseType> offenseTypes = new ArrayList<>();
    ParkingSticker parkingSticker = null;

    if (offenseValidation.getParkingStickerCode() == null) {
      OffenseType absentStickerOffense = offenseTypeFactory.createAbsentStickerOffense();
      offenseTypes.add(absentStickerOffense);
      offenseNotifier.notifyOffenseWithoutParkingSticker(absentStickerOffense);
    } else {
      try {
        ParkingStickerCode parkingStickerCode = offenseValidation.getParkingStickerCode();
        parkingSticker = accountService.getParkingSticker(parkingStickerCode);

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
