package ca.ulaval.glo4003.domain.user;

import ca.ulaval.glo4003.api.user.dto.UserDto;

public class UserAssembler {
  public User create(UserDto userDto) {
    return new User(userDto.name, new CustomDate(userDto.birthDate), Sex.get(userDto.sex));
  }

  public UserDto create(User user) {
    UserDto userDto = new UserDto();
    userDto.name = user.getName();
    userDto.birthDate = user.getBirthDate().toString();
    userDto.sex = user.getSex().name().toLowerCase();

    return userDto;
  }
}
