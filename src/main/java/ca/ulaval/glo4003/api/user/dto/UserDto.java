package ca.ulaval.glo4003.api.user.dto;

public class UserDto {
  public String name;
  public String birthDate;
  public String sex;
  public String accessDay;

  @Override
  public String toString() {
    return String.format(
        "UserDto{name='%s', birthDate='%s', sex='%s', accessDay='%s'}",
        name, birthDate, sex, accessDay);
  }
}
