package ca.ulaval.glo4003.parkings.helpers;

import static ca.ulaval.glo4003.interfaces.helpers.Randomizer.randomEnum;

import ca.ulaval.glo4003.parkings.domain.ParkingPeriod;
import ca.ulaval.glo4003.parkings.domain.ParkingStickerCode;
import ca.ulaval.glo4003.parkings.domain.ReceptionMethod;
import com.github.javafaker.Faker;

public class ParkingStickerMother {
  public static ParkingStickerCode createParkingStickerCode() {
    return new ParkingStickerCode(Faker.instance().color().toString());
  }

  public static ReceptionMethod createReceptionMethod() {
    return randomEnum(ReceptionMethod.class);
  }

  public static ParkingPeriod createParkingPeriod() {
    return randomEnum(ParkingPeriod.class);
  }
}
