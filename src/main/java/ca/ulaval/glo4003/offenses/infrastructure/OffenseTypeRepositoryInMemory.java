package ca.ulaval.glo4003.offenses.infrastructure;

import ca.ulaval.glo4003.offenses.domain.Offense;
import ca.ulaval.glo4003.offenses.domain.OffenseCodes;
import ca.ulaval.glo4003.offenses.domain.OffenseTypeRepository;
import ca.ulaval.glo4003.offenses.exceptions.OffenseTypeNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OffenseTypeRepositoryInMemory implements OffenseTypeRepository {

  private final Map<OffenseCodes, Offense> offenses = new HashMap<>();

  @Override
  public void save(Offense offense) {
    offenses.put(offense.getCode(), offense);
  }

  @Override
  public Offense findByCode(OffenseCodes offenseCodes) {
    Offense foundOffense = offenses.get(offenseCodes);

    if (foundOffense == null) throw new OffenseTypeNotFoundException();

    return foundOffense;
  }

  @Override
  public List<Offense> getAll() {
    return new ArrayList<>(offenses.values());
  }
}
