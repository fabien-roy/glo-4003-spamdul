package ca.ulaval.glo4003.domain.parking.helpers;

import ca.ulaval.glo4003.domain.parking.ParkingAreaCode;
import com.github.javafaker.Faker;

public class ParkingAreaMother {
  public static ParkingAreaCode createParkingAreaCode() {
    return new ParkingAreaCode(Faker.instance().color().toString());
  }
}
