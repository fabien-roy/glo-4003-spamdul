package ca.ulaval.glo4003.parkings.helpers;

import static ca.ulaval.glo4003.parkings.helpers.ParkingStickerMother.createParkingStickerCode;

import ca.ulaval.glo4003.parkings.services.dto.ParkingStickerCodeDto;

public class ParkingStickerCodeDtoBuilder {
  private String parkingStickerCode = createParkingStickerCode().toString();

  public static ParkingStickerCodeDtoBuilder aParkingStickerCodeDto() {
    return new ParkingStickerCodeDtoBuilder();
  }

  public ParkingStickerCodeDto build() {
    ParkingStickerCodeDto parkingStickerCodeDto = new ParkingStickerCodeDto();
    parkingStickerCodeDto.parkingStickerCode = parkingStickerCode;
    return parkingStickerCodeDto;
  }
}
