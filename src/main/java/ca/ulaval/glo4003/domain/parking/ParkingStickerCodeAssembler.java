package ca.ulaval.glo4003.domain.parking;

import ca.ulaval.glo4003.api.parking.dto.ParkingStickerCodeDto;

public class ParkingStickerCodeAssembler {
  public ParkingStickerCodeDto assemble(ParkingStickerCode parkingStickerCode) {
    ParkingStickerCodeDto parkingStickerCodeDto = new ParkingStickerCodeDto();
    parkingStickerCodeDto.code = parkingStickerCode.toString();
    return parkingStickerCodeDto;
  }

  public ParkingStickerCode assemble(ParkingStickerCodeDto parkingStickerCodeDto) {
    ParkingStickerCode parkingStickerCode =
        new ParkingStickerCode(parkingStickerCodeDto.toString());
    return parkingStickerCode;
  }
}
