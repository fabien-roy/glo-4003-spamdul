package ca.ulaval.glo4003.funds.services;

import ca.ulaval.glo4003.access.domain.AccessPassCode;
import ca.ulaval.glo4003.funds.api.dto.BillDto;
import ca.ulaval.glo4003.funds.assemblers.BillAssembler;
import ca.ulaval.glo4003.funds.domain.*;
import ca.ulaval.glo4003.offenses.domain.OffenseCode;
import ca.ulaval.glo4003.parkings.domain.ParkingArea;
import ca.ulaval.glo4003.parkings.domain.ParkingPeriod;
import ca.ulaval.glo4003.parkings.domain.ParkingSticker;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class BillService {
  private final Logger logger = Logger.getLogger(BillService.class.getName());
  private final BillFactory billFactory;
  private final BillRepository billRepository;
  private final BillAssembler billAssembler;
  private final BillQueryFactory billQueryFactory;

  public BillService(
      BillFactory billFactory,
      BillRepository billRepository,
      BillAssembler billAssembler,
      BillQueryFactory billQueryFactory) {
    this.billFactory = billFactory;
    this.billRepository = billRepository;
    this.billAssembler = billAssembler;
    this.billQueryFactory = billQueryFactory;
  }

  public BillId addBillForParkingSticker(ParkingSticker parkingSticker, ParkingArea parkingArea) {
    logger.info(String.format("Create bill for parking sticker %s", parkingSticker));

    Money feeForPeriod =
        parkingArea.getFeeForPeriod(ParkingPeriod.ONE_DAY); // TODO : Will this always be one day?
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

    return billAssembler.assemble(bill);
  }

  public List<Bill> getBillsByIds(List<BillId> billIds) {
    return billRepository.getBills(billIds);
  }

  public Bill getBill(BillId billId) {
    return billRepository.getBill(billId);
  }

  public Money getAllBills(Map<String, List<String>> params) {
    BillQuery billQuery = billQueryFactory.create(params);

    List<Bill> bills = billRepository.getAll(billQuery);

    return calculateProfits(bills);
  }

  private Money calculateProfits(List<Bill> bills) {
    Money total = Money.ZERO();
    for (Bill bill : bills) {
      total.plus(bill.getAmountPaid());
    }
    return total;
  }
}
