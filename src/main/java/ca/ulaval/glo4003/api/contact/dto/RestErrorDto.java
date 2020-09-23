package ca.ulaval.glo4003.api.contact.dto;

public class RestErrorDto {
  public String error;
  public String description;

  @Override
  public String toString() {
    return String.format("RestErrorDto{error='%s', description='%s'}", error, description);
  }
}
