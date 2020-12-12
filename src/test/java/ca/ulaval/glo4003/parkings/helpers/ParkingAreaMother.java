package ca.ulaval.glo4003.parkings.helpers;

import static ca.ulaval.glo4003.funds.helpers.MoneyMother.createMoney;
import static ca.ulaval.glo4003.randomizers.helpers.Randomizer.randomEnum;
import static ca.ulaval.glo4003.randomizers.helpers.Randomizer.randomEnums;

import ca.ulaval.glo4003.funds.domain.Money;
import ca.ulaval.glo4003.parkings.domain.ParkingAreaCode;
import ca.ulaval.glo4003.parkings.domain.ParkingPeriod;
import ca.ulaval.glo4003.parkings.domain.ParkingPeriodInFrench;
import com.github.javafaker.Faker;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParkingAreaMother {
  public static ParkingAreaCode createParkingAreaCode() {
    String parkingAreaCode = Faker.instance().beer().name();
    return new ParkingAreaCode(parkingAreaCode);
  }

  public static Map<ParkingPeriod, Money> createFeePerPeriod() {
    List<ParkingPeriod> parkingPeriods =
        randomEnums(ParkingPeriod.class, Faker.instance().number().numberBetween(1, 5));
    Map<ParkingPeriod, Money> feePerPeriod = new HashMap<>();
    parkingPeriods.forEach(period -> feePerPeriod.put(period, createMoney()));
    return feePerPeriod;
  }

  public static Map<String, Map<String, Double>> createParkingDataFromExcel() {
    Map<String, Double> feeByPeriod = new HashMap<>();
    feeByPeriod.put(
        randomEnum(ParkingPeriodInFrench.class).toString(), Faker.instance().random().nextDouble());
    Map<String, Map<String, Double>> feeByPeriodByArea = new HashMap<>();

    feeByPeriodByArea.put(Faker.instance().random().toString(), feeByPeriod);
    return feeByPeriodByArea;
  }
}
