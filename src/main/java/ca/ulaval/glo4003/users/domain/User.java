package ca.ulaval.glo4003.users.domain;

import ca.ulaval.glo4003.times.domain.CustomDate;
import ca.ulaval.glo4003.times.domain.Days;

public class User {
  private String name;
  private CustomDate birthDate;
  private Sex sex;
  private Days accessDay;

  public User(String name, CustomDate birthDate, Sex sex, Days accessDay) {
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

  public Days getAccessDay() {
    return accessDay;
  }
}
