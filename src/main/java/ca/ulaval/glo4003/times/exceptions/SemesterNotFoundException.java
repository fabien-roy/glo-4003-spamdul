package ca.ulaval.glo4003.times.exceptions;

public class SemesterNotFoundException extends TimeException {
  private static final String ERROR = "Semester not found";
  private static final String DESCRIPTION =
      "The semester code used does not belong to an existing semester";

  public SemesterNotFoundException() {
    super(ERROR, DESCRIPTION);
  }
}
