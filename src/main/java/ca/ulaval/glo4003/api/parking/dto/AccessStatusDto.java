package ca.ulaval.glo4003.api.parking.dto;

public class AccessStatusDto {
  public String accessStatus;

  @Override
  public String toString() {
    return String.format("accessStatus='%s'", accessStatus);
  }
}
