package ca.ulaval.glo4003.domain.user;

import ca.ulaval.glo4003.api.user.dto.UserDto;
import ca.ulaval.glo4003.domain.user.exception.InvalidAccessDayException;
import ca.ulaval.glo4003.domain.user.exception.InvalidBirthDateException;
import ca.ulaval.glo4003.domain.user.exception.InvalidNameException;
import ca.ulaval.glo4003.times.assemblers.CustomDateAssembler;
import ca.ulaval.glo4003.times.domain.CustomDate;
import ca.ulaval.glo4003.times.domain.Days;
import ca.ulaval.glo4003.times.exceptions.InvalidDateException;
import ca.ulaval.glo4003.times.exceptions.InvalidDayException;

public class UserAssembler {
  private final CustomDateAssembler customDateAssembler;

  public UserAssembler(CustomDateAssembler customDateAssembler) {
    this.customDateAssembler = customDateAssembler;
  }

  public User assemble(UserDto userDto) {
    CustomDate birthDate;
    Days accessDay;

    validateNotNull(userDto);

    try {
      birthDate = customDateAssembler.assemble(userDto.birthDate);
    } catch (InvalidDateException exception) {
      throw new InvalidBirthDateException();
    }

    if (birthDate.isFuture()) throw new InvalidBirthDateException();

    try {
      accessDay = Days.get(userDto.accessDay);
    } catch (InvalidDayException exception) {
      throw new InvalidAccessDayException();
    }

    return new User(userDto.name, birthDate, Sex.get(userDto.sex), accessDay);
  }

  public UserDto assemble(User user) {
    UserDto userDto = new UserDto();
    userDto.name = user.getName();
    userDto.birthDate = user.getBirthDate().toString();
    userDto.sex = user.getSex().name().toLowerCase();
    userDto.accessDay = user.getAccessDay().toString();

    return userDto;
  }

  private void validateNotNull(UserDto userDto) {
    if (userDto.name == null) {
      throw new InvalidNameException();
    } else if (userDto.birthDate == null) {
      throw new InvalidBirthDateException();
    }
  }
}
