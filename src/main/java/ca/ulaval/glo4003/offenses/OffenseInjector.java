package ca.ulaval.glo4003.offenses;

import ca.ulaval.glo4003.offenses.api.OffenseResource;
import ca.ulaval.glo4003.offenses.api.OffenseResourceImplementation;
import ca.ulaval.glo4003.offenses.api.OffenseTypeResource;
import ca.ulaval.glo4003.offenses.api.OffenseTypeResourceImplementation;
import ca.ulaval.glo4003.offenses.assemblers.OffenseAssembler;
import ca.ulaval.glo4003.offenses.assemblers.OffenseValidationAssembler;
import ca.ulaval.glo4003.offenses.domain.Offense;
import ca.ulaval.glo4003.offenses.domain.OffenseRepository;
import ca.ulaval.glo4003.offenses.filesystem.OffenseFileHelper;
import ca.ulaval.glo4003.offenses.infrastructure.OffenseRepositoryInMemory;
import ca.ulaval.glo4003.offenses.services.OffenseService;
import ca.ulaval.glo4003.parkings.assemblers.ParkingAreaCodeAssembler;
import ca.ulaval.glo4003.parkings.assemblers.ParkingStickerCodeAssembler;
import ca.ulaval.glo4003.parkings.domain.ParkingStickerRepository;
import ca.ulaval.glo4003.times.assemblers.TimeOfDayAssembler;
import java.util.List;

public class OffenseInjector {
  private final OffenseRepository offenseRepository;

  public OffenseInjector() {
    offenseRepository = new OffenseRepositoryInMemory();
  }

  public OffenseResource createOffenseResource(
      ParkingStickerRepository parkingStickerRepository,
      ParkingStickerCodeAssembler parkingStickerCodeAssembler,
      ParkingAreaCodeAssembler parkingAreaCodeAssembler,
      TimeOfDayAssembler timeOfDayAssembler) {
    OffenseService offenseService =
        createOffenseService(
            parkingStickerRepository,
            parkingStickerCodeAssembler,
            parkingAreaCodeAssembler,
            timeOfDayAssembler);

    return new OffenseResourceImplementation(offenseService);
  }

  public OffenseTypeResource createOffenseTypeResource(
      ParkingStickerRepository parkingStickerRepository,
      ParkingStickerCodeAssembler parkingStickerCodeAssembler,
      ParkingAreaCodeAssembler parkingAreaCodeAssembler,
      TimeOfDayAssembler timeOfDayAssembler) {
    OffenseFileHelper offenseFileHelper = new OffenseFileHelper();
    List<Offense> offenses = offenseFileHelper.getAllOffenses();

    for (Offense offense : offenses) {
      offenseRepository.save(offense);
    }

    OffenseService offenseService =
        createOffenseService(
            parkingStickerRepository,
            parkingStickerCodeAssembler,
            parkingAreaCodeAssembler,
            timeOfDayAssembler);

    return new OffenseTypeResourceImplementation(offenseService);
  }

  private OffenseService createOffenseService(
      ParkingStickerRepository parkingStickerRepository,
      ParkingStickerCodeAssembler parkingStickerCodeAssembler,
      ParkingAreaCodeAssembler parkingAreaCodeAssembler,
      TimeOfDayAssembler timeOfDayAssembler) {
    OffenseValidationAssembler offenseValidationAssembler =
        createOffenseValidationAssembler(
            parkingStickerCodeAssembler, parkingAreaCodeAssembler, timeOfDayAssembler);
    OffenseAssembler offenseAssembler = new OffenseAssembler();

    return new OffenseService(
        parkingStickerRepository, offenseValidationAssembler, offenseAssembler, offenseRepository);
  }

  private OffenseValidationAssembler createOffenseValidationAssembler(
      ParkingStickerCodeAssembler parkingStickerCodeAssembler,
      ParkingAreaCodeAssembler parkingAreaCodeAssembler,
      TimeOfDayAssembler timeOfDayAssembler) {
    return new OffenseValidationAssembler(
        parkingStickerCodeAssembler, parkingAreaCodeAssembler, timeOfDayAssembler);
  }
}
