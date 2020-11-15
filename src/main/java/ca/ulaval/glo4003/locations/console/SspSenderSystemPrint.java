package ca.ulaval.glo4003.locations.console;

import ca.ulaval.glo4003.locations.domain.SspSender;
import ca.ulaval.glo4003.parkings.domain.ParkingSticker;
import ca.ulaval.glo4003.parkings.domain.ReceptionMethod;

public class SspSenderSystemPrint implements SspSender {
  private static final String PARKING_STICKER_CREATION_MESSAGE =
      "Your SPAMD-UL parking sticker code is %s";

  @Override
  public void sendSSP(String message) {
    System.out.println(message);
  }

  @Override
  public void listenParkingStickerCreated(ParkingSticker parkingSticker) {
    if (parkingSticker.getReceptionMethod().equals(ReceptionMethod.SSP)) {
      sendSSP(String.format(PARKING_STICKER_CREATION_MESSAGE, parkingSticker.getCode().toString()));
    }
  }
}
