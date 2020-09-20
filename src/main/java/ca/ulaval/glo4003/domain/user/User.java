package ca.ulaval.glo4003.domain.user;

public class User {
  private int id;
  private String name;
  private String birthDate;
  private String sex;
  private int age;
  private String communication;

  public User(String name, String birthDate, String sex, int age, String communication) {
    this.name = name;
    this.birthDate = birthDate;
    this.sex = sex;
    this.age = age;
    this.communication = communication;
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

  public String getCommunication() {
    return communication;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getId() {
    return id;
  }
}
