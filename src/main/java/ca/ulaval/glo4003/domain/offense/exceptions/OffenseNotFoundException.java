package ca.ulaval.glo4003.domain.offense.exceptions;

public class OffenseNotFoundException extends InvalidOffenseException {

  private static final String ERROR = "Offense not found";
  private static final String DESCRIPTION =
      "The offense code used does not belong to an existing offense";

  public OffenseNotFoundException() {
    super(ERROR, DESCRIPTION);
  }
}
