package ca.ulaval.glo4003.funds.domain;

import ca.ulaval.glo4003.parkings.domain.ParkingStickerCode;
import ca.ulaval.glo4003.parkings.domain.ReceptionMethods;

public class BillFactory {

  private static final String PARKING_STICKER_BILL_DESCRIPTION =
      "Purchase of a parking sticker with code %s";

  private final BillIdGenerator billIdGenerator;

  public BillFactory(BillIdGenerator billIdGenerator) {
    this.billIdGenerator = billIdGenerator;
  }

  // TODO : Test BillFactory.createForParkingSticker
  public Bill createForParkingSticker(
      Money feeForPeriod, ParkingStickerCode parkingStickerCode, ReceptionMethods receptionMethod) {
    BillId id = billIdGenerator.generate();
    String description =
        String.format(PARKING_STICKER_BILL_DESCRIPTION, parkingStickerCode.toString());

    Money amount = new Money(feeForPeriod);

    if (receptionMethod.equals(ReceptionMethods.POSTAL)) {
      amount = amount.plus(Money.fromDouble(5));
    }

    return new Bill(id, description, amount);
  }
}
