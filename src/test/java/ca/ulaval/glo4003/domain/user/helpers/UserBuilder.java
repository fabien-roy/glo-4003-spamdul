package ca.ulaval.glo4003.domain.user.helpers;

import static ca.ulaval.glo4003.domain.user.helpers.UserMother.createName;

import ca.ulaval.glo4003.domain.time.CustomDate;
import ca.ulaval.glo4003.domain.user.Sex;
import ca.ulaval.glo4003.domain.user.User;

public class UserBuilder {
  private String name = createName();
  private CustomDate birthDate;
  private Sex sex;

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
