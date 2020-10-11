package ca.ulaval.glo4003.parkings.domain;

import java.util.UUID;

public class ParkingStickerCodeGenerator {
  public ParkingStickerCode generate() {
    return new ParkingStickerCode(UUID.randomUUID().toString());
  }
}
