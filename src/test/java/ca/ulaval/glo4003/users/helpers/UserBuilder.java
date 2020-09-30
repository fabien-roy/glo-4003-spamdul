package ca.ulaval.glo4003.users.helpers;

import static ca.ulaval.glo4003.times.helpers.CustomDateMother.createPastDate;
import static ca.ulaval.glo4003.users.helpers.UserMother.createName;
import static ca.ulaval.glo4003.users.helpers.UserMother.createSex;

import ca.ulaval.glo4003.times.domain.CustomDate;
import ca.ulaval.glo4003.times.domain.Days;
import ca.ulaval.glo4003.users.domain.Sex;
import ca.ulaval.glo4003.users.domain.User;

public class UserBuilder {
  private String name = createName();
  private CustomDate birthDate = createPastDate();
  private Sex sex = createSex();
  private Days accessDay;

  private UserBuilder() {}

  public static UserBuilder aUser() {
    return new UserBuilder();
  }

  public UserBuilder withName(String name) {
    this.name = name;
    return this;
  }

  public UserBuilder withBirthDate(CustomDate birthDate) {
    this.birthDate = birthDate;
    return this;
  }

  public UserBuilder withSex(Sex sex) {
    this.sex = sex;
    return this;
  }

  public UserBuilder withAccessDay(Days accessDay) {
    this.accessDay = accessDay;
    return this;
  }

  public User build() {
    return new User(name, birthDate, sex, accessDay);
  }
}
