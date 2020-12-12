package ca.ulaval.glo4003.communications.domain.exceptions;

import ca.ulaval.glo4003.errors.domain.ErrorCode;
import ca.ulaval.glo4003.errors.domain.exceptions.ApplicationException;

public class EmailSendingFailedException extends ApplicationException {
  public static final String ERROR = "Email could not be sent";
  public static final String DESCRIPTION = "An error occurred while trying to send an email";
  public static final ErrorCode CODE = ErrorCode.APPLICATION_FAILURE;

  public EmailSendingFailedException() {
    super(ERROR, DESCRIPTION, CODE);
  }
}
