package ca.ulaval.glo4003.initiatives.domain;

import ca.ulaval.glo4003.funds.domain.Money;
import java.util.ArrayList;
import java.util.List;

public class InitiativeAddedAllocatedAmountObservable {
  private List<InitiativeAddedAllocatedAmountObserver> observers = new ArrayList<>();

  public void register(InitiativeAddedAllocatedAmountObserver observer) {
    observers.add(observer);
  }

  public void notifyInitiativeAddedAllocatedAmount(
      Initiative initiative, Money addedAllocatedAmount) {
    for (InitiativeAddedAllocatedAmountObserver observer : observers) {
      observer.listenInitiativeAddedAllocatedAmount(initiative, addedAllocatedAmount);
    }
  }
}
