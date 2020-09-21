package ca.ulaval.glo4003.api.contact.dto;

public class PostUserDto {
  public String accountId;

  @Override
  public String toString() {
    return String.format("PostUserDto{accountId='%s'}", accountId);
  }
}
