package ca.ulaval.glo4003.funds.services;

import static ca.ulaval.glo4003.funds.helpers.BillBuilder.aBill;
import static ca.ulaval.glo4003.funds.helpers.MoneyMother.createMoneyBelowAmount;
import static com.google.common.truth.Truth.assertThat;

import ca.ulaval.glo4003.funds.domain.Bill;
import ca.ulaval.glo4003.funds.domain.Money;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class BillProfitsCalculatorTest {

  private BillProfitsCalculator billProfitsCalculator;
  private Bill bill = aBill().build();
  private Bill anotherBill = aBill().build();
  private final List<Bill> bills = new ArrayList<>();
  private Money expectedAmount = Money.zero();

  @Before
  public void setUp() {
    billProfitsCalculator = new BillProfitsCalculator();
    bill.pay(createMoneyBelowAmount(bill.getAmountDue()));
    anotherBill.pay(createMoneyBelowAmount(anotherBill.getAmountDue()));

    bills.add(bill);
    bills.add(anotherBill);

    expectedAmount = expectedAmount.plus(bill.getAmountPaid());
    expectedAmount = expectedAmount.plus(anotherBill.getAmountPaid());
  }

  @Test
  public void whenCalculatingProfits_thenShouldReturnCorrectAmount() {
    Money total = billProfitsCalculator.calculate(bills);

    assertThat(total.toDouble()).isEqualTo(expectedAmount.toDouble());
  }
}
