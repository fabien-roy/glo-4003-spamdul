package ca.ulaval.glo4003.initiative.domain;

import java.util.List;

public interface InitiativeRepository {
  InitiativeCode save(Initiative initiative);

  List<Initiative> getAllInitiatives();

  Initiative get(InitiativeCode initiativeCode);

  void update(Initiative initiative);
}
