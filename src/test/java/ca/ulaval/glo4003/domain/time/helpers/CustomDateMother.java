package ca.ulaval.glo4003.domain.time.helpers;

import ca.ulaval.glo4003.domain.time.CustomDate;
import com.github.javafaker.Faker;
import java.time.LocalDate;
import java.time.ZoneId;

public class CustomDateMother {
  public static CustomDate createPastDate() {
    LocalDate pastDate =
        Faker.instance().date().birthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    return new CustomDate(pastDate);
  }
}
