package ca.ulaval.glo4003.funds.domain;

import static ca.ulaval.glo4003.funds.helpers.BillMother.*;
import static ca.ulaval.glo4003.funds.helpers.MoneyMother.createMoney;

import ca.ulaval.glo4003.funds.exception.AmountDueExceededException;
import com.google.common.truth.Truth;
import org.junit.Test;

public class BillTest {

  private final BillId billId = createBillId();
  private final String description = createDescription();
  private final Money amountDue = createMoney();
  private final BillType billType = createBillType();

  @Test
  public void whenConstructing_setAmountPaidToZero() {
    Money expectedAmountPaid = Money.ZERO();

    Bill bill = new Bill(billId, billType, description, amountDue);

    Truth.assertThat(bill.getAmountPaid()).isEqualTo(expectedAmountPaid);
  }

  @Test
  public void whenPaying_thenAmountIsPaid() {
    Bill bill = new Bill(billId, billType, description, amountDue);

    bill.pay(amountDue);

    Truth.assertThat(bill.getAmountDue()).isEqualTo(Money.ZERO());
    Truth.assertThat(bill.getAmountPaid()).isEqualTo(amountDue);
  }

  @Test(expected = AmountDueExceededException.class)
  public void whenPayingMoreThanAmountDue_thenThrowTooMuchMoney() {
    Bill bill = new Bill(billId, billType, description, amountDue);

    bill.pay(amountDue.plus(new Money(10)));
  }
}
