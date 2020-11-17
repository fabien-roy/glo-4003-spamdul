package ca.ulaval.glo4003.initiatives.domain;

import ca.ulaval.glo4003.funds.domain.Money;

public interface InitiativeAddedAllocatedAmountObserver {

  void listenInitiativeAddedAllocatedAmount(Initiative initiative, Money addedAllocatedAmount);
}
