package ca.ulaval.glo4003.funds.domain;

import ca.ulaval.glo4003.funds.exception.TooMuchMoney;

public class Bill {
  private final BillId id;
  private final BillTypes billTypes;
  private final String description;
  private Money amountDue;
  private Money amountPaid;

  public Bill(BillId id, BillTypes billTypes, String description, Money amountDue) {
    this.id = id;
    this.billTypes = billTypes;
    this.description = description;
    this.amountDue = amountDue;
    this.amountPaid = Money.ZERO();
  }

  public void pay(Money amountToPay) {
    if (amountToPay.toDouble() > amountDue.toDouble()) {
      throw new TooMuchMoney();
    }

    amountPaid = amountPaid.plus(amountToPay);
    amountDue = amountDue.minus(amountToPay);
  }

  public BillId getId() {
    return id;
  }

  public BillTypes getBillTypes() {
    return billTypes;
  }

  public String getDescription() {
    return description;
  }

  public Money getAmountDue() {
    return amountDue;
  }

  public Money getAmountPaid() {
    return amountPaid;
  }
}
