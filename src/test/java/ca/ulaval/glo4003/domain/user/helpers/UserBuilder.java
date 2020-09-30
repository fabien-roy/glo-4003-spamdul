package ca.ulaval.glo4003.domain.user.helpers;

import static ca.ulaval.glo4003.domain.user.helpers.UserMother.createName;
import static ca.ulaval.glo4003.domain.user.helpers.UserMother.createSex;
import static ca.ulaval.glo4003.times.helpers.CustomDateMother.createPastDate;

import ca.ulaval.glo4003.domain.user.Sex;
import ca.ulaval.glo4003.domain.user.User;
import ca.ulaval.glo4003.times.domain.CustomDate;
import ca.ulaval.glo4003.times.domain.Days;

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
