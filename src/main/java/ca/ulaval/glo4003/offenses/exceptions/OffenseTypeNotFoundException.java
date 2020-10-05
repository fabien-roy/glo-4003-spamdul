package ca.ulaval.glo4003.offenses.exceptions;

public class OffenseTypeNotFoundException extends OffenseException {

  private static final String ERROR = "Offense type not found";
  private static final String DESCRIPTION =
      "The offense code used does not belong to an existing offense type";

  public OffenseTypeNotFoundException() {
    super(ERROR, DESCRIPTION);
  }
}
