package ca.ulaval.glo4003.domain.offense;

import ca.ulaval.glo4003.api.offense.dto.OffenseValidationDto;
import ca.ulaval.glo4003.domain.parking.ParkingAreaCode;
import ca.ulaval.glo4003.domain.parking.ParkingAreaCodeAssembler;
import ca.ulaval.glo4003.domain.parking.ParkingStickerCode;
import ca.ulaval.glo4003.domain.parking.ParkingStickerCodeAssembler;
import ca.ulaval.glo4003.domain.time.TimeOfDay;

public class OffenseValidationAssembler {
  private final ParkingStickerCodeAssembler parkingStickerCodeAssembler;
  private final ParkingAreaCodeAssembler parkingAreaCodeAssembler;

  public OffenseValidationAssembler(
      ParkingStickerCodeAssembler parkingStickerCodeAssembler,
      ParkingAreaCodeAssembler parkingAreaCodeAssembler) {
    this.parkingStickerCodeAssembler = parkingStickerCodeAssembler;
    this.parkingAreaCodeAssembler = parkingAreaCodeAssembler;
  }

  public OffenseValidation assemble(OffenseValidationDto offenseValidationDto) {
    ParkingStickerCode parkingStickerCode =
        parkingStickerCodeAssembler.assemble(offenseValidationDto.parkingStickerCode);
    ParkingAreaCode parkingAreaCode =
        parkingAreaCodeAssembler.assemble(offenseValidationDto.parkingArea);
    TimeOfDay timeOfDay = new TimeOfDay(offenseValidationDto.timeOfDay);

    return new OffenseValidation(parkingStickerCode, parkingAreaCode, timeOfDay);
  }
}
