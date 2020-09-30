package ca.ulaval.glo4003.domain.offense;

public interface OffenseRepository {

  void save(Offense offense);

  Offense findByCode(OffenseCodes offenseCodes);
}
