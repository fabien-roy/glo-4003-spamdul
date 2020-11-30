package ca.ulaval.glo4003.offenses.services.assemblers;

import ca.ulaval.glo4003.offenses.domain.OffenseValidation;
import ca.ulaval.glo4003.offenses.services.dto.OffenseValidationDto;
import ca.ulaval.glo4003.parkings.domain.ParkingAreaCode;
import ca.ulaval.glo4003.parkings.domain.ParkingStickerCode;
import ca.ulaval.glo4003.parkings.services.assemblers.ParkingAreaCodeAssembler;
import ca.ulaval.glo4003.parkings.services.assemblers.ParkingStickerCodeAssembler;
import ca.ulaval.glo4003.times.assemblers.TimeOfDayAssembler;
import ca.ulaval.glo4003.times.domain.TimeOfDay;

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
