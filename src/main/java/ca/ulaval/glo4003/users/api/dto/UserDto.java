package ca.ulaval.glo4003.users.api.dto;

public class UserDto {
  public String name;
  public String birthDate;
  public String sex;

  @Override
  public String toString() {
    return String.format("UserDto{name='%s', birthDate='%s', sex='%s'}", name, birthDate, sex);
  }
}
