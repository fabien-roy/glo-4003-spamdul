package ca.ulaval.glo4003.domain.bill.exceptions;

public class InvalidFileException extends BillException {
  private static final String ERROR = "Invalid File";
  private static final String DESCRIPTION = "Csv file is invalid";

  public InvalidFileException() {
    super(ERROR, DESCRIPTION);
  }
}
