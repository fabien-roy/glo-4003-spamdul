package ca.ulaval.glo4003.domain.contact.exception;

public class ContactNotFoundException extends ContactException {
  public static final String ERROR = "Contact not found";
  public static final String DESCRIPTION = "Contact not found in repository";

  public ContactNotFoundException() {
    super(ERROR, DESCRIPTION);
  }
}
