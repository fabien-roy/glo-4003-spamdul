package ca.ulaval.glo4003.funds.domain;

public class Bill {
  private final BillId id;
  private final String description;
  private final Money amountDue;
  private Money amountPaid;

  public Bill(BillId id, String description, Money amountDue) {
    this.id = id;
    this.description = description;
    this.amountDue = amountDue;
    this.amountPaid = Money.ZERO();
  }
}
