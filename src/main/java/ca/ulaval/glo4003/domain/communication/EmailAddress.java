package ca.ulaval.glo4003.domain.communication;

public class EmailAddress {
  private final String address;

  public EmailAddress(String address) {
    this.address = address;
  }

  @Override
  public String toString() {
    return address;
  }

  @Override
  public boolean equals(Object object) {
    if (object == null || getClass() != object.getClass()) return false;

    EmailAddress email = (EmailAddress) object;

    return address.equals(email.toString());
  }

  @Override
  public int hashCode() {
    return address.hashCode();
  }
}
