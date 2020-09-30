package ca.ulaval.glo4003.injection.offense;

import ca.ulaval.glo4003.api.offense.OffenseResource;
import ca.ulaval.glo4003.api.offense.OffenseResourceImplementation;
import ca.ulaval.glo4003.domain.offense.Offense;
import ca.ulaval.glo4003.domain.offense.OffenseHelper;
import ca.ulaval.glo4003.domain.offense.OffenseService;
import ca.ulaval.glo4003.domain.offense.OffenseValidationAssembler;
import ca.ulaval.glo4003.domain.parking.ParkingAreaCodeAssembler;
import ca.ulaval.glo4003.domain.parking.ParkingStickerCodeAssembler;
import ca.ulaval.glo4003.domain.parking.ParkingStickerRepository;
import ca.ulaval.glo4003.infrastructure.offense.OffenseRepositoryInMemory;
import java.util.List;

public class OffenseResourceConfig {

  private OffenseRepositoryInMemory offenseRepositoryInMemory;

  public OffenseResource createOffenseResource(
      ParkingStickerRepository parkingStickerRepository,
      ParkingStickerCodeAssembler parkingStickerCodeAssembler,
      ParkingAreaCodeAssembler parkingAreaCodeAssembler) {
    OffenseValidationAssembler offenseValidationAssembler =
        new OffenseValidationAssembler(parkingStickerCodeAssembler, parkingAreaCodeAssembler);

    offenseRepositoryInMemory = new OffenseRepositoryInMemory();
    OffenseHelper offenseHelper = new OffenseHelper();
    List<Offense> offenses = offenseHelper.getAllOffenses();

    for (Offense offense : offenses) {
      offenseRepositoryInMemory.save(offense);
    }

    return new OffenseResourceImplementation(
        new OffenseService(
            parkingStickerRepository, offenseValidationAssembler, offenseRepositoryInMemory));
  }
}
