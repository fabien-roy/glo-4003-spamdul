package ca.ulaval.glo4003.carboncredits.domain;

public interface CarbonCreditRepository {

  void addCarbonCredit(CarbonCredit carbonCredit);

  CarbonCredit getAll();
}
