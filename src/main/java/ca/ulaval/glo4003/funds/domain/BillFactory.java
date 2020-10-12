package ca.ulaval.glo4003.funds.domain;

import ca.ulaval.glo4003.access.domain.AccessPassCode;
import ca.ulaval.glo4003.offenses.domain.OffenseCode;
import ca.ulaval.glo4003.parkings.domain.ParkingStickerCode;
import ca.ulaval.glo4003.parkings.domain.ReceptionMethods;

public class BillFactory {

  private static final String PARKING_STICKER_BILL_DESCRIPTION =
      "Purchase of a parking sticker with code %s";
  private static final String ACCESS_PASS_BILL_DESCRIPTION =
      "Purchase of an access pass with code %s";
  private static final String OFFENSE_BILL_DESCRIPTION = "Offense with offense code %s";

  private final BillIdGenerator billIdGenerator;

  public BillFactory(BillIdGenerator billIdGenerator) {
    this.billIdGenerator = billIdGenerator;
  }

  public Bill createForParkingSticker(
      Money feeForPeriod, ParkingStickerCode parkingStickerCode, ReceptionMethods receptionMethod) {
    BillId id = generateBillId();
    String description =
        String.format(PARKING_STICKER_BILL_DESCRIPTION, parkingStickerCode.toString());

    Money amount = new Money(feeForPeriod);

    if (receptionMethod.equals(ReceptionMethods.POSTAL)) {
      amount = amount.plus(Money.fromDouble(5));
    }

    BillTypes billTypes = BillTypes.PARKING_STICKER;

    return new Bill(id, billTypes, description, amount);
  }

  public Bill createForAccessPass(Money feeForPeriod, AccessPassCode accessPassCode) {
    BillId id = generateBillId();
    String description = String.format(ACCESS_PASS_BILL_DESCRIPTION, accessPassCode.toString());

    BillTypes billTypes = BillTypes.ACCESS_PASS;

    return new Bill(id, billTypes, description, feeForPeriod);
  }

  public Bill createForOffense(Money fee, OffenseCode offenseCode) {
    BillId id = generateBillId();
    String description = String.format(OFFENSE_BILL_DESCRIPTION, offenseCode.toString());

    BillTypes billTypes = BillTypes.OFFENSE;

    return new Bill(id, billTypes, description, fee);
  }

  private BillId generateBillId() {
    return billIdGenerator.generate();
  }
}