package ca.ulaval.glo4003.accesspasses.services.dto;

public class AccessPassCodeDto {
  public String accessPassCode;

  @Override
  public String toString() {
    return String.format("AccessPassCodeDto{accessPassCode='%s'}", accessPassCode);
  }
}
