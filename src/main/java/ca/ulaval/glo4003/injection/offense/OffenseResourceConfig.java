package ca.ulaval.glo4003.injection.offense;

import ca.ulaval.glo4003.api.offense.OffenseResource;
import ca.ulaval.glo4003.api.offense.OffenseResourceImplementation;
import ca.ulaval.glo4003.domain.offense.OffenseAssembler;
import ca.ulaval.glo4003.domain.offense.OffenseService;
import ca.ulaval.glo4003.domain.offense.OffenseValidationAssembler;
import ca.ulaval.glo4003.domain.parking.ParkingAreaCodeAssembler;
import ca.ulaval.glo4003.domain.parking.ParkingStickerCodeAssembler;
import ca.ulaval.glo4003.domain.parking.ParkingStickerRepository;

public class OffenseResourceConfig {
  private final OffenseAssembler offenseAssembler;
  private final ParkingStickerCodeAssembler parkingStickerCodeAssembler;
  private final ParkingAreaCodeAssembler parkingAreaCodeAssembler;

  public OffenseResourceConfig() {
    offenseAssembler = new OffenseAssembler();
    parkingStickerCodeAssembler = new ParkingStickerCodeAssembler();
    parkingAreaCodeAssembler = new ParkingAreaCodeAssembler();
  }

  public OffenseResource createOffenseResource(ParkingStickerRepository parkingStickerRepository) {

    OffenseValidationAssembler offenseValidationAssembler =
        new OffenseValidationAssembler(parkingStickerCodeAssembler, parkingAreaCodeAssembler);

    OffenseService offenseService =
        new OffenseService(parkingStickerRepository, offenseValidationAssembler, offenseAssembler);

    return new OffenseResourceImplementation(offenseService);
  }
}
