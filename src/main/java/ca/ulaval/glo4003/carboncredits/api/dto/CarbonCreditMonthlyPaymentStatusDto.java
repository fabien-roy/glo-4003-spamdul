package ca.ulaval.glo4003.carboncredits.api.dto;

public class CarbonCreditMonthlyPaymentStatusDto {
  public String monthlyPaymentStatus;

  @Override
  public String toString() {
    return String.format("CarbonCredits{monthlyPaymentStatus='%s'}", monthlyPaymentStatus);
  }
}
