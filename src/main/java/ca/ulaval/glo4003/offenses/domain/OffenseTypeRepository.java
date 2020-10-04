package ca.ulaval.glo4003.offenses.domain;

import java.util.List;

public interface OffenseTypeRepository {

  void save(OffenseType offenseType);

  OffenseType findByCode(OffenseCodes offenseCodes);

  List<OffenseType> getAll();
}
