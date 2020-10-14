package ca.ulaval.glo4003.users.domain;

import ca.ulaval.glo4003.times.domain.CustomDate;
import ca.ulaval.glo4003.times.domain.DayOfWeek;

public class User {
  private String name;
  private CustomDate birthDate;
  private Sex sex;
  private DayOfWeek accessDay;

  public User(String name, CustomDate birthDate, Sex sex, DayOfWeek accessDay) {
    this.name = name;
    this.birthDate = birthDate;
    this.sex = sex;
    this.accessDay = accessDay;
  }

  public String getName() {
    return name;
  }

  public CustomDate getBirthDate() {
    return birthDate;
  }

  public Sex getSex() {
    return sex;
  }

  public DayOfWeek getAccessDay() {
    return accessDay;
  }
}
