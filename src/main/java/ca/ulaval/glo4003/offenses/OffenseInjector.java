package ca.ulaval.glo4003.offenses;

import ca.ulaval.glo4003.accounts.services.AccountService;
import ca.ulaval.glo4003.files.domain.StringFileReader;
import ca.ulaval.glo4003.files.filesystem.JsonFileReader;
import ca.ulaval.glo4003.funds.assemblers.MoneyAssembler;
import ca.ulaval.glo4003.funds.services.BillService;
import ca.ulaval.glo4003.offenses.api.OffenseResource;
import ca.ulaval.glo4003.offenses.assemblers.OffenseCodeAssembler;
import ca.ulaval.glo4003.offenses.assemblers.OffenseTypeAssembler;
import ca.ulaval.glo4003.offenses.assemblers.OffenseTypeInFrenchAssembler;
import ca.ulaval.glo4003.offenses.assemblers.OffenseValidationAssembler;
import ca.ulaval.glo4003.offenses.console.OffenseNotifierSystemPrint;
import ca.ulaval.glo4003.offenses.domain.OffenseNotifier;
import ca.ulaval.glo4003.offenses.domain.OffenseType;
import ca.ulaval.glo4003.offenses.domain.OffenseTypeFactory;
import ca.ulaval.glo4003.offenses.domain.OffenseTypeRepository;
import ca.ulaval.glo4003.offenses.filesystem.OffenseFileHelper;
import ca.ulaval.glo4003.offenses.filesystem.dto.OffenseDtoInFrench;
import ca.ulaval.glo4003.offenses.infrastructure.OffenseTypeRepositoryInMemory;
import ca.ulaval.glo4003.offenses.services.OffenseTypeService;
import ca.ulaval.glo4003.parkings.assemblers.ParkingAreaCodeAssembler;
import ca.ulaval.glo4003.parkings.assemblers.ParkingStickerCodeAssembler;
import ca.ulaval.glo4003.parkings.domain.ParkingAreaRepository;
import ca.ulaval.glo4003.parkings.domain.ParkingStickerRepository;
import ca.ulaval.glo4003.times.assemblers.TimeOfDayAssembler;
import java.util.List;

public class OffenseInjector {
  private final OffenseNotifier offenseNotifier = new OffenseNotifierSystemPrint();
  private final OffenseTypeRepository offenseTypeRepository = new OffenseTypeRepositoryInMemory();
  private final StringFileReader fileReader = new JsonFileReader();

  public OffenseResource createOffenseResource(
      ParkingAreaRepository parkingAreaRepository,
      ParkingStickerRepository parkingStickerRepository,
      ParkingStickerCodeAssembler parkingStickerCodeAssembler,
      ParkingAreaCodeAssembler parkingAreaCodeAssembler,
      TimeOfDayAssembler timeOfDayAssembler,
      MoneyAssembler moneyAssembler,
      BillService billService,
      AccountService accountService) {
    addOffenseTypesToRepository(moneyAssembler);

    OffenseTypeService offenseTypeService =
        createOffenseService(
            parkingAreaRepository,
            parkingStickerRepository,
            parkingStickerCodeAssembler,
            parkingAreaCodeAssembler,
            timeOfDayAssembler,
            billService,
            accountService);

    return new OffenseResource(offenseTypeService);
  }

  private void addOffenseTypesToRepository(MoneyAssembler moneyAssembler) {
    OffenseFileHelper offenseFileHelper = new OffenseFileHelper(fileReader);
    List<OffenseDtoInFrench> offensesInFrenchDto = offenseFileHelper.getOffenseInFrench();

    OffenseCodeAssembler offenseCodeAssembler = new OffenseCodeAssembler();
    OffenseTypeInFrenchAssembler offenseTypeInFrenchAssembler =
        new OffenseTypeInFrenchAssembler(offenseCodeAssembler, moneyAssembler);
    List<OffenseType> offenseTypes = offenseTypeInFrenchAssembler.assembleMany(offensesInFrenchDto);

    for (OffenseType offenseType : offenseTypes) {
      offenseTypeRepository.save(offenseType);
    }
  }

  private OffenseTypeService createOffenseService(
      ParkingAreaRepository parkingAreaRepository,
      ParkingStickerRepository parkingStickerRepository,
      ParkingStickerCodeAssembler parkingStickerCodeAssembler,
      ParkingAreaCodeAssembler parkingAreaCodeAssembler,
      TimeOfDayAssembler timeOfDayAssembler,
      BillService billService,
      AccountService accountService) {
    OffenseValidationAssembler offenseValidationAssembler =
        createOffenseValidationAssembler(
            parkingStickerCodeAssembler, parkingAreaCodeAssembler, timeOfDayAssembler);
    OffenseTypeAssembler offenseTypeAssembler = new OffenseTypeAssembler();
    OffenseTypeFactory offenseTypeFactory =
        new OffenseTypeFactory(offenseTypeRepository, createOffenseCodeAssembler());

    return new OffenseTypeService(
        parkingAreaRepository,
        parkingStickerRepository,
        offenseValidationAssembler,
        offenseTypeAssembler,
        offenseTypeRepository,
        offenseTypeFactory,
        billService,
        accountService,
        offenseNotifier);
  }

  private OffenseValidationAssembler createOffenseValidationAssembler(
      ParkingStickerCodeAssembler parkingStickerCodeAssembler,
      ParkingAreaCodeAssembler parkingAreaCodeAssembler,
      TimeOfDayAssembler timeOfDayAssembler) {
    return new OffenseValidationAssembler(
        parkingStickerCodeAssembler, parkingAreaCodeAssembler, timeOfDayAssembler);
  }

  private OffenseCodeAssembler createOffenseCodeAssembler() {
    return new OffenseCodeAssembler();
  }
}
