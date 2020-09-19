package ca.ulaval.glo4003.domain.contact;

public class Contact {
  private String id;
  private String telephoneNumber;
  private String address;
  private String name;

  public Contact(String telephoneNumber, String address, String name) {
    this.telephoneNumber = telephoneNumber;
    this.address = address;
    this.name = name;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getTelephoneNumber() {
    return telephoneNumber;
  }

  public String getAddress() {
    return address;
  }

  public String getName() {
    return name;
  }
}
