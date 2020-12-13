package ca.ulaval.glo4003.parkings.domain;

import java.util.ArrayList;
import java.util.List;

public class ParkingStickerCreationObservable {
  private final List<ParkingStickerCreationObserver> observers = new ArrayList<>();

  public void register(ParkingStickerCreationObserver observer) {
    observers.add(observer);
  }

  public void notifyParkingStickerCreated(ParkingSticker parkingSticker) {
    for (ParkingStickerCreationObserver observer : observers) {
      observer.listenParkingStickerCreated(parkingSticker);
    }
  }
}
