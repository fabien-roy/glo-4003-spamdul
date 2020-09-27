package ca.ulaval.glo4003.domain.parking;

import ca.ulaval.glo4003.api.parking.dto.ParkingStickerCodeDto;
import ca.ulaval.glo4003.domain.parking.exception.InvalidParkingStickerCodeException;

public class ParkingStickerCodeAssembler {
  public ParkingStickerCodeDto assemble(ParkingStickerCode parkingStickerCode) {
    ParkingStickerCodeDto parkingStickerCodeDto = new ParkingStickerCodeDto();
    parkingStickerCodeDto.code = parkingStickerCode.toString();
    return parkingStickerCodeDto;
  }

  public ParkingStickerCode assemble(ParkingStickerCodeDto parkingStickerCodeDto) {
    if (parkingStickerCodeDto.code == null) throw new InvalidParkingStickerCodeException();

    return new ParkingStickerCode(parkingStickerCodeDto.code);
  }
}
