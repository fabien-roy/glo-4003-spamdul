package ca.ulaval.glo4003.communications.domain;

import ca.ulaval.glo4003.accesspasses.domain.AccessPassCreationObserver;
import ca.ulaval.glo4003.parkings.domain.ParkingStickerCreationObserver;

public interface EmailSender extends ParkingStickerCreationObserver, AccessPassCreationObserver {

  void sendEmail(String emailAddress, String emailSubject, String emailContent);
}
