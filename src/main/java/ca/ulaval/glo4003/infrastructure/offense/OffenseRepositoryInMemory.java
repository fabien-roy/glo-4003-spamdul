package ca.ulaval.glo4003.infrastructure.offense;

import ca.ulaval.glo4003.domain.offense.Offense;
import ca.ulaval.glo4003.domain.offense.OffenseCodes;
import ca.ulaval.glo4003.domain.offense.OffenseRepository;
import ca.ulaval.glo4003.domain.offense.exceptions.OffenseNotFoundException;
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
