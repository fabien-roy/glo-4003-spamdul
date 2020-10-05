package ca.ulaval.glo4003.funds.services;

import ca.ulaval.glo4003.funds.domain.Bill;
import ca.ulaval.glo4003.parkings.domain.ParkingSticker;
import java.util.logging.Logger;

// TODO : Test BillService
public class BillService {
  private final Logger logger = Logger.getLogger(BillService.class.getName());

  public Bill createBill(ParkingSticker parkingSticker) {
    logger.info(String.format("Create bill for parking sticker %s", parkingSticker));

    return new Bill();
  }
}
