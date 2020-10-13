package ca.ulaval.glo4003.users.assemblers;

import ca.ulaval.glo4003.times.assemblers.CustomDateAssembler;
import ca.ulaval.glo4003.times.domain.CustomDate;
import ca.ulaval.glo4003.times.domain.DayOfWeek;
import ca.ulaval.glo4003.times.exceptions.InvalidDateException;
import ca.ulaval.glo4003.times.exceptions.InvalidDayOfWeekException;
import ca.ulaval.glo4003.users.api.dto.UserDto;
import ca.ulaval.glo4003.users.domain.Sex;
import ca.ulaval.glo4003.users.domain.User;
import ca.ulaval.glo4003.users.exceptions.InvalidAccessDayException;
import ca.ulaval.glo4003.users.exceptions.InvalidBirthDateException;
import ca.ulaval.glo4003.users.exceptions.InvalidNameException;

public class UserAssembler {
  private final CustomDateAssembler customDateAssembler;

  public UserAssembler(CustomDateAssembler customDateAssembler) {
    this.customDateAssembler = customDateAssembler;
  }

  public User assemble(UserDto userDto) {
    CustomDate birthDate;
    DayOfWeek accessDay;

    validateNotNull(userDto);

    try {
      birthDate = customDateAssembler.assemble(userDto.birthDate);
    } catch (InvalidDateException exception) {
      throw new InvalidBirthDateException();
    }

    if (birthDate.isFuture()) throw new InvalidBirthDateException();

    try {
      accessDay = DayOfWeek.get(userDto.accessDay);
    } catch (InvalidDayOfWeekException exception) {
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
