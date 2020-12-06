package ca.ulaval.glo4003.funds.helpers;

import static ca.ulaval.glo4003.funds.helpers.MoneyMother.createMoney;

import ca.ulaval.glo4003.funds.services.dto.BillPaymentDto;

public class BillPaymentDtoBuilder {
  public double amountToPay = createMoney().toDouble();

  public static BillPaymentDtoBuilder aBillPaymentDto() {
    return new BillPaymentDtoBuilder();
  }

  public BillPaymentDto build() {
    BillPaymentDto billPaymentDto = new BillPaymentDto();
    billPaymentDto.amountToPay = amountToPay;
    return billPaymentDto;
  }
}
