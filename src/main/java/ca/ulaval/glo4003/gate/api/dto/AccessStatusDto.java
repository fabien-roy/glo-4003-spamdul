package ca.ulaval.glo4003.gate.api.dto;

public class AccessStatusDto {
  public String accessStatus;

  @Override
  public String toString() {
    return String.format("accessStatusDto='%s'", accessStatus);
  }
}
