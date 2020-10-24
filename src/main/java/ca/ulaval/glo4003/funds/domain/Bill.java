package ca.ulaval.glo4003.funds.domain;

import ca.ulaval.glo4003.funds.exception.AmountDueExceededException;
import ca.ulaval.glo4003.times.domain.CustomDateTime;

public class Bill {
  private final BillId id;
  private final BillType billType;
  private final String description;
  private Money amountDue;
  private Money amountPaid;
  private CustomDateTime customDateTime;

  public Bill(
      BillId id,
      BillType billType,
      String description,
      Money amountDue,
      CustomDateTime customDateTime) {
    this.id = id;
    this.billType = billType;
    this.description = description;
    this.amountDue = amountDue;
    this.amountPaid = Money.ZERO();
    this.customDateTime = customDateTime;
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

  public CustomDateTime getCustomDateTime() {
    return customDateTime;
  }
}
