package ca.ulaval.glo4003.carboncredits.api.dto;

public class CarbonCreditDto {
  public double carbonCreditAmount;

  @Override
  public String toString() {
    return String.format("CarbonCredits{carbonCreditAmount='%s'}", carbonCreditAmount);
  }
}
