package ca.ulaval.glo4003.communications.exceptions;

public class EmailSendingFailedException extends CommunicationException {
  public static final String ERROR = "Email could not be sent";
  public static final String DESCRIPTION = "An error occurred while trying to send an email";

  public EmailSendingFailedException() {
    super(ERROR, DESCRIPTION);
  }
}
