package ca.ulaval.glo4003.times.helpers;

import ca.ulaval.glo4003.times.domain.TimeOfDay;
import com.github.javafaker.Faker;
import java.time.LocalTime;

public class TimeOfDayMother {
  public static TimeOfDay createTimeOfDay() {
    int hours = Faker.instance().number().numberBetween(0, 23);
    int minutes = Faker.instance().number().numberBetween(0, 59);
    return new TimeOfDay(LocalTime.of(hours, minutes));
  }
}
