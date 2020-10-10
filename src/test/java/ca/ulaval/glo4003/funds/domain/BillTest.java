package ca.ulaval.glo4003.funds.domain;

import static ca.ulaval.glo4003.funds.helpers.BillMother.createBillId;
import static ca.ulaval.glo4003.funds.helpers.BillMother.createDescription;
import static ca.ulaval.glo4003.funds.helpers.MoneyMother.createMoney;

import com.google.common.truth.Truth;
import org.junit.Test;

public class BillTest {

  private final BillId billId = createBillId();
  private final String description = createDescription();
  private final Money amountDue = createMoney();

  @Test
  public void whenConstructing_setAmountPaidToZero() {
    Money expectedAmountPaid = Money.ZERO();

    Bill bill = new Bill(billId, description, amountDue);

    Truth.assertThat(bill.getAmountPaid()).isEqualTo(expectedAmountPaid);
  }
}
