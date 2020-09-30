package ca.ulaval.glo4003.files.exceptions;

public class FileException extends RuntimeException {
  public final String error;
  public final String description;

  FileException(String error, String description) {
    this.error = error;
    this.description = description;
  }
}
