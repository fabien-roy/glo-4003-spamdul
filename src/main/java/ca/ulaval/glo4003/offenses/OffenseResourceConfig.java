package ca.ulaval.glo4003.offenses;

import ca.ulaval.glo4003.domain.time.TimeOfDayAssembler;
import ca.ulaval.glo4003.offenses.api.OffenseResource;
import ca.ulaval.glo4003.offenses.api.OffenseResourceImplementation;
import ca.ulaval.glo4003.offenses.assemblers.OffenseAssembler;
import ca.ulaval.glo4003.offenses.assemblers.OffenseValidationAssembler;
import ca.ulaval.glo4003.offenses.domain.Offense;
import ca.ulaval.glo4003.offenses.filesystem.OffenseFileHelper;
import ca.ulaval.glo4003.offenses.infrastructure.OffenseRepositoryInMemory;
import ca.ulaval.glo4003.offenses.services.OffenseService;
import ca.ulaval.glo4003.parkings.assemblers.ParkingAreaCodeAssembler;
import ca.ulaval.glo4003.parkings.assemblers.ParkingStickerCodeAssembler;
import ca.ulaval.glo4003.parkings.domain.ParkingStickerRepository;
import java.util.List;

public class OffenseResourceConfig {
  private OffenseRepositoryInMemory offenseRepositoryInMemory;
  private final OffenseAssembler offenseAssembler;
  private final ParkingStickerCodeAssembler parkingStickerCodeAssembler;
  private final ParkingAreaCodeAssembler parkingAreaCodeAssembler;
  private final TimeOfDayAssembler timeOfDayAssembler;

  public OffenseResourceConfig() {
    offenseRepositoryInMemory = new OffenseRepositoryInMemory();
    offenseAssembler = new OffenseAssembler();
    parkingStickerCodeAssembler = new ParkingStickerCodeAssembler();
    parkingAreaCodeAssembler = new ParkingAreaCodeAssembler();
    timeOfDayAssembler = new TimeOfDayAssembler();
  }

  public OffenseResource createOffenseResource(ParkingStickerRepository parkingStickerRepository) {

    OffenseValidationAssembler offenseValidationAssembler =
        new OffenseValidationAssembler(
            parkingStickerCodeAssembler, parkingAreaCodeAssembler, timeOfDayAssembler);

    OffenseService offenseService =
        new OffenseService(
            parkingStickerRepository,
            offenseValidationAssembler,
            offenseAssembler,
            offenseRepositoryInMemory);

    offenseRepositoryInMemory = new OffenseRepositoryInMemory();
    OffenseFileHelper offenseFileHelper = new OffenseFileHelper();
    List<Offense> offenses = offenseFileHelper.getAllOffenses();

    for (Offense offense : offenses) {
      offenseRepositoryInMemory.save(offense);
    }

    return new OffenseResourceImplementation(offenseService);
  }
}
