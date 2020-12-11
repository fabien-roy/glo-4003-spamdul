package ca.ulaval.glo4003.initiatives.domain;

import ca.ulaval.glo4003.funds.domain.Money;
import java.util.List;

public interface InitiativeRepository {
  InitiativeCode save(Initiative initiative);

  List<Initiative> getAll();

  Initiative get(InitiativeCode initiativeCode);

  void update(Initiative initiative);

  void removeAvailableMoney(Money money);

  Money getAvailableMoney();
}
