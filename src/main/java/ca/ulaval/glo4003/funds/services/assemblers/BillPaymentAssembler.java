package ca.ulaval.glo4003.funds.services.assemblers;

import ca.ulaval.glo4003.funds.domain.Money;
import ca.ulaval.glo4003.funds.services.dto.BillPaymentDto;

public class BillPaymentAssembler {
  private final MoneyAssembler moneyAssembler;

  public BillPaymentAssembler(MoneyAssembler moneyAssembler) {
    this.moneyAssembler = moneyAssembler;
  }

  public Money assemble(BillPaymentDto billPaymentDto) {
    return moneyAssembler.assemble(billPaymentDto.amountToPay);
  }
}
