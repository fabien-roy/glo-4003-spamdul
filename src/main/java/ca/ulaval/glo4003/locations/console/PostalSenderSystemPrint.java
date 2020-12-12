package ca.ulaval.glo4003.locations.console;

import ca.ulaval.glo4003.accesspasses.domain.AccessPass;
import ca.ulaval.glo4003.locations.domain.PostalCode;
import ca.ulaval.glo4003.locations.domain.PostalSender;
import ca.ulaval.glo4003.parkings.domain.ParkingSticker;
import ca.ulaval.glo4003.parkings.domain.ReceptionMethod;

public class PostalSenderSystemPrint implements PostalSender {
  private static final String POSTAL_MESSAGE = "Sending to postal code %s :";
  private static final String PARKING_STICKER_CREATION_MESSAGE =
      "Your SPAMD-UL parking sticker code is %s";
  private static final String ACCESS_PASS_CREATION_MESSAGE = "Your SPAMD-UL access pass code is %s";

  @Override
  public void sendPostal(PostalCode postalCode, String message) {
    System.out.println(String.format(POSTAL_MESSAGE, postalCode.toString()));
    System.out.println(message);
  }

  @Override
  public void listenParkingStickerCreated(ParkingSticker parkingSticker) {
    if (parkingSticker.getReceptionMethod().equals(ReceptionMethod.POSTAL)) {
      sendPostal(
          parkingSticker.getPostalCode(),
          String.format(PARKING_STICKER_CREATION_MESSAGE, parkingSticker.getCode().toString()));
    }
  }

  @Override
  public void listenAccessPassCreated(AccessPass accessPass) {
    if (accessPass.getReceptionMethod().equals(ReceptionMethod.POSTAL)) {
      sendPostal(
          accessPass.getPostalCode(),
          String.format(ACCESS_PASS_CREATION_MESSAGE, accessPass.getCode().toString()));
    }
  }
}
