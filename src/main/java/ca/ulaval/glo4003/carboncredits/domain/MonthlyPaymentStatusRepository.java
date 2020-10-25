package ca.ulaval.glo4003.carboncredits.domain;

public interface MonthlyPaymentStatusRepository {
  void save(MonthlyPaymentStatus monthlyPaymentStatus);

  MonthlyPaymentStatus get();
}
