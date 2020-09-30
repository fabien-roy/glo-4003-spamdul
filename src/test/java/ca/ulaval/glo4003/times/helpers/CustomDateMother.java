package ca.ulaval.glo4003.times.helpers;

import ca.ulaval.glo4003.times.domain.CustomDate;
import com.github.javafaker.Faker;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class CustomDateMother {
  public static CustomDate createPastDate() {
    Date pastDate = Faker.instance().date().past(2000, 500, TimeUnit.DAYS);
    return new CustomDate(convertToLocalDate(pastDate));
  }

  public static CustomDate createFutureDate() {
    Date futureDate = Faker.instance().date().future(2000, 500, TimeUnit.DAYS);
    return new CustomDate(convertToLocalDate(futureDate));
  }

  private static LocalDate convertToLocalDate(Date date) {
    return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
  }
}
