package ca.ulaval.glo4003.users.services.assemblers;

import ca.ulaval.glo4003.users.domain.User;
import ca.ulaval.glo4003.users.services.dto.UserDto;

public class UserAssembler {
  public UserDto assemble(User user) {
    UserDto userDto = new UserDto();
    userDto.name = user.getName();
    userDto.birthDate = user.getBirthDate().toString();
    userDto.sex = user.getSex().name().toLowerCase();

    return userDto;
  }
}
