package ca.ulaval.glo4003.gates.api.dto;

public class AccessStatusDto {
  public String accessStatus;

  @Override
  public String toString() {
    return String.format("accessStatusDto='%s'", accessStatus);
  }
}
