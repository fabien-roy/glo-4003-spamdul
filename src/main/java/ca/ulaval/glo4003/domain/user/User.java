package ca.ulaval.glo4003.domain.user;

import ca.ulaval.glo4003.domain.time.CustomDate;

public class User {
  private String name;
  private CustomDate birthDate;
  private Sex sex;

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
