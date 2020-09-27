package ca.ulaval.glo4003.domain.time.exception;

public abstract class TimeException extends RuntimeException {
  public String error;
  public String description;

  TimeException(String error, String description) {
    this.error = error;
    this.description = description;
  }
}
