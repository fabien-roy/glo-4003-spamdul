package ca.ulaval.glo4003.funds.assemblers;

import ca.ulaval.glo4003.funds.api.dto.PayBillDto;
import ca.ulaval.glo4003.funds.domain.Money;

public class PayBillAssembler {
  public Money assemble(PayBillDto payBillDto) {
    return new Money(payBillDto.amountPaid);
  }
}
