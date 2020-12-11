package ca.ulaval.glo4003.funds.services;

import static ca.ulaval.glo4003.accesspasses.helpers.AccessPassMother.createAccessPassCode;
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
import ca.ulaval.glo4003.cars.domain.ConsumptionType;
import ca.ulaval.glo4003.funds.domain.*;
import ca.ulaval.glo4003.funds.services.assemblers.BillAssembler;
import ca.ulaval.glo4003.initiatives.domain.InitiativeFundCollector;
import ca.ulaval.glo4003.offenses.domain.OffenseCode;
import ca.ulaval.glo4003.parkings.domain.ParkingArea;
import ca.ulaval.glo4003.parkings.domain.ParkingPeriod;
import ca.ulaval.glo4003.parkings.domain.ParkingSticker;
import ca.ulaval.glo4003.parkings.domain.ReceptionMethod;
import ca.ulaval.glo4003.reports.services.ReportEventService;
import java.util.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class BillServiceTest {

  @Mock private BillFactory billFactory;
  @Mock private BillRepository billRepository;
  @Mock private BillAssembler billAssembler;
  @Mock private BillPriceCalculator billPriceCalculator;
  @Mock private InitiativeFundCollector initiativeFundCollector;
  @Mock private ReportEventService reportEventService;

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

  @Before
  public void setUp() {
    billService =
        new BillService(
            billFactory,
            billRepository,
            billAssembler,
            reportEventService,
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
    when(billRepository.getBill(bill.getId())).thenReturn(bill);
    when(billPriceCalculator.calculateTotalPrice(bills)).thenReturn(fee);
    when(billPriceCalculator.calculatePaidPrice(bills)).thenReturn(fee);
    when(sustainableMobilityProgramAllocationCalculator.calculate(amountDue))
        .thenReturn(amountKeptForSustainabilityProgram);
  }

  @Test
  public void whenAddingBillForParkingSticker_thenReturnBillId() {
    BillId billId = billService.addBillForParkingSticker(parkingSticker, parkingArea);

    assertThat(billId).isEqualTo(bill.getId());
  }

  @Test
  public void whenAddingBillForParkingSticker_thenSaveBillToRepository() {
    billService.addBillForParkingSticker(parkingSticker, parkingArea);

    verify(billRepository).save(bill);
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
  public void whenAddingBillForAccessPass_thenReturnBillId() {
    BillId billId = billService.addBillForAccessCode(fee, accessPassCode, consumptionType);

    assertThat(billId).isEqualTo(bill.getId());
  }

  @Test
  public void whenAddingBillForAccessPass_thenSaveBillToRepository() {
    billService.addBillForAccessCode(fee, accessPassCode, consumptionType);

    verify(billRepository).save(bill);
  }

  @Test
  public void whenAddingBillForOffense_thenReturnBillId() {
    BillId billId = billService.addBillOffense(fee, offenseCode);

    assertThat(billId).isEqualTo(bill.getId());
  }

  @Test
  public void whenAddingBillForOffense_thenSaveBillToRepository() {
    billService.addBillOffense(fee, offenseCode);

    verify(billRepository).save(bill);
  }

  @Test
  public void givenBillIds_whenGettingBills_thenBillRepositoryIsCalled() {
    List<BillId> billIds = new ArrayList<>();
    billIds.add(bill.getId());

    billService.getBillsByIds(billIds);

    verify(billRepository).getBills(billIds);
  }

  @Test
  public void whenGettingBill_thenBillRepositoryIsCalled() {
    billService.getBill(bill.getId());

    verify(billRepository).getBill(bill.getId());
  }

  @Test
  public void whenPayingBill_thenGetBillRepositoryIsCalled() {
    billService.payBill(bill.getId(), bill.getAmountDue());

    verify(billRepository).getBill(bill.getId());
  }

  @Test
  public void whenPayingBill_thenSaveBillRepositoryIsCalled() {
    billService.payBill(bill.getId(), bill.getAmountDue());
    bill.pay(bill.getAmountDue());

    verify(billRepository).updateBill(bill);
  }

  @Test
  public void whenPayingBill_thenBillAssemblerIsCalled() {
    billService.payBill(bill.getId(), bill.getAmountDue());
    bill.pay(bill.getAmountDue());

    verify(billAssembler).assemble(bill);
  }

  @Test
  public void
      givenBillTypeParkingSticker_whenPayingBill_thenSustainableMobilityProgramBankRepositoryIsCalled() {
    Bill parkingBill =
        aBill().withAmountDue(bill.getAmountDue()).withBillType(BillType.PARKING_STICKER).build();
    when(billRepository.getBill(parkingBill.getId())).thenReturn(parkingBill);

    billService.payBill(parkingBill.getId(), amountDue);
    bill.pay(amountDue);

    verify(initiativeFundCollector).addMoney(amountKeptForSustainabilityProgram);
  }

  @Test
  public void
      givenBillTypeParkingSticker_whenPayingBill_thenReportBillPaidForParkingStickerEvent() {
    Bill parkingBill =
        aBill().withAmountDue(bill.getAmountDue()).withBillType(BillType.PARKING_STICKER).build();
    when(billRepository.getBill(parkingBill.getId())).thenReturn(parkingBill);

    billService.payBill(parkingBill.getId(), amountDue);
    bill.pay(amountDue);

    verify(reportEventService).addBillPaidForParkingStickerEvent(amountDue);
  }

  @Test
  public void
      givenBillTypeAccessPass_whenPayingBill_thenSustainableMobilityProgramBankRepositoryIsCalled() {
    Bill accessPassBill =
        aBill().withAmountDue(bill.getAmountDue()).withBillType(BillType.ACCESS_PASS).build();
    when(billRepository.getBill(accessPassBill.getId())).thenReturn(accessPassBill);

    billService.payBill(accessPassBill.getId(), amountDue);
    bill.pay(amountDue);

    verify(initiativeFundCollector).addMoney(amountKeptForSustainabilityProgram);
  }

  @Test
  public void givenBillTypeAccessPass_whenPayingBill_thenReportBillPaidForAccessPassEvent() {
    Bill accessPassBill =
        aBill().withAmountDue(bill.getAmountDue()).withBillType(BillType.ACCESS_PASS).build();
    when(billRepository.getBill(accessPassBill.getId())).thenReturn(accessPassBill);

    billService.payBill(accessPassBill.getId(), amountDue);
    bill.pay(amountDue);

    verify(reportEventService)
        .addBillPaidForAccessPassEvent(amountDue, accessPassBill.getConsumptionType().get());
  }

  @Test
  public void
      givenBillTypeOffense_whenPayingBill_thenSustainableMobilityProgramBankRepositoryIsCalled() {
    Bill offenseBill =
        aBill().withAmountDue(bill.getAmountDue()).withBillType(BillType.OFFENSE).build();
    when(billRepository.getBill(offenseBill.getId())).thenReturn(offenseBill);

    billService.payBill(offenseBill.getId(), amountDue);
    bill.pay(amountDue);

    verify(initiativeFundCollector, never()).addMoney(any(Money.class));
  }

  @Test
  public void givenBillTypeOffense_whenPayingBill_thenReportBillPaidForOffenseEvent() {
    Bill offenseBill =
        aBill().withAmountDue(bill.getAmountDue()).withBillType(BillType.OFFENSE).build();
    when(billRepository.getBill(offenseBill.getId())).thenReturn(offenseBill);

    billService.payBill(offenseBill.getId(), amountDue);
    bill.pay(amountDue);

    verify(reportEventService).addBillPaidForOffenseEvent(amountDue);
  }
}
