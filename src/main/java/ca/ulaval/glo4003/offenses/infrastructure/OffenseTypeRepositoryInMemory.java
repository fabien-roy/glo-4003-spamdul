package ca.ulaval.glo4003.offenses.infrastructure;

import ca.ulaval.glo4003.offenses.domain.OffenseCodes;
import ca.ulaval.glo4003.offenses.domain.OffenseType;
import ca.ulaval.glo4003.offenses.domain.OffenseTypeRepository;
import ca.ulaval.glo4003.offenses.exceptions.OffenseTypeNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OffenseTypeRepositoryInMemory implements OffenseTypeRepository {

  private final Map<OffenseCodes, OffenseType> offenses = new HashMap<>();

  @Override
  public void save(OffenseType offenseType) {
    offenses.put(offenseType.getCode(), offenseType);
  }

  @Override
  public OffenseType findByCode(OffenseCodes offenseCodes) {
    OffenseType foundOffenseType = offenses.get(offenseCodes);

    if (foundOffenseType == null) throw new OffenseTypeNotFoundException();

    return foundOffenseType;
  }

  @Override
  public List<OffenseType> getAll() {
    return new ArrayList<>(offenses.values());
  }
}
