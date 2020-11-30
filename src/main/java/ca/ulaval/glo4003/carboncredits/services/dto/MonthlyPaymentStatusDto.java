package ca.ulaval.glo4003.carboncredits.services.dto;

public class MonthlyPaymentStatusDto {
  public String monthlyPaymentStatus;

  @Override
  public String toString() {
    return String.format(
        "CarbonCreditMonthlyPaymentStatusDto{monthlyPaymentStatus='%s'}", monthlyPaymentStatus);
  }
}
