package ca.ulaval.glo4003.users.services.converters;

import ca.ulaval.glo4003.times.domain.CustomDate;
import ca.ulaval.glo4003.times.exceptions.InvalidDateException;
import ca.ulaval.glo4003.times.services.converters.CustomDateConverter;
import ca.ulaval.glo4003.users.domain.Sex;
import ca.ulaval.glo4003.users.domain.User;
import ca.ulaval.glo4003.users.exceptions.InvalidBirthDateException;
import ca.ulaval.glo4003.users.exceptions.InvalidNameException;
import ca.ulaval.glo4003.users.services.dto.UserDto;

public class UserConverter {
  private final CustomDateConverter customDateConverter;

  public UserConverter(CustomDateConverter customDateConverter) {
    this.customDateConverter = customDateConverter;
  }

  public User convert(UserDto userDto) {
    CustomDate birthDate;

    validateNotNull(userDto);

    try {
      birthDate = customDateConverter.convert(userDto.birthDate);
    } catch (InvalidDateException exception) {
      throw new InvalidBirthDateException();
    }

    if (birthDate.isFuture()) throw new InvalidBirthDateException();

    return new User(userDto.name, birthDate, Sex.get(userDto.sex));
  }

  private void validateNotNull(UserDto userDto) {
    if (userDto.name == null) {
      throw new InvalidNameException();
    } else if (userDto.birthDate == null) {
      throw new InvalidBirthDateException();
    }
  }
}
