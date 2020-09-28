package ca.ulaval.glo4003.domain.location.exception;

public abstract class LocationException extends RuntimeException {
  public String error;
  public String description;

  public LocationException(String error, String description) {
    this.error = error;
    this.description = description;
  }
}
