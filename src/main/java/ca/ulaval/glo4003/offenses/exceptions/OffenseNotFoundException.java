package ca.ulaval.glo4003.offenses.exceptions;

public class OffenseNotFoundException extends OffenseException {

  private static final String ERROR = "Offense not found";
  private static final String DESCRIPTION =
      "The offense code used does not belong to an existing offense";

  public OffenseNotFoundException() {
    super(ERROR, DESCRIPTION);
  }
}
