package ca.ulaval.glo4003.interfaces.api.dto;

// TODO #300 : Move this to a services.dto somewhere
public class ErrorDto {
  public String error;
  public String description;

  @Override
  public String toString() {
    return String.format("RestErrorDto{error='%s', description='%s'}", error, description);
  }
}
