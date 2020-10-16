package ca.ulaval.glo4003.funds.helpers;

import static ca.ulaval.glo4003.interfaces.helpers.Randomizer.randomEnum;

import ca.ulaval.glo4003.funds.domain.BillId;
import ca.ulaval.glo4003.funds.domain.BillType;
import com.github.javafaker.Faker;
import java.util.UUID;

public class BillMother {
  public static BillId createBillId() {
    return new BillId(UUID.randomUUID());
  }

  public static String createDescription() {
    return Faker.instance().elderScrolls().quote();
  }

  public static BillType createBillType() {
    return randomEnum(BillType.class);
  }

  public static int createYear() {
    return Faker.instance().number().numberBetween(2000, 2020);
  }
}
