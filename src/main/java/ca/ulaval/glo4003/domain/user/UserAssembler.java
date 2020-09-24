package ca.ulaval.glo4003.domain.user;

import ca.ulaval.glo4003.api.user.dto.UserDto;
import ca.ulaval.glo4003.domain.time.CustomDate;

public class UserAssembler {
  public User assemble(UserDto userDto) {
    return new User(userDto.name, new CustomDate(userDto.birthDate), Sex.get(userDto.sex));
  }

  public UserDto assemble(User user) {
    UserDto userDto = new UserDto();
    userDto.name = user.getName();
    userDto.birthDate = user.getBirthDate().toString();
    userDto.sex = user.getSex().name().toLowerCase();

    return userDto;
  }
}
