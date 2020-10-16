package ca.ulaval.glo4003.accesspasses.api.dto;

public class AccessPassCodeDto {
  public String accessPassCode;

  @Override
  public String toString() {
    return String.format("AccessPassCodeDto{accessPassCode='%s'}", accessPassCode);
  }
}
