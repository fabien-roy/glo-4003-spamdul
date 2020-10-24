package ca.ulaval.glo4003.funds.services;

import ca.ulaval.glo4003.accesspasses.domain.AccessPassCode;
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
  private final BillRepository<BillQuery> billRepository;
  private final BillAssembler billAssembler;
  private final BillQueryFactory billQueryFactory;
  private final BillProfitsCalculator billProfitsCalculator;
  private final SustainableMobilityProgramBankRepository sustainableMobilityProgramBankRepository;
  private final SustainableMobilityProgramAllocationCalculator
      sustainableMobilityProgramAllocationCalculator;

  public BillService(
      BillFactory billFactory,
      BillRepository<BillQuery> billRepository,
      BillAssembler billAssembler,
      BillQueryFactory billQueryFactory,
      BillProfitsCalculator billProfitsCalculator,
      SustainableMobilityProgramBankRepository sustainableMobilityProgramBankRepository,
      SustainableMobilityProgramAllocationCalculator
          sustainableMobilityProgramAllocationCalculator) {
    this.billFactory = billFactory;
    this.billRepository = billRepository;
    this.billAssembler = billAssembler;
    this.billQueryFactory = billQueryFactory;
    this.billProfitsCalculator = billProfitsCalculator;
    this.sustainableMobilityProgramBankRepository = sustainableMobilityProgramBankRepository;
    this.sustainableMobilityProgramAllocationCalculator =
        sustainableMobilityProgramAllocationCalculator;
  }

  public BillId addBillForParkingSticker(ParkingSticker parkingSticker, ParkingArea parkingArea) {
    logger.info(String.format("Create bill for parking sticker %s", parkingSticker.getCode()));

    Money feeForPeriod =
        parkingArea.getFeeForPeriod(ParkingPeriod.ONE_DAY); // TODO : Will this always be one day?
    Bill bill =
        billFactory.createForParkingSticker(
            feeForPeriod, parkingSticker.getCode(), parkingSticker.getReceptionMethod());

    billRepository.save(bill);

    return bill.getId();
  }

  public BillId addBillForAccessCode(Money fee, AccessPassCode accessPassCode) {
    logger.info(String.format("Create bill for access code %s", accessPassCode));

    Bill bill = billFactory.createForAccessPass(fee, accessPassCode);

    billRepository.save(bill);

    return bill.getId();
  }

  public BillId addBillOffense(Money fee, OffenseCode offenseCode) {
    logger.info(String.format("Create bill for offense %s", offenseCode));

    Bill bill = billFactory.createForOffense(fee, offenseCode);

    billRepository.save(bill);

    return bill.getId();
  }

  public BillDto payBill(BillId billId, Money amountToPay) {
    logger.info(String.format("Paying bill %s an amount of %s", billId, amountToPay));

    Bill bill = getBill(billId);
    bill.pay(amountToPay);
    billRepository.updateBill(bill);

    sustainableMobilityProgramBankRepository.add(
        sustainableMobilityProgramAllocationCalculator.calculate(amountToPay, 0.4));

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

    return billProfitsCalculator.calculate(bills);
  }
}
