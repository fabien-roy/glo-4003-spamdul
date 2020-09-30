package ca.ulaval.glo4003.domain.communication.exception;

public class InvalidEmailAddressException extends CommunicationException {
  public static final String ERROR = "Invalid email address";
  public static final String DESCRIPTION = "Email address is invalid";

  public InvalidEmailAddressException() {
    super(ERROR, DESCRIPTION);
  }
}
