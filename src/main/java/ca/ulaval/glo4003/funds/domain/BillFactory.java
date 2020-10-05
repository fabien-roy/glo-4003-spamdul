package ca.ulaval.glo4003.funds.domain;

import ca.ulaval.glo4003.parkings.domain.ReceptionMethods;

public class BillFactory {

  // TODO : Test BillFactory.createForParkingSticker
  public Bill createForParkingSticker(Money feeForPeriod, ReceptionMethods receptionMethod) {
    // TODO : Use BillIdGenerator to make a new one
    // TODO : Add description concerning parking stickers
    // TODO : Add a fee for reception method
    return new Bill(Money.ZERO());
  }
}
