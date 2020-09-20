package ca.ulaval.glo4003.domain.user;

import ca.ulaval.glo4003.api.contact.dto.UserDto;

public class UserAssembler {

  public User create(UserDto userDto) {
    return new User(
        userDto.name, userDto.birthDate, userDto.sex, userDto.age, userDto.communication);
  }

  public UserDto create(User user) {
    UserDto userDto = new UserDto();
    userDto.name = user.getName();
    userDto.birthDate = user.getBirthDate();
    userDto.sex = user.getSex();
    userDto.age = user.getAge();
    userDto.communication = user.getCommunication();

    return userDto;
  }
}
