package ca.ulaval.glo4003.api.contact.dto;

public class UserDto {
  public String name;
  public String birthDate;
  public String sex;
  public Integer age;
  public String preferredCommunicationMethod;
  public String postalCode;

  @Override
  public String toString() {
    return String.format(
        "UserDto{name='%s', birthDate='%s', sex='%s', age='%s', communication='%', postalCode='%s'}",
        name, birthDate, sex, age, preferredCommunicationMethod, postalCode);
  }
}
