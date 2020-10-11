package ca.ulaval.glo4003.funds.api.dto;

public class PayBillDto {
  public Double amountToPay;

  @Override
  public String toString() {
    return String.format("PayBillDto{amountToPay='%s'}", amountToPay);
  }
}
