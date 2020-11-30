package ca.ulaval.glo4003.carboncredits.services.dto;

public class CarbonCreditDto {
  public double carbonCredits;

  @Override
  public String toString() {
    return String.format("CarbonCreditDto{carbonCredits='%s'}", carbonCredits);
  }
}
