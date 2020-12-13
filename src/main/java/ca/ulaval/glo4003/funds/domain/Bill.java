package ca.ulaval.glo4003.funds.domain;

import ca.ulaval.glo4003.cars.domain.ConsumptionType;
import ca.ulaval.glo4003.funds.domain.exceptions.AmountDueExceededException;
import ca.ulaval.glo4003.times.domain.CustomDateTime;
import java.util.Optional;

public class Bill {
  private final BillId id;
  private final BillType billType;
  private final String description;
  private final Optional<ConsumptionType> consumptionType;
  private final CustomDateTime customDateTime;
  private Money amountDue;
  private Money amountPaid;

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
    this.amountPaid = Money.zero();
    this.customDateTime = customDateTime;
    this.consumptionType = Optional.empty();
  }

  public Bill(
      BillId id,
      BillType billType,
      String description,
      Money amountDue,
      CustomDateTime customDateTime,
      ConsumptionType consumptionType) {
    this.id = id;
    this.billType = billType;
    this.description = description;
    this.amountDue = amountDue;
    this.amountPaid = Money.zero();
    this.customDateTime = customDateTime;
    this.consumptionType = Optional.of(consumptionType);
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

  public CustomDateTime getCustomDateTime() {
    return customDateTime;
  }

  public ConsumptionType getConsumptionType() {
    return consumptionType.get();
  }

  public boolean isBillTypeEqual(BillType billType) {
    return this.billType.equals(billType);
  }
}
