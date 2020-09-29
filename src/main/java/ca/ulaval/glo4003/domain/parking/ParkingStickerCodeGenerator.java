package ca.ulaval.glo4003.domain.parking;

import java.util.UUID;

public class ParkingStickerCodeGenerator {
  public ParkingStickerCode generate() {
    // TODO : Instead of UUID, shouldn't we use some sticker parkingStickerCode pattern?
    return new ParkingStickerCode(UUID.randomUUID().toString());
  }
}
