package ca.ulaval.glo4003.domain.parking;

import ca.ulaval.glo4003.api.parking.dto.ParkingStickerCodeDto;
import ca.ulaval.glo4003.domain.parking.exception.InvalidParkingStickerCodeException;

public class ParkingStickerCodeAssembler {
  public ParkingStickerCodeDto assemble(ParkingStickerCode parkingStickerCode) {
    ParkingStickerCodeDto parkingStickerCodeDto = new ParkingStickerCodeDto();
    parkingStickerCodeDto.parkingStickerCode = parkingStickerCode.toString();
    return parkingStickerCodeDto;
  }

  public ParkingStickerCode assemble(ParkingStickerCodeDto parkingStickerCodeDto) {
    return assemble(parkingStickerCodeDto.parkingStickerCode);
  }

  public ParkingStickerCode assemble(String parkingStickerCode) {
    if (parkingStickerCode == null) throw new InvalidParkingStickerCodeException();

    return new ParkingStickerCode(parkingStickerCode);
  }
}
