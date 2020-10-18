package ca.ulaval.glo4003.offenses.console;

import ca.ulaval.glo4003.offenses.domain.OffenseNotifier;
import ca.ulaval.glo4003.offenses.domain.OffenseType;

public class OffenseNotifierSystemPrint implements OffenseNotifier {
  private static final String OFFENSE_WITHOUT_PARKING_STICKER_MESSAGE =
      "An offense was validated for without a valid parking sticker. The associated offense type is"
          + "%s, '%s', with an amount of %s.";

  @Override
  public void notifyOffenseWithoutParkingSticker(OffenseType offenseType) {
    String message =
        String.format(
            OFFENSE_WITHOUT_PARKING_STICKER_MESSAGE,
            offenseType.getCode().toString(),
            offenseType.getDescription(),
            offenseType.getAmount().toString());
    System.out.println(message);
  }
}
