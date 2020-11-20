package ca.ulaval.glo4003.files.exceptions;

public class InvalidFileException extends FileException {
  private static final String ERROR = "Invalid File";
  private static final String DESCRIPTION = "File is invalid";

  public InvalidFileException() {
    super(ERROR, DESCRIPTION);
  }
}
