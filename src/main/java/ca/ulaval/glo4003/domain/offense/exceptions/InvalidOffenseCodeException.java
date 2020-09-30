package ca.ulaval.glo4003.domain.offense.exceptions;

public class InvalidOffenseCodeException extends OffenseException {
  private static final String ERROR = "Invalid offense code";
  private static final String DESCRIPTION = "Offense code is invalid";

  public InvalidOffenseCodeException() {
    super(ERROR, DESCRIPTION);
  }
}
