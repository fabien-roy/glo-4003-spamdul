package ca.ulaval.glo4003.funds.domain;

import static ca.ulaval.glo4003.funds.helpers.BillBuilder.aBill;
import static ca.ulaval.glo4003.funds.helpers.MoneyMother.createMoneyBelowAmount;
import static com.google.common.truth.Truth.assertThat;

import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class BillPriceCalculatorTest {

  private BillPriceCalculator billPriceCalculator;
  private Bill bill = aBill().build();
  private Bill anotherBill = aBill().build();
  private final List<Bill> bills = new ArrayList<>();
  private Money expectedAmount = Money.zero();

  @Before
  public void setUp() {
    billPriceCalculator = new BillPriceCalculator();
    anotherBill.pay(createMoneyBelowAmount(anotherBill.getAmountDue()));

    bills.add(bill);
    bills.add(anotherBill);
  }

  @Test
  public void whenCalculatingPaidProfits_thenShouldReturnCorrectAmount() {
    expectedAmount = expectedAmount.plus(anotherBill.getAmountPaid());

    Money profits = billPriceCalculator.calculatePaidPrice(bills);

    assertThat(profits.toDouble()).isEqualTo(expectedAmount.toDouble());
  }

  @Test
  public void whenCalculatingTotalProfits_thenShouldReturnCorrectAmount() {
    expectedAmount = expectedAmount.plus(anotherBill.getAmountPaid());
    expectedAmount = expectedAmount.plus(anotherBill.getAmountDue());
    expectedAmount = expectedAmount.plus(bill.getAmountDue());

    Money profits = billPriceCalculator.calculateTotalPrice(bills);

    assertThat(profits.toDouble()).isEqualTo(expectedAmount.toDouble());
  }
}
