package ca.ulaval.glo4003.files.domain.exceptions;

import ca.ulaval.glo4003.errors.domain.ErrorCode;
import ca.ulaval.glo4003.errors.domain.exceptions.ApplicationException;

public class InvalidFileException extends ApplicationException {
  private static final String ERROR = "Invalid file";
  private static final String DESCRIPTION = "File %s is invalid";
  private static final ErrorCode CODE = ErrorCode.APPLICATION_FAILURE;
  private final String filePath;

  public InvalidFileException(String filePath) {
    super(ERROR, DESCRIPTION, CODE);
    this.filePath = filePath;
  }

  @Override
  public String getDescription() {
    return String.format(DESCRIPTION, filePath);
  }
}
