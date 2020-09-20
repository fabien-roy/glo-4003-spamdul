package ca.ulaval.glo4003.domain.user;

public class User {
  private int id;
  private String name;
  private String birthDate;
  private String sex;
  private int age;
  private String preferredCommunicationMethod;

  public User(
      String name, String birthDate, String sex, int age, String preferredCommunicationMethod) {
    this.name = name;
    this.birthDate = birthDate;
    this.sex = sex;
    this.age = age;
    this.preferredCommunicationMethod = preferredCommunicationMethod;
  }

  public String getName() {
    return name;
  }

  public String getBirthDate() {
    return birthDate;
  }

  public String getSex() {
    return sex;
  }

  public int getAge() {
    return age;
  }

  public String getPreferredCommunicationMethod() {
    return preferredCommunicationMethod;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getId() {
    return id;
  }
}
