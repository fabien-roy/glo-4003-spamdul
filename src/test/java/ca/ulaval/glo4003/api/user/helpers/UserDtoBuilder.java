package ca.ulaval.glo4003.api.user.helpers;

import static ca.ulaval.glo4003.domain.user.helpers.UserMother.*;
import static ca.ulaval.glo4003.times.helpers.CustomDateMother.createPastDate;

import ca.ulaval.glo4003.api.user.dto.UserDto;

public class UserDtoBuilder {
  private String name = createName();
  private String birthDate = createPastDate().toString();
  private String sex = createSex().toString();
  private String accessDay = createAccessDay().toString();

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

  public UserDtoBuilder withAccessDay(String accessDay) {
    this.accessDay = accessDay;
    return this;
  }

  public UserDto build() {
    UserDto userDto = new UserDto();
    userDto.name = name;
    userDto.birthDate = birthDate;
    userDto.sex = sex;
    userDto.accessDay = accessDay;
    return userDto;
  }
}
