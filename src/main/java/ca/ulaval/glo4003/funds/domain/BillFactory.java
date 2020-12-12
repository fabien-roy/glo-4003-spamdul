package ca.ulaval.glo4003.funds.domain;

import ca.ulaval.glo4003.accesspasses.domain.AccessPassCode;
import ca.ulaval.glo4003.cars.domain.ConsumptionType;
import ca.ulaval.glo4003.offenses.domain.OffenseCode;
import ca.ulaval.glo4003.parkings.domain.ParkingStickerCode;
import ca.ulaval.glo4003.parkings.domain.ReceptionMethod;
import ca.ulaval.glo4003.times.domain.CustomDateTime;

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
      Money feeForPeriod, ParkingStickerCode parkingStickerCode, ReceptionMethod receptionMethod) {
    BillId id = generateBillId();
    String description =
        String.format(PARKING_STICKER_BILL_DESCRIPTION, parkingStickerCode.toString());

    Money amount = new Money(feeForPeriod);

    if (receptionMethod.equals(ReceptionMethod.POSTAL)) {
      amount = amount.plus(Money.fromDouble(5));
    }

    BillType billType = BillType.PARKING_STICKER;

    return new Bill(id, billType, description, amount, CustomDateTime.now());
  }

  public Bill createForAccessPass(
      Money feeForPeriod,
      AccessPassCode accessPassCode,
      ConsumptionType consumptionType,
      ReceptionMethod receptionMethod) {
    BillId id = generateBillId();
    String description = String.format(ACCESS_PASS_BILL_DESCRIPTION, accessPassCode.toString());

    Money amount = new Money(feeForPeriod);

    if (receptionMethod != null && receptionMethod.equals(ReceptionMethod.POSTAL)) {
      amount = amount.plus(Money.fromDouble(5));
    }

    BillType billType = BillType.ACCESS_PASS;

    return new Bill(id, billType, description, amount, CustomDateTime.now(), consumptionType);
  }

  public Bill createForOffense(Money fee, OffenseCode offenseCode) {
    BillId id = generateBillId();
    String description = String.format(OFFENSE_BILL_DESCRIPTION, offenseCode.toString());

    BillType billType = BillType.OFFENSE;

    return new Bill(id, billType, description, fee, CustomDateTime.now());
  }

  private BillId generateBillId() {
    return billIdGenerator.generate();
  }
}
