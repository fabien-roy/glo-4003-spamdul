package ca.ulaval.glo4003.domain.offense.exceptions;

public class OffenseCodeNotFoundException extends InvalidOffenseException {
  private static final String ERROR = "Offense code not found";
  private static final String DESCRIPTION = "You should enter a valid code";

  public OffenseCodeNotFoundException() {
    super(ERROR, DESCRIPTION);
  }
}
