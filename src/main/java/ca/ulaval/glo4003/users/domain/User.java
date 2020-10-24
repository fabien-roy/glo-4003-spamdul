package ca.ulaval.glo4003.users.domain;

import ca.ulaval.glo4003.times.domain.CustomDate;

public class User {
  private final String name;
  private final CustomDate birthDate;
  private final Sex sex;

  public User(String name, CustomDate birthDate, Sex sex) {
    this.name = name;
    this.birthDate = birthDate;
    this.sex = sex;
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
}
