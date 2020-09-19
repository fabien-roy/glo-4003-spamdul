package ca.ulaval.glo4003.api.contact.dto;

public class ContactDto {
  public String id;
  public String telephoneNumber;
  public String address;
  public String name;

  @Override
  public String toString() {
    return String.format("ContactDto{id='%s', telephoneNumber='%s', address='%s', name='%s'}", id, telephoneNumber, address, name);
  }
}
