package ca.ulaval.glo4003.parkings.helpers;

import static ca.ulaval.glo4003.funds.helpers.MoneyMother.createMoney;
import static ca.ulaval.glo4003.interfaces.helpers.Randomizer.randomEnums;

import ca.ulaval.glo4003.funds.domain.Money;
import ca.ulaval.glo4003.parkings.domain.ParkingAreaCode;
import ca.ulaval.glo4003.parkings.domain.ParkingPeriods;
import com.github.javafaker.Faker;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParkingAreaMother {
  public static ParkingAreaCode createParkingAreaCode() {
    return new ParkingAreaCode("Zone1");
  }

  public static Map<ParkingPeriods, Money> createFeePerPeriod() {
    List<ParkingPeriods> parkingPeriods =
        randomEnums(ParkingPeriods.class, Faker.instance().number().numberBetween(1, 5));
    Map<ParkingPeriods, Money> feePerPeriod = new HashMap<>();
    parkingPeriods.forEach(period -> feePerPeriod.put(period, createMoney()));
    return feePerPeriod;
  }
}
