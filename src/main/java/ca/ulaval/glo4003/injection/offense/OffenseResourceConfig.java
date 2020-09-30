package ca.ulaval.glo4003.injection.offense;

import ca.ulaval.glo4003.api.offense.OffenseResource;
import ca.ulaval.glo4003.api.offense.OffenseResourceImplementation;
import ca.ulaval.glo4003.domain.offense.OffenseAssembler;
import ca.ulaval.glo4003.domain.offense.OffenseService;
import ca.ulaval.glo4003.domain.offense.OffenseValidationAssembler;
import ca.ulaval.glo4003.domain.parking.ParkingAreaCodeAssembler;
import ca.ulaval.glo4003.domain.parking.ParkingStickerCodeAssembler;
import ca.ulaval.glo4003.domain.parking.ParkingStickerRepository;
import ca.ulaval.glo4003.domain.time.TimeOfDayAssembler;

public class OffenseResourceConfig {
  private final OffenseAssembler offenseAssembler;
  private final ParkingStickerCodeAssembler parkingStickerCodeAssembler;
  private final ParkingAreaCodeAssembler parkingAreaCodeAssembler;
  private final TimeOfDayAssembler timeOfDayAssembler;

  public OffenseResourceConfig() {
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
        new OffenseService(parkingStickerRepository, offenseValidationAssembler, offenseAssembler);

    return new OffenseResourceImplementation(offenseService);
  }
}
