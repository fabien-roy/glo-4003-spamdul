package ca.ulaval.glo4003.domain.file.exceptions;

public class InvalidFileException extends FileException {
  private static final String ERROR = "Invalid File";
  private static final String DESCRIPTION = "Csv file is invalid";

  public InvalidFileException() {
    super(ERROR, DESCRIPTION);
  }
}
