package ca.ulaval.glo4003.communications.console;

import ca.ulaval.glo4003.accesspasses.domain.AccessPass;
import ca.ulaval.glo4003.communications.domain.ReceptionMethod;
import ca.ulaval.glo4003.communications.domain.SspSender;
import ca.ulaval.glo4003.parkings.domain.ParkingSticker;

public class SspSenderSystemPrint implements SspSender {
  private static final String PARKING_STICKER_CREATION_MESSAGE =
      "Your SPAMD-UL parking sticker code is %s";
  private static final String ACCESS_PASS_CREATION_MESSAGE = "Your SPAMD-UL access pass code is %s";

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

  @Override
  public void listenAccessPassCreated(AccessPass accessPass) {
    if (accessPass.getReceptionMethod().equals(ReceptionMethod.SSP)) {
      sendSSP(String.format(ACCESS_PASS_CREATION_MESSAGE, accessPass.getCode().toString()));
    }
  }
}
