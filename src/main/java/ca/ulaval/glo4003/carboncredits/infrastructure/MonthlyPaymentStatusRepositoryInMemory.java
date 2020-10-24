package ca.ulaval.glo4003.carboncredits.infrastructure;

import ca.ulaval.glo4003.carboncredits.domain.MonthlyPaymentStatus;
import ca.ulaval.glo4003.carboncredits.domain.MonthlyPaymentStatusRepository;

public class MonthlyPaymentStatusRepositoryInMemory implements MonthlyPaymentStatusRepository {
  private MonthlyPaymentStatus monthlyPaymentStatus;

  @Override
  public void save(MonthlyPaymentStatus newMonthlyPaymentStatus) {
    this.monthlyPaymentStatus = newMonthlyPaymentStatus;
  }
}
