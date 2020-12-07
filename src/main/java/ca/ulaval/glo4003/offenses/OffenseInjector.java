package ca.ulaval.glo4003.offenses;

import ca.ulaval.glo4003.accounts.services.AccountService;
import ca.ulaval.glo4003.files.domain.StringFileReader;
import ca.ulaval.glo4003.files.filesystem.JsonFileReader;
import ca.ulaval.glo4003.funds.services.BillService;
import ca.ulaval.glo4003.funds.services.converters.MoneyConverter;
import ca.ulaval.glo4003.offenses.api.OffenseResource;
import ca.ulaval.glo4003.offenses.domain.OffenseType;
import ca.ulaval.glo4003.offenses.domain.OffenseTypeFactory;
import ca.ulaval.glo4003.offenses.domain.OffenseTypeRepository;
import ca.ulaval.glo4003.offenses.filesystem.OffenseFileHelper;
import ca.ulaval.glo4003.offenses.infrastructure.OffenseTypeRepositoryInMemory;
import ca.ulaval.glo4003.offenses.services.OffenseTypeService;
import ca.ulaval.glo4003.offenses.services.assemblers.OffenseCodeAssembler;
import ca.ulaval.glo4003.offenses.services.converters.OffenseTypeInFrenchConverter;
import ca.ulaval.glo4003.offenses.services.dto.OffenseDtoInFrench;
import ca.ulaval.glo4003.parkings.domain.ParkingAreaRepository;
import ca.ulaval.glo4003.parkings.domain.ParkingStickerRepository;
import java.util.List;

public class OffenseInjector {
  private final OffenseTypeRepository offenseTypeRepository = new OffenseTypeRepositoryInMemory();
  private final StringFileReader fileReader = new JsonFileReader();

  public OffenseResource createOffenseResource(
      ParkingAreaRepository parkingAreaRepository,
      ParkingStickerRepository parkingStickerRepository,
      MoneyConverter moneyConverter,
      BillService billService,
      AccountService accountService) {
    addOffenseTypesToRepository(moneyConverter);

    OffenseTypeService offenseTypeService =
        createOffenseService(
            parkingAreaRepository, parkingStickerRepository, billService, accountService);

    return new OffenseResource(offenseTypeService);
  }

  private void addOffenseTypesToRepository(MoneyConverter moneyConverter) {
    OffenseFileHelper offenseFileHelper = new OffenseFileHelper(fileReader);
    List<OffenseDtoInFrench> offensesInFrenchDto = offenseFileHelper.getOffenseInFrench();

    OffenseCodeAssembler offenseCodeAssembler = new OffenseCodeAssembler();
    OffenseTypeInFrenchConverter offenseTypeInFrenchConverter =
        new OffenseTypeInFrenchConverter(offenseCodeAssembler, moneyConverter);
    List<OffenseType> offenseTypes = offenseTypeInFrenchConverter.assembleMany(offensesInFrenchDto);

    for (OffenseType offenseType : offenseTypes) {
      offenseTypeRepository.save(offenseType);
    }
  }

  private OffenseTypeService createOffenseService(
      ParkingAreaRepository parkingAreaRepository,
      ParkingStickerRepository parkingStickerRepository,
      BillService billService,
      AccountService accountService) {
    OffenseTypeFactory offenseTypeFactory =
        new OffenseTypeFactory(offenseTypeRepository, new OffenseCodeAssembler());

    return new OffenseTypeService(
        parkingAreaRepository,
        parkingStickerRepository,
        offenseTypeRepository,
        offenseTypeFactory,
        billService,
        accountService);
  }
}
