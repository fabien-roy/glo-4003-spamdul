package ca.ulaval.glo4003.times.domain.exceptions;

public abstract class TimeException extends RuntimeException {
  public String error;
  public String description;

  TimeException(String error, String description) {
    this.error = error;
    this.description = description;
  }
}
