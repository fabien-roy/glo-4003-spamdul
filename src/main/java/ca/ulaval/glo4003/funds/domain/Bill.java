package ca.ulaval.glo4003.funds.domain;

import ca.ulaval.glo4003.funds.exception.AmountDueExceededException;

public class Bill {
  private final BillId id;
  private final BillType billType;
  private final String description;
  private Money amountDue;
  private Money amountPaid;

  public Bill(BillId id, BillType billType, String description, Money amountDue) {
    this.id = id;
    this.billType = billType;
    this.description = description;
    this.amountDue = amountDue;
    this.amountPaid = Money.zero();
  }

  public void pay(Money amountToPay) {
    if (amountToPay.toDouble() > amountDue.toDouble()) {
      throw new AmountDueExceededException();
    }

    amountPaid = amountPaid.plus(amountToPay);
    amountDue = amountDue.minus(amountToPay);
  }

  public BillId getId() {
    return id;
  }

  public BillType getBillTypes() {
    return billType;
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

  public boolean isBillTypeEqual(BillType billType) {
    return this.billType.equals(billType);
  }
}
