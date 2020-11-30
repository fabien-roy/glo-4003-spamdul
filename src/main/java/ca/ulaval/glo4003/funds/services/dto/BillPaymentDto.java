package ca.ulaval.glo4003.funds.services.dto;

public class BillPaymentDto {
  public double amountToPay;

  @Override
  public String toString() {
    return String.format("PayBillDto{amountToPay='%s'}", amountToPay);
  }
}
