package ca.ulaval.glo4003.funds.domain;

public class Bill {
  private final BillId id;
  private final BillTypes billTypes;
  private final String description;
  private final Money amountDue;
  private Money amountPaid;

  public Bill(BillId id, BillTypes billTypes, String description, Money amountDue) {
    this.id = id;
    this.billTypes = billTypes;
    this.description = description;
    this.amountDue = amountDue;
    this.amountPaid = Money.ZERO();
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
