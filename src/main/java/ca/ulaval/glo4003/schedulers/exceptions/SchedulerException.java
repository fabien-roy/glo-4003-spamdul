package ca.ulaval.glo4003.schedulers.exceptions;

public abstract class SchedulerException extends RuntimeException {
  public String error;
  public String description;

  SchedulerException(String error, String description) {
    this.error = error;
    this.description = description;
  }
}
