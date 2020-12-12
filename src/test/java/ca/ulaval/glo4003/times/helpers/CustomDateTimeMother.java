package ca.ulaval.glo4003.times.helpers;

import static ca.ulaval.glo4003.randomizers.helpers.Randomizer.randomEnum;

import ca.ulaval.glo4003.times.domain.CustomDateTime;
import com.github.javafaker.Faker;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class CustomDateTimeMother {
  public static CustomDateTime createDateTime() {
    return new CustomDateTime(createLocalDateTime());
  }

  public static LocalDateTime createLocalDateTime() {
    return createDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
  }

  private static Date createDate() {
    return Faker.instance().date().past(30, 1, TimeUnit.DAYS);
  }

  public static Month createMonth() {
    return randomEnum(Month.class);
  }
}
