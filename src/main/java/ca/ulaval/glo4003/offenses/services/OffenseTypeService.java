package ca.ulaval.glo4003.offenses.services;

import ca.ulaval.glo4003.accounts.services.AccountService;
import ca.ulaval.glo4003.funds.domain.BillId;
import ca.ulaval.glo4003.funds.services.BillService;
import ca.ulaval.glo4003.offenses.api.dto.OffenseTypeDto;
import ca.ulaval.glo4003.offenses.api.dto.OffenseValidationDto;
import ca.ulaval.glo4003.offenses.assemblers.OffenseTypeAssembler;
import ca.ulaval.glo4003.offenses.assemblers.OffenseValidationAssembler;
import ca.ulaval.glo4003.offenses.domain.OffenseType;
import ca.ulaval.glo4003.offenses.domain.OffenseTypeFactory;
import ca.ulaval.glo4003.offenses.domain.OffenseTypeRepository;
import ca.ulaval.glo4003.offenses.domain.OffenseValidation;
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

  public OffenseTypeService(
      ParkingAreaRepository parkingAreaRepository,
      ParkingStickerRepository parkingStickerRepository,
      OffenseValidationAssembler offenseValidationAssembler,
      OffenseTypeAssembler offenseTypeAssembler,
      OffenseTypeRepository offenseTypeRepository,
      OffenseTypeFactory offenseTypeFactory,
      BillService billService,
      AccountService accountService) {
    this.parkingAreaRepository = parkingAreaRepository;
    this.parkingStickerRepository = parkingStickerRepository;
    this.offenseValidationAssembler = offenseValidationAssembler;
    this.offenseTypeAssembler = offenseTypeAssembler;
    this.offenseTypeRepository = offenseTypeRepository;
    this.offenseTypeFactory = offenseTypeFactory;
    this.billService = billService;
    this.accountService = accountService;
  }

  public List<OffenseTypeDto> getAllOffenseTypes() {
    return offenseTypeAssembler.assembleMany(offenseTypeRepository.getAll());
  }

  public List<OffenseTypeDto> validateOffense(OffenseValidationDto offenseValidationDto) {
    OffenseValidation offenseValidation = offenseValidationAssembler.assemble(offenseValidationDto);

    List<OffenseType> offenseTypes = new ArrayList<>();

    parkingAreaRepository.get(offenseValidation.getParkingAreaCode());

    ParkingSticker parkingSticker = null;
    try {
      parkingSticker = parkingStickerRepository.get(offenseValidation.getParkingStickerCode());
    } catch (NotFoundParkingStickerException exception) {
      offenseTypes.add(offenseTypeFactory.createInvalidStickerOffense());
      // TODO : We cannot add a bill. Do we print to console?
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
