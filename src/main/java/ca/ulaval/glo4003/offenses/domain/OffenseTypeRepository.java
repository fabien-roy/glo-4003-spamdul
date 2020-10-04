package ca.ulaval.glo4003.offenses.domain;

import java.util.List;

public interface OffenseTypeRepository {

  void save(Offense offense);

  Offense findByCode(OffenseCodes offenseCodes);

  List<Offense> getAll();
}
