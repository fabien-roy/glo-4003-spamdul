package ca.ulaval.glo4003.parkings.domain;

import java.util.UUID;

public class ParkingStickerCodeGenerator {
  public ParkingStickerCode generate() {
    // TODO : Instead of UUID, shouldn't we use some sticker code pattern?
    return new ParkingStickerCode(UUID.randomUUID().toString());
  }
}
