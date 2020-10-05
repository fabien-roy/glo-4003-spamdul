package ca.ulaval.glo4003.funds.domain;

public class Bill {
  private final Money amountDue;
  private Money amountPaid;

  public Bill(Money amountDue) {
    this.amountDue = amountDue;
    this.amountPaid = Money.ZERO();
  }
}
