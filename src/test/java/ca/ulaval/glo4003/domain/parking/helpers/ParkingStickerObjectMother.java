package ca.ulaval.glo4003.domain.parking.helpers;

import ca.ulaval.glo4003.domain.parking.ParkingStickerCode;
import com.github.javafaker.Faker;

public class ParkingStickerObjectMother {
  public static ParkingStickerCode createParkingStickerCode() {
    return new ParkingStickerCode(Faker.instance().color().toString());
  }
}
