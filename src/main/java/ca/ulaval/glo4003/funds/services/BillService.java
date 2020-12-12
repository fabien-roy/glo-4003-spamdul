package ca.ulaval.glo4003.funds.services;

import ca.ulaval.glo4003.accesspasses.domain.AccessPassCode;
import ca.ulaval.glo4003.cars.domain.ConsumptionType;
import ca.ulaval.glo4003.funds.domain.*;
import ca.ulaval.glo4003.funds.services.assemblers.BillAssembler;
import ca.ulaval.glo4003.funds.services.dto.BillDto;
import ca.ulaval.glo4003.initiatives.domain.InitiativeFundCollector;
import ca.ulaval.glo4003.offenses.domain.OffenseCode;
import ca.ulaval.glo4003.parkings.domain.ParkingArea;
import ca.ulaval.glo4003.parkings.domain.ParkingSticker;
import ca.ulaval.glo4003.parkings.domain.ReceptionMethod;
import ca.ulaval.glo4003.reports.services.ReportEventService;
import java.util.List;
import java.util.logging.Logger;

public class BillService {
  public static final Money POSTAL_CODE_FEE = Money.fromDouble(5);
  private final Logger logger = Logger.getLogger(BillService.class.getName());
  private final BillFactory billFactory;
  private final BillRepository billRepository;
  private final BillAssembler billAssembler;
  private final ReportEventService reportEventService;

  private final InitiativeFundCollector initiativeFundCollector;
  private final SustainableMobilityProgramAllocationCalculator
      sustainableMobilityProgramAllocationCalculator;

  public BillService(
      BillFactory billFactory,
      BillRepository billRepository,
      ReportEventService reportEventService,
      InitiativeFundCollector initiativeFundCollector) {
    this(
        billFactory,
        billRepository,
        new BillAssembler(),
        reportEventService,
        initiativeFundCollector,
        new SustainableMobilityProgramAllocationCalculator());
  }

  public BillService(
      BillFactory billFactory,
      BillRepository billRepository,
      BillAssembler billAssembler,
      ReportEventService reportEventService,
      InitiativeFundCollector initiativeFundCollector,
      SustainableMobilityProgramAllocationCalculator
          sustainableMobilityProgramAllocationCalculator) {
    this.billFactory = billFactory;
    this.billRepository = billRepository;
    this.billAssembler = billAssembler;
    this.reportEventService = reportEventService;
    this.initiativeFundCollector = initiativeFundCollector;
    this.sustainableMobilityProgramAllocationCalculator =
        sustainableMobilityProgramAllocationCalculator;
  }

  public BillId addBillForParkingSticker(ParkingSticker parkingSticker, ParkingArea parkingArea) {
    logger.info(String.format("Create bill for parking sticker %s", parkingSticker.getCode()));

    Money feeForPeriod = parkingArea.getFeeForPeriod(parkingSticker.getParkingPeriod());

    if (parkingSticker.getReceptionMethod().equals(ReceptionMethod.POSTAL)) {
      feeForPeriod = feeForPeriod.plus(POSTAL_CODE_FEE);
    }

    Bill bill =
        billFactory.createForParkingSticker(
            feeForPeriod, parkingSticker.getCode(), parkingSticker.getReceptionMethod());

    billRepository.save(bill);

    return bill.getId();
  }

  public BillId addBillForAccessCode(
      Money fee, AccessPassCode accessPassCode, ConsumptionType consumptionType) {
    logger.info(String.format("Create bill for access code %s", accessPassCode));

    Bill bill = billFactory.createForAccessPass(fee, accessPassCode, consumptionType);

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

    reportBillPaidEvent(bill, amountToPay);

    if (bill.isBillTypeEqual(BillType.ACCESS_PASS)
        || bill.isBillTypeEqual(BillType.PARKING_STICKER)) {
      initiativeFundCollector.addMoney(
          sustainableMobilityProgramAllocationCalculator.calculate(amountToPay));
    }

    return billAssembler.assemble(bill);
  }

  public List<Bill> getBillsByIds(List<BillId> billIds) {
    return billRepository.getBills(billIds);
  }

  public Bill getBill(BillId billId) {
    return billRepository.getBill(billId);
  }

  private void reportBillPaidEvent(Bill bill, Money amountPaid) {
    switch (bill.getBillType()) {
      case PARKING_STICKER:
        reportEventService.addBillPaidForParkingStickerEvent(amountPaid);
        break;
      case ACCESS_PASS:
        reportEventService.addBillPaidForAccessPassEvent(
            amountPaid, bill.getConsumptionType().get());
        break;
      default:
      case OFFENSE:
        reportEventService.addBillPaidForOffenseEvent(amountPaid);
        break;
    }
  }
}
