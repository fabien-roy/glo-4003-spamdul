package ca.ulaval.glo4003.accesspasses.exceptions;

public class WrongAmountOfSemestersForPeriodException extends AccessPassException {
  private static final String ERROR = "Wrong amount of semesters";
  private static final String DESCRIPTION =
      "The amount of semesters provided doesn't match the period selected";

  public WrongAmountOfSemestersForPeriodException() {
    super(ERROR, DESCRIPTION);
  }
}
