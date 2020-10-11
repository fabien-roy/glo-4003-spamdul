package ca.ulaval.glo4003.funds.api.dto;

public class PayBillDto {
  public Double amountPaid;

  @Override
  public String toString() {
    return String.format("PayBillDto{amountPaid='%s'}", amountPaid);
  }
}
