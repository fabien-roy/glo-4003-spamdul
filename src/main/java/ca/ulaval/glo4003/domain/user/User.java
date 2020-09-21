package ca.ulaval.glo4003.domain.user;

import ca.ulaval.glo4003.domain.user.userEnum.CommunicationMethod;
import ca.ulaval.glo4003.domain.user.userEnum.Sex;

public class User {
  private String name;
  private CustomDate birthDate;
  private Sex sex;
  private int age;
  private CommunicationMethod preferredCommunicationMethod;
  private String postalCode;

  public User(
      String name,
      CustomDate birthDate,
      Sex sex,
      int age,
      CommunicationMethod preferredCommunicationMethod,
      String postalCode) {
    this.name = name;
    this.birthDate = birthDate;
    this.sex = sex;
    this.age = age;
    this.postalCode = postalCode;
    this.preferredCommunicationMethod = preferredCommunicationMethod;
  }

  public String getName() {
    return name;
  }

  public CustomDate getBirthDate() {
    return birthDate;
  }

  public String getPostalCode() {
    return postalCode;
  }

  public Sex getSex() {
    return sex;
  }

  public int getAge() {
    return age;
  }

  public CommunicationMethod getPreferredCommunicationMethod() {
    return preferredCommunicationMethod;
  }
}
