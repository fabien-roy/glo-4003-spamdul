package ca.ulaval.glo4003.parkings.assemblers;

import ca.ulaval.glo4003.parkings.api.dto.ParkingStickerCodeDto;
import ca.ulaval.glo4003.parkings.domain.ParkingStickerCode;
import ca.ulaval.glo4003.parkings.exceptions.InvalidParkingStickerCodeException;

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
