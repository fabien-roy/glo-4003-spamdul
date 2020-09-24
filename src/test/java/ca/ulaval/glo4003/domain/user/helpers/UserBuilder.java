package ca.ulaval.glo4003.domain.user.helpers;

import static ca.ulaval.glo4003.domain.time.helpers.CustomDateMother.createPastDate;
import static ca.ulaval.glo4003.domain.user.helpers.UserMother.createName;
import static ca.ulaval.glo4003.domain.user.helpers.UserMother.createSex;

import ca.ulaval.glo4003.domain.time.CustomDate;
import ca.ulaval.glo4003.domain.user.Sex;
import ca.ulaval.glo4003.domain.user.User;

public class UserBuilder {
  private String name = createName();
  private CustomDate birthDate = createPastDate();
  private Sex sex = createSex();

  private UserBuilder() {}

  public static UserBuilder aUser() {
    return new UserBuilder();
  }

  public UserBuilder withName(String name) {
    this.name = name;
    return this;
  }

  public User build() {
    return new User(name, birthDate, sex);
  }
}
