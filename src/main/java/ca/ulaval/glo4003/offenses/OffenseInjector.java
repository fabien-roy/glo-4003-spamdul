package ca.ulaval.glo4003.offenses;

import ca.ulaval.glo4003.files.filesystem.JsonHelper;
import ca.ulaval.glo4003.offenses.api.OffenseResource;
import ca.ulaval.glo4003.offenses.api.OffenseResourceImplementation;
import ca.ulaval.glo4003.offenses.api.OffenseTypeResource;
import ca.ulaval.glo4003.offenses.api.OffenseTypeResourceImplementation;
import ca.ulaval.glo4003.offenses.assemblers.InfractionAssembler;
import ca.ulaval.glo4003.offenses.assemblers.OffenseTypeAssembler;
import ca.ulaval.glo4003.offenses.assemblers.OffenseValidationAssembler;
import ca.ulaval.glo4003.offenses.domain.Offense;
import ca.ulaval.glo4003.offenses.domain.OffenseTypeRepository;
import ca.ulaval.glo4003.offenses.filesystem.InfractionFileHelper;
import ca.ulaval.glo4003.offenses.filesystem.dto.InfractionDto;
import ca.ulaval.glo4003.offenses.infrastructure.OffenseTypeRepositoryInMemory;
import ca.ulaval.glo4003.offenses.services.OffenseTypeService;
import ca.ulaval.glo4003.parkings.assemblers.ParkingAreaCodeAssembler;
import ca.ulaval.glo4003.parkings.assemblers.ParkingStickerCodeAssembler;
import ca.ulaval.glo4003.parkings.domain.ParkingStickerRepository;
import ca.ulaval.glo4003.times.assemblers.TimeOfDayAssembler;
import java.util.List;

public class OffenseInjector {
  private final OffenseTypeRepository offenseTypeRepository;

  public OffenseInjector() {
    offenseTypeRepository = new OffenseTypeRepositoryInMemory();
  }

  public OffenseResource createOffenseResource(
      ParkingStickerRepository parkingStickerRepository,
      ParkingStickerCodeAssembler parkingStickerCodeAssembler,
      ParkingAreaCodeAssembler parkingAreaCodeAssembler,
      TimeOfDayAssembler timeOfDayAssembler) {
    OffenseTypeService offenseTypeService =
        createOffenseService(
            parkingStickerRepository,
            parkingStickerCodeAssembler,
            parkingAreaCodeAssembler,
            timeOfDayAssembler);

    return new OffenseResourceImplementation(offenseTypeService);
  }

  public OffenseTypeResource createOffenseTypeResource(
      ParkingStickerRepository parkingStickerRepository,
      ParkingStickerCodeAssembler parkingStickerCodeAssembler,
      ParkingAreaCodeAssembler parkingAreaCodeAssembler,
      TimeOfDayAssembler timeOfDayAssembler,
      JsonHelper jsonHelper) {
    addOffenseTypesToRepository(jsonHelper);

    OffenseTypeService offenseTypeService =
        createOffenseService(
            parkingStickerRepository,
            parkingStickerCodeAssembler,
            parkingAreaCodeAssembler,
            timeOfDayAssembler);

    return new OffenseTypeResourceImplementation(offenseTypeService);
  }

  private void addOffenseTypesToRepository(JsonHelper jsonHelper) {
    InfractionFileHelper infractionFileHelper = new InfractionFileHelper(jsonHelper);
    List<InfractionDto> infractions = infractionFileHelper.readInfractions();

    InfractionAssembler infractionAssembler = new InfractionAssembler();
    List<Offense> offenses = infractionAssembler.assembleMany(infractions);

    for (Offense offense : offenses) {
      offenseTypeRepository.save(offense);
    }
  }

  private OffenseTypeService createOffenseService(
      ParkingStickerRepository parkingStickerRepository,
      ParkingStickerCodeAssembler parkingStickerCodeAssembler,
      ParkingAreaCodeAssembler parkingAreaCodeAssembler,
      TimeOfDayAssembler timeOfDayAssembler) {
    OffenseValidationAssembler offenseValidationAssembler =
        createOffenseValidationAssembler(
            parkingStickerCodeAssembler, parkingAreaCodeAssembler, timeOfDayAssembler);
    OffenseTypeAssembler offenseTypeAssembler = new OffenseTypeAssembler();

    return new OffenseTypeService(
        parkingStickerRepository,
        offenseValidationAssembler,
        offenseTypeAssembler,
        offenseTypeRepository);
  }

  private OffenseValidationAssembler createOffenseValidationAssembler(
      ParkingStickerCodeAssembler parkingStickerCodeAssembler,
      ParkingAreaCodeAssembler parkingAreaCodeAssembler,
      TimeOfDayAssembler timeOfDayAssembler) {
    return new OffenseValidationAssembler(
        parkingStickerCodeAssembler, parkingAreaCodeAssembler, timeOfDayAssembler);
  }
}
