package ca.ulaval.glo4003.locations.domain;

import ca.ulaval.glo4003.parkings.domain.ParkingStickerCreationObserver;

public interface SspSender extends ParkingStickerCreationObserver {
  void sendSSP(String message);
}
