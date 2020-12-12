package ca.ulaval.glo4003.cars.domain.exceptions;

import ca.ulaval.glo4003.errors.domain.ErrorCode;
import ca.ulaval.glo4003.errors.domain.exceptions.ApplicationException;

public class InvalidLicensePlateException extends ApplicationException {
  private static final String ERROR = "Invalid license plate";
  private static final String DESCRIPTION = "License plate must be of format : %s";
  private static final ErrorCode CODE = ErrorCode.INVALID_REQUEST;
  private final String format;

  public InvalidLicensePlateException(String format) {
    super(ERROR, DESCRIPTION, CODE);
    this.format = format;
  }

  @Override
  public String getDescription() {
    return String.format(DESCRIPTION, format);
  }
}
