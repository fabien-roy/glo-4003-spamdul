package ca.ulaval.glo4003.domain.Email;

public class EmailAddress {
  private String email;

  public EmailAddress(String email) {
    this.email = email;
  }

  @Override
  public String toString() {
    return email;
  }

  @Override
  public boolean equals(Object object) {
    if (object == null || getClass() != object.getClass()) return false;

    EmailAddress emailAddress = (EmailAddress) object;

    return email.equals(emailAddress.toString());
  }

  @Override
  public int hashCode() {
    return email.hashCode();
  }
}
