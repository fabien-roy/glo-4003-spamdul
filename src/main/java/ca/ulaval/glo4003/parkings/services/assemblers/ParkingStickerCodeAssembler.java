package ca.ulaval.glo4003.parkings.services.assemblers;

import ca.ulaval.glo4003.parkings.domain.ParkingStickerCode;
import ca.ulaval.glo4003.parkings.exceptions.InvalidParkingStickerCodeException;
import ca.ulaval.glo4003.parkings.services.dto.ParkingStickerCodeDto;

public class ParkingStickerCodeAssembler {
  public ParkingStickerCodeDto assemble(ParkingStickerCode parkingStickerCode) {
    ParkingStickerCodeDto parkingStickerCodeDto = new ParkingStickerCodeDto();
    parkingStickerCodeDto.parkingStickerCode = parkingStickerCode.toString();
    return parkingStickerCodeDto;
  }

  public ParkingStickerCode assemble(String parkingStickerCode) {
    if (parkingStickerCode == null) throw new InvalidParkingStickerCodeException();

    return new ParkingStickerCode(parkingStickerCode);
  }
}
