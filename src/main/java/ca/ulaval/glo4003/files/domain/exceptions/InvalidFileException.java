package ca.ulaval.glo4003.files.domain.exceptions;

import ca.ulaval.glo4003.errors.domain.ErrorCode;
import ca.ulaval.glo4003.errors.domain.exceptions.ApplicationException;

// TODO #305 : Give file name
public class InvalidFileException extends ApplicationException {
  private static final String ERROR = "Invalid file";
  private static final String DESCRIPTION = "File is invalid";
  private static final ErrorCode CODE = ErrorCode.APPLICATION_FAILURE;

  public InvalidFileException() {
    super(ERROR, DESCRIPTION, CODE);
  }
}
