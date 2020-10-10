package ca.ulaval.glo4003.locations.domain;

import ca.ulaval.glo4003.parkings.domain.ParkingStickerCreationObserver;

public interface PostalSender extends ParkingStickerCreationObserver {
  void sendPostal(PostalCode postalCode, String message);
}
