package ca.ulaval.glo4003.funds.services;

import ca.ulaval.glo4003.accesspasses.domain.AccessPass;
import ca.ulaval.glo4003.accounts.domain.AccountId;
import ca.ulaval.glo4003.accounts.services.AccountService;
import ca.ulaval.glo4003.cars.domain.ConsumptionType;
import ca.ulaval.glo4003.communications.domain.ReceptionMethod;
import ca.ulaval.glo4003.funds.domain.*;
import ca.ulaval.glo4003.funds.services.assemblers.BillAssembler;
import ca.ulaval.glo4003.funds.services.converters.BillIdConverter;
import ca.ulaval.glo4003.funds.services.converters.BillPaymentConverter;
import ca.ulaval.glo4003.funds.services.dto.BillDto;
import ca.ulaval.glo4003.funds.services.dto.BillPaymentDto;
import ca.ulaval.glo4003.initiatives.domain.InitiativeFundCollector;
import ca.ulaval.glo4003.offenses.domain.OffenseCode;
import ca.ulaval.glo4003.parkings.domain.ParkingArea;
import ca.ulaval.glo4003.parkings.domain.ParkingSticker;
import ca.ulaval.glo4003.reports.services.ReportEventService;
import java.util.logging.Logger;

public class BillService {
  public static final Money POSTAL_CODE_FEE = Money.fromDouble(5);
  private final Logger logger = Logger.getLogger(BillService.class.getName());
  private final BillFactory billFactory;
  private final BillAssembler billAssembler;
  private final ReportEventService reportEventService;

  private final InitiativeFundCollector initiativeFundCollector;
  private final AccountService accountService;
  private final BillPaymentConverter billPaymentConverter;
  private final SustainableMobilityProgramAllocationCalculator
      sustainableMobilityProgramAllocationCalculator;
  private final BillIdConverter billIdConverter;

  public BillService(
      BillFactory billFactory,
      ReportEventService reportEventService,
      AccountService accountService,
      InitiativeFundCollector initiativeFundCollector) {
    this(
        billFactory,
        new BillAssembler(),
        reportEventService,
        accountService,
        new BillPaymentConverter(),
        new BillIdConverter(),
        initiativeFundCollector,
        new SustainableMobilityProgramAllocationCalculator());
  }

  public BillService(
      BillFactory billFactory,
      BillAssembler billAssembler,
      ReportEventService reportEventService,
      AccountService accountService,
      BillPaymentConverter billPaymentConverter,
      BillIdConverter billIdConverter,
      InitiativeFundCollector initiativeFundCollector,
      SustainableMobilityProgramAllocationCalculator
          sustainableMobilityProgramAllocationCalculator) {
    this.billFactory = billFactory;
    this.billAssembler = billAssembler;
    this.reportEventService = reportEventService;
    this.accountService = accountService;
    this.initiativeFundCollector = initiativeFundCollector;
    this.sustainableMobilityProgramAllocationCalculator =
        sustainableMobilityProgramAllocationCalculator;
    this.billPaymentConverter = billPaymentConverter;
    this.billIdConverter = billIdConverter;
  }

  public Bill addBillForParkingSticker(ParkingSticker parkingSticker, ParkingArea parkingArea) {
    logger.info(String.format("Create bill for parking sticker %s", parkingSticker.getCode()));

    Money feeForPeriod = parkingArea.getFeeForPeriod(parkingSticker.getParkingPeriod());

    if (parkingSticker.getReceptionMethod().equals(ReceptionMethod.POSTAL)) {
      feeForPeriod = feeForPeriod.plus(POSTAL_CODE_FEE);
    }

    Bill bill =
        billFactory.createForParkingSticker(
            feeForPeriod, parkingSticker.getCode(), parkingSticker.getReceptionMethod());

    accountService.addBillToAccount(parkingSticker.getAccountId(), bill);

    return bill;
  }

  public Bill addBillForAccessPass(
      AccountId accountId, Money fee, AccessPass accessPass, ConsumptionType consumptionType) {
    logger.info(String.format("Create bill for access code %s", accessPass));

    Bill bill =
        billFactory.createForAccessPass(
            fee, accessPass.getCode(), consumptionType, accessPass.getReceptionMethod());

    accountService.addBillToAccount(accountId, bill);

    return bill;
  }

  public Bill addBillOffense(AccountId accountId, Money fee, OffenseCode offenseCode) {
    logger.info(String.format("Create bill for offense %s", offenseCode));

    Bill bill = billFactory.createForOffense(fee, offenseCode);

    accountService.addBillToAccount(accountId, bill);

    return bill;
  }

  public BillDto payBill(BillPaymentDto billPaymentDto, String accountId, String billId) {
    Money amountToPay = billPaymentConverter.convert(billPaymentDto);
    BillId billNumber = billIdConverter.convert(billId);

    Bill bill = accountService.getBill(accountId, billNumber);
    bill.pay(amountToPay);
    accountService.update(bill);

    reportBillPaidEvent(bill, amountToPay);

    if (bill.isBillTypeEqual(BillType.ACCESS_PASS)
        || bill.isBillTypeEqual(BillType.PARKING_STICKER)) {
      initiativeFundCollector.addMoney(
          sustainableMobilityProgramAllocationCalculator.calculate(amountToPay));
    }

    return billAssembler.assemble(bill);
  }

  private void reportBillPaidEvent(Bill bill, Money amountPaid) {
    switch (bill.getBillType()) {
      case PARKING_STICKER:
        reportEventService.addBillPaidForParkingStickerEvent(amountPaid);
        break;
      case ACCESS_PASS:
        reportEventService.addBillPaidForAccessPassEvent(amountPaid, bill.getConsumptionType());
        break;
      default:
      case OFFENSE:
        reportEventService.addBillPaidForOffenseEvent(amountPaid);
        break;
    }
  }
}
