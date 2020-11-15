package ca.ulaval.glo4003.funds.services;

import static ca.ulaval.glo4003.accesspasses.helpers.AccessPassMother.createAccessPassCode;
import static ca.ulaval.glo4003.cars.helpers.CarMother.createConsumptionType;
import static ca.ulaval.glo4003.funds.helpers.BillBuilder.aBill;
import static ca.ulaval.glo4003.funds.helpers.MoneyMother.createMoney;
import static ca.ulaval.glo4003.offenses.helpers.OffenseTypeMother.createOffenseCode;
import static ca.ulaval.glo4003.parkings.helpers.ParkingAreaBuilder.aParkingArea;
import static ca.ulaval.glo4003.parkings.helpers.ParkingStickerBuilder.aParkingSticker;
import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.*;

import ca.ulaval.glo4003.accesspasses.domain.AccessPassCode;
import ca.ulaval.glo4003.cars.domain.ConsumptionType;
import ca.ulaval.glo4003.funds.assemblers.BillAssembler;
import ca.ulaval.glo4003.funds.assemblers.BillsByConsumptionsTypeAssembler;
import ca.ulaval.glo4003.funds.domain.*;
import ca.ulaval.glo4003.funds.domain.queryparams.BillQueryParams;
import ca.ulaval.glo4003.offenses.domain.OffenseCode;
import ca.ulaval.glo4003.parkings.domain.ParkingArea;
import ca.ulaval.glo4003.parkings.domain.ParkingPeriod;
import ca.ulaval.glo4003.parkings.domain.ParkingSticker;
import ca.ulaval.glo4003.parkings.domain.ReceptionMethod;
import java.util.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class BillServiceTest {

  @Mock BillFactory billFactory;
  @Mock BillRepository<BillQuery> billRepository;
  @Mock BillAssembler billAssembler;
  @Mock BillQueryFactory billQueryFactory;
  @Mock BillQuery billQuery;
  @Mock BillProfitsCalculator billProfitsCalculator;
  @Mock SustainableMobilityProgramBankRepository sustainableMobilityProgramBankRepository;
  @Mock private BillsByConsumptionsTypeAssembler billsByConsumptionsTypeAssembler;

  @Mock
  SustainableMobilityProgramAllocationCalculator sustainableMobilityProgramAllocationCalculator;

  private BillService billService;

  private final ParkingSticker parkingSticker = aParkingSticker().build();
  private final Money POSTAL_FEE = BillService.POSTAL_CODE_FEE;
  private final Money parkingPeriodFee = createMoney();
  private final Bill bill = aBill().build();
  private ParkingArea parkingArea;
  private final Money fee = createMoney();
  private final Money amountDue = Money.fromDouble(1);
  private final Money amountKeptForSustainabilityProgram = amountDue.multiply(0.4);
  private final AccessPassCode accessPassCode = createAccessPassCode();
  private final ConsumptionType consumptionType = createConsumptionType();
  private final OffenseCode offenseCode = createOffenseCode();
  private final BillQueryParams params = new BillQueryParams();

  @Before
  public void setUp() {
    billService =
        new BillService(
            billFactory,
            billRepository,
            billAssembler,
            billQueryFactory,
                reportService, sustainableMobilityProgramBankRepository,
            sustainableMobilityProgramAllocationCalculator,
            billsByConsumptionsTypeAssembler);

    Map<ParkingPeriod, Money> feePerPeriod = new HashMap<>();
    feePerPeriod.put(ParkingPeriod.ONE_DAY, parkingPeriodFee);
    parkingArea = aParkingArea().withFeePerPeriod(feePerPeriod).build();

    List<Bill> bills = new ArrayList<>();
    bills.add(bill);

    when(billFactory.createForParkingSticker(
            parkingPeriodFee, parkingSticker.getCode(), parkingSticker.getReceptionMethod()))
        .thenReturn(bill);
    when(billFactory.createForParkingSticker(
            parkingPeriodFee.plus(POSTAL_FEE),
            parkingSticker.getCode(),
            parkingSticker.getReceptionMethod()))
        .thenReturn(bill);
    when(billFactory.createForAccessPass(fee, accessPassCode, consumptionType)).thenReturn(bill);
    when(billFactory.createForOffense(fee, offenseCode)).thenReturn(bill);
    when(billQueryFactory.create(params)).thenReturn(billQuery);
    when(billRepository.getBill(bill.getId())).thenReturn(bill);
    when(billRepository.getAll(billQuery)).thenReturn(bills);
    when(billProfitsCalculator.calculateTotalPrice(bills)).thenReturn(fee);
    when(billProfitsCalculator.calculatePaidPrice(bills)).thenReturn(fee);
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
        aParkingSticker().withReceptionMethod(ReceptionMethod.POSTAL).build();
    when(billFactory.createForParkingSticker(
            parkingPeriodFee.plus(POSTAL_FEE),
            postalParkingSticker.getCode(),
            postalParkingSticker.getReceptionMethod()))
        .thenReturn(bill);

    billService.addBillForParkingSticker(postalParkingSticker, parkingArea);
    Money expectedFee = parkingPeriodFee.plus(POSTAL_FEE);

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

    verify(sustainableMobilityProgramBankRepository).add(amountKeptForSustainabilityProgram);
  }

  @Test
  public void
      givenBillTypeAccessPass_whenPayingBill_thenSustainableMobilityProgramBankRepositoryIsCalled() {
    Bill accessPassBill =
        aBill().withAmountDue(bill.getAmountDue()).withBillType(BillType.ACCESS_PASS).build();
    when(billRepository.getBill(accessPassBill.getId())).thenReturn(accessPassBill);

    billService.payBill(accessPassBill.getId(), amountDue);
    bill.pay(amountDue);

    verify(sustainableMobilityProgramBankRepository).add(amountKeptForSustainabilityProgram);
  }

  @Test
  public void
      givenBillTypeOffense_whenPayingBill_thenSustainableMobilityProgramBankRepositoryIsCalled() {
    Bill offenseBill =
        aBill().withAmountDue(bill.getAmountDue()).withBillType(BillType.OFFENSE).build();
    when(billRepository.getBill(offenseBill.getId())).thenReturn(offenseBill);

    billService.payBill(offenseBill.getId(), amountDue);
    bill.pay(amountDue);

    verify(sustainableMobilityProgramBankRepository, never()).add(any(Money.class));
  }

  @Test
  public void whenGettingAllBills_thenRepositoryIscalled() {
    billService.getAllBillsByQueryParams(params);

    verify(billRepository).getAll(billQuery);
  }
}
