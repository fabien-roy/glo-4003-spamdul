package ca.ulaval.glo4003.funds.domain;

public class Bill {
  private final BillId id;
  private final BillType billType;
  private final String description;
  private final Money amountDue;
  private Money amountPaid;

  public Bill(BillId id, BillType billType, String description, Money amountDue) {
    this.id = id;
    this.billType = billType;
    this.description = description;
    this.amountDue = amountDue;
    this.amountPaid = Money.ZERO();
  }

  public BillId getId() {
    return id;
  }

  public BillType getBillType() {
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
}
