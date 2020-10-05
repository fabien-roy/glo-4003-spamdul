package ca.ulaval.glo4003.funds.services;

import ca.ulaval.glo4003.funds.domain.Bill;
import ca.ulaval.glo4003.funds.domain.BillFactory;
import ca.ulaval.glo4003.funds.domain.Money;
import ca.ulaval.glo4003.parkings.domain.ParkingArea;
import ca.ulaval.glo4003.parkings.domain.ParkingPeriods;
import ca.ulaval.glo4003.parkings.domain.ParkingSticker;
import java.util.logging.Logger;

public class BillService {
  private final Logger logger = Logger.getLogger(BillService.class.getName());
  private final BillFactory billFactory;

  public BillService(BillFactory billFactory) {
    this.billFactory = billFactory;
  }

  public Bill createBillForParkingSticker(ParkingSticker parkingSticker, ParkingArea parkingArea) {
    logger.info(String.format("Create bill for parking sticker %s", parkingSticker));

    Money feeForPeriod =
        parkingArea.getFeeForPeriod(ParkingPeriods.ONE_DAY); // TODO : Will this always be one day?

    return billFactory.createForParkingSticker(
        feeForPeriod, parkingSticker.getCode(), parkingSticker.getReceptionMethod());
  }
}
