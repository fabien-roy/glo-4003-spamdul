package ca.ulaval.glo4003.files.domain.exceptions;

import ca.ulaval.glo4003.errors.domain.ErrorCode;
import ca.ulaval.glo4003.errors.domain.exceptions.ApplicationException;

public class InvalidFileException extends ApplicationException {
  private static final String ERROR = "Invalid File";
  private static final String DESCRIPTION = "File is invalid";
  private static final ErrorCode CODE = ErrorCode.INVALID_REQUEST;

  public InvalidFileException() {
    super(ERROR, DESCRIPTION, CODE);
  }
}
