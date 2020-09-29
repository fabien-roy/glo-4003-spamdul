package ca.ulaval.glo4003.injection.offense;

import ca.ulaval.glo4003.api.offense.OffenseResource;
import ca.ulaval.glo4003.api.offense.OffenseResourceImplementation;
import ca.ulaval.glo4003.domain.offense.OffenseService;
import ca.ulaval.glo4003.domain.offense.OffenseValidationAssembler;
import ca.ulaval.glo4003.domain.parking.ParkingAreaCodeAssembler;
import ca.ulaval.glo4003.domain.parking.ParkingStickerCodeAssembler;
import ca.ulaval.glo4003.domain.parking.ParkingStickerRepository;

public class OffenseResourceConfig {
  public OffenseResource createOffenseResource(
      ParkingStickerRepository parkingStickerRepository,
      ParkingStickerCodeAssembler parkingStickerCodeAssembler,
      ParkingAreaCodeAssembler parkingAreaCodeAssembler) {
    OffenseValidationAssembler offenseValidationAssembler =
        new OffenseValidationAssembler(parkingStickerCodeAssembler, parkingAreaCodeAssembler);
    return new OffenseResourceImplementation(
        new OffenseService(parkingStickerRepository, offenseValidationAssembler));
  }
}
