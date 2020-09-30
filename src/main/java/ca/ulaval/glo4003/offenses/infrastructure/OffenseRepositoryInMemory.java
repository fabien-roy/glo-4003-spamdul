package ca.ulaval.glo4003.offenses.infrastructure;

import ca.ulaval.glo4003.offenses.domain.Offense;
import ca.ulaval.glo4003.offenses.domain.OffenseCodes;
import ca.ulaval.glo4003.offenses.domain.OffenseRepository;
import ca.ulaval.glo4003.offenses.exceptions.OffenseNotFoundException;
import java.util.HashMap;
import java.util.Map;

public class OffenseRepositoryInMemory implements OffenseRepository {

  private final Map<OffenseCodes, Offense> offenses = new HashMap<>();

  @Override
  public void save(Offense offense) {
    offenses.put(offense.getCode(), offense);
  }

  @Override
  public Offense findByCode(OffenseCodes offenseCodes) {
    Offense foundOffense = offenses.get(offenseCodes);

    if (foundOffense == null) throw new OffenseNotFoundException();

    return foundOffense;
  }
}
