package ca.ulaval.glo4003.funds.assemblers;

import ca.ulaval.glo4003.funds.api.dto.BillPaymentDto;
import ca.ulaval.glo4003.funds.domain.Money;

public class BillPaymentAssembler {
  private final MoneyAssembler moneyAssembler;

  public BillPaymentAssembler(MoneyAssembler moneyAssembler) {
    this.moneyAssembler = moneyAssembler;
  }

  public Money assemble(BillPaymentDto billPaymentDto) {
    return moneyAssembler.assemble(billPaymentDto.amountToPay);
  }
}
