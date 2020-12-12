package ca.ulaval.glo4003.funds.services;

import static ca.ulaval.glo4003.accesspasses.helpers.AccessPassMother.createAccessPassCode;
import static ca.ulaval.glo4003.accounts.helpers.AccountBuilder.anAccount;
import static ca.ulaval.glo4003.cars.helpers.CarMother.createConsumptionType;
import static ca.ulaval.glo4003.funds.helpers.BillBuilder.aBill;
import static ca.ulaval.glo4003.funds.helpers.MoneyMother.createMoney;
import static ca.ulaval.glo4003.offenses.helpers.OffenseTypeMother.createOffenseCode;
import static ca.ulaval.glo4003.parkings.helpers.ParkingAreaBuilder.aParkingArea;
import static ca.ulaval.glo4003.parkings.helpers.ParkingStickerBuilder.aParkingSticker;
import static ca.ulaval.glo4003.parkings.helpers.ParkingStickerMother.createParkingPeriod;
import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.*;

import ca.ulaval.glo4003.accesspasses.domain.AccessPassCode;
import ca.ulaval.glo4003.accounts.domain.Account;
import ca.ulaval.glo4003.accounts.services.AccountService;
import ca.ulaval.glo4003.cars.domain.ConsumptionType;
import ca.ulaval.glo4003.funds.domain.*;
import ca.ulaval.glo4003.funds.services.assemblers.BillAssembler;
import ca.ulaval.glo4003.funds.services.converters.BillIdConverter;
import ca.ulaval.glo4003.funds.services.converters.BillPaymentConverter;
import ca.ulaval.glo4003.funds.services.dto.BillPaymentDto;
import ca.ulaval.glo4003.initiatives.domain.InitiativeFundCollector;
import ca.ulaval.glo4003.offenses.domain.OffenseCode;
import ca.ulaval.glo4003.parkings.domain.ParkingArea;
import ca.ulaval.glo4003.parkings.domain.ParkingPeriod;
import ca.ulaval.glo4003.parkings.domain.ParkingSticker;
import ca.ulaval.glo4003.parkings.domain.ReceptionMethod;
import ca.ulaval.glo4003.reports.services.ReportEventService;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class BillServiceTest {

  @Mock private BillFactory billFactory;
  @Mock private BillAssembler billAssembler;
  @Mock private BillPriceCalculator billPriceCalculator;
  @Mock private InitiativeFundCollector initiativeFundCollector;
  @Mock private ReportEventService reportEventService;
  @Mock private AccountService accountService;
  @Mock private BillPaymentConverter billPaymentConverter;
  @Mock private BillIdConverter billIdConverter;
  @Mock private BillPaymentDto billPaymentDto;

  @Mock
  SustainableMobilityProgramAllocationCalculator sustainableMobilityProgramAllocationCalculator;

  private BillService billService;

  private final ParkingPeriod parkingPeriod = createParkingPeriod();
  private final ParkingSticker parkingSticker =
      aParkingSticker().withParkingPeriod(parkingPeriod).build();
  private final Money POSTAL_FEE = BillService.POSTAL_CODE_FEE;
  private final Bill bill = aBill().build();
  private final Money fee = createMoney();
  private final Money amountDue = Money.fromDouble(1);
  private final Money amountKeptForSustainabilityProgram = amountDue.multiply(0.4);
  private final AccessPassCode accessPassCode = createAccessPassCode();
  private final ConsumptionType consumptionType = createConsumptionType();
  private final OffenseCode offenseCode = createOffenseCode();
  private final Map<ParkingPeriod, Money> feePerPeriod =
      Collections.singletonMap(parkingPeriod, fee);
  private final ParkingArea parkingArea = aParkingArea().withFeePerPeriod(feePerPeriod).build();
  private final Account account = anAccount().build();

  @Before
  public void setUp() {
    billService =
        new BillService(
            billFactory,
            billAssembler,
            reportEventService,
            accountService,
            billPaymentConverter,
            billIdConverter,
            initiativeFundCollector,
            sustainableMobilityProgramAllocationCalculator);

    List<Bill> bills = Collections.singletonList(bill);

    when(billFactory.createForParkingSticker(
            fee, parkingSticker.getCode(), parkingSticker.getReceptionMethod()))
        .thenReturn(bill);
    when(billFactory.createForParkingSticker(
            fee.plus(POSTAL_FEE), parkingSticker.getCode(), parkingSticker.getReceptionMethod()))
        .thenReturn(bill);
    when(billFactory.createForAccessPass(fee, accessPassCode, consumptionType)).thenReturn(bill);
    when(billFactory.createForOffense(fee, offenseCode)).thenReturn(bill);
    when(billPriceCalculator.calculateTotalPrice(bills)).thenReturn(fee);
    when(billPriceCalculator.calculatePaidPrice(bills)).thenReturn(fee);
    when(sustainableMobilityProgramAllocationCalculator.calculate(amountDue))
        .thenReturn(amountKeptForSustainabilityProgram);
  }

  @Test
  public void whenAddingBillForParkingSticker_thenReturnBill() {
    Bill anotherBill = billService.addBillForParkingSticker(parkingSticker, parkingArea);

    assertThat(anotherBill).isEqualTo(bill);
  }

  @Test
  public void whenAddingBillForParkingSticker_thenAddBillToAccount() {
    billService.addBillForParkingSticker(parkingSticker, parkingArea);

    verify(accountService).addBillToAccount(parkingSticker.getAccountId(), bill);
  }

  @Test
  public void givenPostalParkingSticker_whenAddingBill_thenAddPostalFee() {
    ParkingSticker postalParkingSticker =
        aParkingSticker()
            .withReceptionMethod(ReceptionMethod.POSTAL)
            .withParkingPeriod(parkingPeriod)
            .build();
    when(billFactory.createForParkingSticker(
            fee.plus(POSTAL_FEE),
            postalParkingSticker.getCode(),
            postalParkingSticker.getReceptionMethod()))
        .thenReturn(bill);

    billService.addBillForParkingSticker(postalParkingSticker, parkingArea);
    Money expectedFee = fee.plus(POSTAL_FEE);

    verify(billFactory)
        .createForParkingSticker(
            expectedFee, postalParkingSticker.getCode(), postalParkingSticker.getReceptionMethod());
  }

  @Test
  public void whenAddingBillForAccessPass_thenReturnBill() {
    Bill bill =
        billService.addBillForAccessCode(account.getId(), fee, accessPassCode, consumptionType);

    assertThat(bill).isEqualTo(bill);
  }

  @Test
  public void whenAddingBillForAccessPass_thenAddBillToAccount() {
    billService.addBillForAccessCode(account.getId(), fee, accessPassCode, consumptionType);

    verify(accountService).addBillToAccount(account.getId(), bill);
  }

  @Test
  public void whenAddingBillForOffense_thenReturnBillId() {
    Bill bill = billService.addBillOffense(account.getId(), fee, offenseCode);

    assertThat(bill).isEqualTo(bill);
  }

  @Test
  public void whenAddingBillForOffense_thenAddBillToAccount() {
    billService.addBillOffense(account.getId(), fee, offenseCode);

    verify(accountService).addBillToAccount(account.getId(), bill);
  }

  @Test
  public void whenPayingBill_thenBillAssemblerIsCalled() {
    when(accountService.getBill(account.getId().toString(), bill.getId())).thenReturn(bill);
    when(billPaymentConverter.convert(billPaymentDto)).thenReturn(amountDue);
    when(billIdConverter.convert(bill.getId().toString())).thenReturn(bill.getId());

    billService.payBill(billPaymentDto, account.getId().toString(), bill.getId().toString());
    bill.pay(amountDue);

    verify(billAssembler).assemble(bill);
  }

  @Test
  public void givenBillTypeParkingSticker_whenPayingBill_thenInitiativeFundCollectorIsCalled() {
    Bill parkingBill =
        aBill().withAmountDue(bill.getAmountDue()).withBillType(BillType.PARKING_STICKER).build();
    when(accountService.getBill(account.getId().toString(), parkingBill.getId()))
        .thenReturn(parkingBill);
    when(billPaymentConverter.convert(billPaymentDto)).thenReturn(amountDue);
    when(billIdConverter.convert(parkingBill.getId().toString())).thenReturn(parkingBill.getId());

    billService.payBill(billPaymentDto, account.getId().toString(), parkingBill.getId().toString());
    bill.pay(amountDue);

    verify(initiativeFundCollector).addMoney(amountKeptForSustainabilityProgram);
  }

  @Test
  public void
      givenBillTypeParkingSticker_whenPayingBill_thenReportBillPaidForParkingStickerEvent() {
    Bill parkingBill =
        aBill().withAmountDue(bill.getAmountDue()).withBillType(BillType.PARKING_STICKER).build();
    when(accountService.getBill(account.getId().toString(), parkingBill.getId()))
        .thenReturn(parkingBill);

    when(billPaymentConverter.convert(billPaymentDto)).thenReturn(amountDue);
    when(billIdConverter.convert(parkingBill.getId().toString())).thenReturn(parkingBill.getId());

    billService.payBill(billPaymentDto, account.getId().toString(), parkingBill.getId().toString());
    bill.pay(amountDue);

    verify(reportEventService).addBillPaidForParkingStickerEvent(amountDue);
  }

  @Test
  public void givenBillTypeAccessPass_whenPayingBill_thenInitiativeFundCollectorIsCalled() {
    Bill accessPassBill =
        aBill().withAmountDue(bill.getAmountDue()).withBillType(BillType.ACCESS_PASS).build();
    when(accountService.getBill(account.getId().toString(), accessPassBill.getId()))
        .thenReturn(accessPassBill);

    when(billPaymentConverter.convert(billPaymentDto)).thenReturn(amountDue);
    when(billIdConverter.convert(accessPassBill.getId().toString()))
        .thenReturn(accessPassBill.getId());

    billService.payBill(
        billPaymentDto, account.getId().toString(), accessPassBill.getId().toString());
    bill.pay(amountDue);

    verify(initiativeFundCollector).addMoney(amountKeptForSustainabilityProgram);
  }

  @Test
  public void givenBillTypeAccessPass_whenPayingBill_thenReportBillPaidForAccessPassEvent() {
    Bill accessPassBill =
        aBill().withAmountDue(bill.getAmountDue()).withBillType(BillType.ACCESS_PASS).build();
    when(billPaymentConverter.convert(billPaymentDto)).thenReturn(amountDue);
    when(billIdConverter.convert(accessPassBill.getId().toString()))
        .thenReturn(accessPassBill.getId());
    when(accountService.getBill(account.getId().toString(), accessPassBill.getId()))
        .thenReturn(accessPassBill);

    billService.payBill(
        billPaymentDto, account.getId().toString(), accessPassBill.getId().toString());
    bill.pay(amountDue);

    verify(reportEventService)
        .addBillPaidForAccessPassEvent(amountDue, accessPassBill.getConsumptionType().get());
  }

  @Test
  public void givenBillTypeOffense_whenPayingBill_thenInitiativeFundCollectorIsCalled() {
    Bill offenseBill =
        aBill().withAmountDue(bill.getAmountDue()).withBillType(BillType.OFFENSE).build();
    when(accountService.getBill(account.getId().toString(), offenseBill.getId()))
        .thenReturn(offenseBill);

    when(billPaymentConverter.convert(billPaymentDto)).thenReturn(amountDue);
    when(billIdConverter.convert(offenseBill.getId().toString())).thenReturn(offenseBill.getId());

    billService.payBill(billPaymentDto, account.getId().toString(), offenseBill.getId().toString());
    bill.pay(amountDue);

    verify(initiativeFundCollector, never()).addMoney(any(Money.class));
  }

  @Test
  public void givenBillTypeOffense_whenPayingBill_thenReportBillPaidForOffenseEvent() {
    Bill offenseBill =
        aBill().withAmountDue(bill.getAmountDue()).withBillType(BillType.OFFENSE).build();
    when(accountService.getBill(account.getId().toString(), offenseBill.getId()))
        .thenReturn(offenseBill);

    when(billPaymentConverter.convert(billPaymentDto)).thenReturn(amountDue);
    when(billIdConverter.convert(offenseBill.getId().toString())).thenReturn(offenseBill.getId());

    billService.payBill(billPaymentDto, account.getId().toString(), offenseBill.getId().toString());
    bill.pay(amountDue);

    verify(reportEventService).addBillPaidForOffenseEvent(amountDue);
  }
}
