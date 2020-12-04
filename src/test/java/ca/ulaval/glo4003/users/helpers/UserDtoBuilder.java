package ca.ulaval.glo4003.users.helpers;

import static ca.ulaval.glo4003.times.helpers.CustomDateMother.createPastDate;
import static ca.ulaval.glo4003.users.helpers.UserMother.*;

import ca.ulaval.glo4003.users.services.dto.UserDto;

public class UserDtoBuilder {
  private String name = createName();
  private String birthDate = createPastDate().toString();
  private String sex = createSex().toString();

  private UserDtoBuilder() {}

  public static UserDtoBuilder aUserDto() {
    return new UserDtoBuilder();
  }

  public UserDtoBuilder withName(String name) {
    this.name = name;
    return this;
  }

  public UserDtoBuilder withBirthDate(String birthDate) {
    this.birthDate = birthDate;
    return this;
  }

  public UserDtoBuilder withSex(String sex) {
    this.sex = sex;
    return this;
  }

  public UserDto build() {
    UserDto userDto = new UserDto();
    userDto.name = name;
    userDto.birthDate = birthDate;
    userDto.sex = sex;
    return userDto;
  }
}
