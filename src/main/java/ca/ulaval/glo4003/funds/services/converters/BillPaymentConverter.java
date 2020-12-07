package ca.ulaval.glo4003.funds.services.converters;

import ca.ulaval.glo4003.funds.domain.Money;
import ca.ulaval.glo4003.funds.services.dto.BillPaymentDto;

public class BillPaymentConverter {
  private final MoneyConverter moneyConverter;

  public BillPaymentConverter() {
    this.moneyConverter = new MoneyConverter();
  }

  // TODO Me dire pourquoi y'a cet intermédiaire-là
  public Money convert(BillPaymentDto billPaymentDto) {
    return moneyConverter.convert(billPaymentDto.amountToPay);
  }
}
