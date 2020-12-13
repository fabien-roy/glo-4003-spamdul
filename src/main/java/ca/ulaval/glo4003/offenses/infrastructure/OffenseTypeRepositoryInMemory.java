package ca.ulaval.glo4003.offenses.infrastructure;

import ca.ulaval.glo4003.offenses.domain.OffenseCode;
import ca.ulaval.glo4003.offenses.domain.OffenseType;
import ca.ulaval.glo4003.offenses.domain.OffenseTypeRepository;
import ca.ulaval.glo4003.offenses.domain.exceptions.NotFoundOffenseTypeException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OffenseTypeRepositoryInMemory implements OffenseTypeRepository {

  private final Map<OffenseCode, OffenseType> offenses = new HashMap<>();

  @Override
  public void save(OffenseType offenseType) {
    offenses.put(offenseType.getCode(), offenseType);
  }

  @Override
  public OffenseType findByCode(OffenseCode offenseCode) {
    OffenseType foundOffenseType = offenses.get(offenseCode);

    if (foundOffenseType == null) throw new NotFoundOffenseTypeException(offenseCode);

    return foundOffenseType;
  }

  @Override
  public List<OffenseType> getAll() {
    return new ArrayList<>(offenses.values());
  }
}
