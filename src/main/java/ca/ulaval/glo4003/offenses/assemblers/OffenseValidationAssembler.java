package ca.ulaval.glo4003.domain.offense;

import ca.ulaval.glo4003.offenses.api.dto.OffenseValidationDto;
import ca.ulaval.glo4003.domain.parking.ParkingAreaCode;
import ca.ulaval.glo4003.domain.parking.ParkingAreaCodeAssembler;
import ca.ulaval.glo4003.domain.parking.ParkingStickerCode;
import ca.ulaval.glo4003.domain.parking.ParkingStickerCodeAssembler;
import ca.ulaval.glo4003.domain.time.TimeOfDay;
import ca.ulaval.glo4003.domain.time.TimeOfDayAssembler;

public class OffenseValidationAssembler {
  private final ParkingStickerCodeAssembler parkingStickerCodeAssembler;
  private final ParkingAreaCodeAssembler parkingAreaCodeAssembler;
  private final TimeOfDayAssembler timeOfDayAssembler;

  public OffenseValidationAssembler(
      ParkingStickerCodeAssembler parkingStickerCodeAssembler,
      ParkingAreaCodeAssembler parkingAreaCodeAssembler,
      TimeOfDayAssembler timeOfDayAssembler) {
    this.parkingStickerCodeAssembler = parkingStickerCodeAssembler;
    this.parkingAreaCodeAssembler = parkingAreaCodeAssembler;
    this.timeOfDayAssembler = timeOfDayAssembler;
  }

  public OffenseValidation assemble(OffenseValidationDto offenseValidationDto) {
    ParkingStickerCode parkingStickerCode =
        parkingStickerCodeAssembler.assemble(offenseValidationDto.parkingStickerCode);
    ParkingAreaCode parkingAreaCode =
        parkingAreaCodeAssembler.assemble(offenseValidationDto.parkingArea);
    TimeOfDay timeOfDay = timeOfDayAssembler.assemble(offenseValidationDto.timeOfDay);

    return new OffenseValidation(parkingStickerCode, parkingAreaCode, timeOfDay);
  }
}
