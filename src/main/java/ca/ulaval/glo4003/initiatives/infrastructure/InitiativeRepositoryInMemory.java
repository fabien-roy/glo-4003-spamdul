package ca.ulaval.glo4003.initiatives.infrastructure;

import ca.ulaval.glo4003.initiatives.domain.Initiative;
import ca.ulaval.glo4003.initiatives.domain.InitiativeCode;
import ca.ulaval.glo4003.initiatives.domain.InitiativeRepository;
import ca.ulaval.glo4003.initiatives.exception.InitiativeNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InitiativeRepositoryInMemory implements InitiativeRepository {
  private final Map<InitiativeCode, Initiative> initiatives = new HashMap<>();

  @Override
  public InitiativeCode save(Initiative initiative) {
    initiatives.put(initiative.getCode(), initiative);
    return initiative.getCode();
  }

  @Override
  public List<Initiative> getAll() {
    return new ArrayList<>(initiatives.values());
  }

  @Override
  public Initiative get(InitiativeCode initiativeCode) {
    Initiative initiative = initiatives.get(initiativeCode);

    if (initiative == null) {
      throw new InitiativeNotFoundException();
    }

    return initiative;
  }

  @Override
  public void update(Initiative initiative) {
    get(initiative.getCode());

    initiatives.put(initiative.getCode(), initiative);
  }
}
