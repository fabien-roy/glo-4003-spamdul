package ca.ulaval.glo4003.api.interfaces.dto;

public class ErrorDto {
  public String error;
  public String description;

  @Override
  public String toString() {
    return String.format("RestErrorDto{error='%s', description='%s'}", error, description);
  }
}
