package ca.ulaval.glo4003.initiatives.infrastructure;

import ca.ulaval.glo4003.funds.domain.Money;
import ca.ulaval.glo4003.funds.domain.exceptions.InsufficientAvailableMoneyException;
import ca.ulaval.glo4003.initiatives.domain.Initiative;
import ca.ulaval.glo4003.initiatives.domain.InitiativeCode;
import ca.ulaval.glo4003.initiatives.domain.InitiativeFundCollector;
import ca.ulaval.glo4003.initiatives.domain.InitiativeRepository;
import ca.ulaval.glo4003.initiatives.domain.exceptions.InitiativeNotFoundException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InitiativeRepositoryInMemory implements InitiativeRepository, InitiativeFundCollector {
  private final Map<InitiativeCode, Initiative> initiatives = new HashMap<>();
  private Money availableMoney = Money.zero();

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

  @Override
  public void addAvailableMoney(Money money) {
    this.availableMoney = availableMoney.plus(money);
  }

  @Override
  public void removeAvailableMoney(Money money) {
    if (availableMoney.isLessThan(money)) throw new InsufficientAvailableMoneyException();

    this.availableMoney = availableMoney.minus(money);
  }

  @Override
  public Money getAvailableMoney() {
    return availableMoney;
  }
}
