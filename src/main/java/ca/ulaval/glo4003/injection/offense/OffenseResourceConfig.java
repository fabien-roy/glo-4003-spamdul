package ca.ulaval.glo4003.injection.offense;

import ca.ulaval.glo4003.api.offense.OffenseResource;
import ca.ulaval.glo4003.api.offense.OffenseResourceImplementation;
import ca.ulaval.glo4003.domain.offense.*;
import ca.ulaval.glo4003.domain.parking.ParkingAreaCodeAssembler;
import ca.ulaval.glo4003.domain.parking.ParkingStickerCodeAssembler;
import ca.ulaval.glo4003.domain.parking.ParkingStickerRepository;
import ca.ulaval.glo4003.infrastructure.offense.OffenseRepositoryInMemory;
import java.util.List;

public class OffenseResourceConfig {
  private OffenseRepositoryInMemory offenseRepositoryInMemory;
  private final OffenseAssembler offenseAssembler;
  private final ParkingStickerCodeAssembler parkingStickerCodeAssembler;
  private final ParkingAreaCodeAssembler parkingAreaCodeAssembler;

  public OffenseResourceConfig() {
    offenseRepositoryInMemory = new OffenseRepositoryInMemory();
    offenseAssembler = new OffenseAssembler();
    parkingStickerCodeAssembler = new ParkingStickerCodeAssembler();
    parkingAreaCodeAssembler = new ParkingAreaCodeAssembler();
  }

  public OffenseResource createOffenseResource(ParkingStickerRepository parkingStickerRepository) {

    OffenseValidationAssembler offenseValidationAssembler =
        new OffenseValidationAssembler(parkingStickerCodeAssembler, parkingAreaCodeAssembler);

    OffenseService offenseService =
        new OffenseService(
            parkingStickerRepository,
            offenseValidationAssembler,
            offenseAssembler,
            offenseRepositoryInMemory);

    offenseRepositoryInMemory = new OffenseRepositoryInMemory();
    OffenseHelper offenseHelper = new OffenseHelper();
    List<Offense> offenses = offenseHelper.getAllOffenses();

    for (Offense offense : offenses) {
      offenseRepositoryInMemory.save(offense);
    }

    return new OffenseResourceImplementation(offenseService);
  }
}
