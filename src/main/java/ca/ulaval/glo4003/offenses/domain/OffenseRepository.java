package ca.ulaval.glo4003.offenses.domain;

public interface OffenseRepository {

  void save(Offense offense);

  Offense findByCode(OffenseCodes offenseCodes);
}
