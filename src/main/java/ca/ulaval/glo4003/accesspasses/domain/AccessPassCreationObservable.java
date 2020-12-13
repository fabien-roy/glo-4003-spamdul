package ca.ulaval.glo4003.accesspasses.domain;

import java.util.ArrayList;
import java.util.List;

public class AccessPassCreationObservable {
  private final List<AccessPassCreationObserver> observers = new ArrayList<>();

  public void register(AccessPassCreationObserver observer) {
    observers.add(observer);
  }

  public void notifyAccessPassCreated(AccessPass accessPass) {
    for (AccessPassCreationObserver observer : observers) {
      observer.listenAccessPassCreated(accessPass);
    }
  }
}
