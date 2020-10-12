package ca.ulaval.glo4003.funds.services;

import ca.ulaval.glo4003.access.domain.AccessPassCode;
import ca.ulaval.glo4003.funds.api.dto.BillDto;
import ca.ulaval.glo4003.funds.assemblers.BillsAssembler;
import ca.ulaval.glo4003.funds.domain.*;
import ca.ulaval.glo4003.offenses.domain.OffenseCode;
import ca.ulaval.glo4003.parkings.domain.ParkingArea;
import ca.ulaval.glo4003.parkings.domain.ParkingPeriods;
import ca.ulaval.glo4003.parkings.domain.ParkingSticker;
import java.util.List;
import java.util.logging.Logger;

public class BillService {
  private final Logger logger = Logger.getLogger(BillService.class.getName());
  private final BillFactory billFactory;
  private final BillRepository billRepository;
  private final BillsAssembler billsAssembler;

  public BillService(
      BillFactory billFactory, BillRepository billRepository, BillsAssembler billsAssembler) {
    this.billFactory = billFactory;
    this.billRepository = billRepository;
    this.billsAssembler = billsAssembler;
  }

  public BillId addBillForParkingSticker(ParkingSticker parkingSticker, ParkingArea parkingArea) {
    logger.info(String.format("Create bill for parking sticker %s", parkingSticker));

    Money feeForPeriod =
        parkingArea.getFeeForPeriod(ParkingPeriods.ONE_DAY); // TODO : Will this always be one day?
    Bill bill =
        billFactory.createForParkingSticker(
            feeForPeriod, parkingSticker.getCode(), parkingSticker.getReceptionMethod());

    billRepository.save(bill);

    return bill.getId();
  }

  public BillId addBillForAccessCode(Money fee, AccessPassCode accessPassCode) {
    Bill bill = billFactory.createForAccessPass(fee, accessPassCode);

    billRepository.save(bill);

    return bill.getId();
  }

  public BillId addBillOffense(Money fee, OffenseCode offenseCode) {
    Bill bill = billFactory.createForOffense(fee, offenseCode);

    billRepository.save(bill);

    return bill.getId();
  }

  public BillDto payBill(BillId billId, Money amountToPay) {
    Bill bill = getBill(billId);
    bill.pay(amountToPay);
    billRepository.updateBill(bill);

    return billsAssembler.assemble(bill);
  }

  public List<Bill> getBillsByIds(List<BillId> billIds) {
    return billRepository.getBills(billIds);
  }

  public Bill getBill(BillId billId) {
    return billRepository.getBill(billId);
  }
}
