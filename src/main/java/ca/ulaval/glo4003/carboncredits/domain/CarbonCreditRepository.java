package ca.ulaval.glo4003.carboncredits.domain;

public interface CarbonCreditRepository {

  void add(CarbonCredit carbonCredit);

  CarbonCredit get();
}
