package ca.ulaval.glo4003.funds.services;

import static ca.ulaval.glo4003.accesspasses.helpers.AccessPassMother.createAccessPassCode;
import static ca.ulaval.glo4003.funds.helpers.BillBuilder.aBill;
import static ca.ulaval.glo4003.funds.helpers.MoneyMother.createMoney;
import static ca.ulaval.glo4003.offenses.helpers.OffenseTypeMother.createOffenseCode;
import static ca.ulaval.glo4003.parkings.helpers.ParkingAreaBuilder.aParkingArea;
import static ca.ulaval.glo4003.parkings.helpers.ParkingStickerBuilder.aParkingSticker;
import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import ca.ulaval.glo4003.accesspasses.domain.AccessPassCode;
import ca.ulaval.glo4003.funds.assemblers.BillAssembler;
import ca.ulaval.glo4003.funds.domain.*;
import ca.ulaval.glo4003.funds.domain.queries.BillQueryParams;
import ca.ulaval.glo4003.offenses.domain.OffenseCode;
import ca.ulaval.glo4003.parkings.domain.ParkingArea;
import ca.ulaval.glo4003.parkings.domain.ParkingPeriod;
import ca.ulaval.glo4003.parkings.domain.ParkingSticker;
import java.util.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class BillServiceTest {

  @Mock private BillFactory billFactory;
  @Mock private BillRepository<BillQuery> billRepository;
  @Mock private BillAssembler billAssembler;
  @Mock private BillQueryFactory billQueryFactory;
  @Mock private BillQuery billQuery;
  @Mock private BillProfitsCalculator billProfitsCalculator;
  @Mock private BillQueryParams billQueryParams;

  private BillService billService;

  private final ParkingSticker parkingSticker = aParkingSticker().build();
  private final Money parkingPeriodFee = createMoney();
  private final Bill bill = aBill().build();
  private ParkingArea parkingArea;
  private final Money fee = createMoney();
  private final AccessPassCode accessPassCode = createAccessPassCode();
  private final OffenseCode offenseCode = createOffenseCode();
  private final Map<String, List<String>> params = new HashMap<>();

  @Before
  public void setUp() {
    billService = new BillService(billFactory, billRepository, billAssembler, billQueryFactory);

    Map<ParkingPeriod, Money> feePerPeriod = new HashMap<>();
    feePerPeriod.put(ParkingPeriod.ONE_DAY, parkingPeriodFee);
    parkingArea = aParkingArea().withFeePerPeriod(feePerPeriod).build();

    List<Bill> bills = new ArrayList<>();
    bills.add(bill);

    when(billFactory.createForParkingSticker(
            parkingPeriodFee, parkingSticker.getCode(), parkingSticker.getReceptionMethod()))
        .thenReturn(bill);
    when(billFactory.createForAccessPass(fee, accessPassCode)).thenReturn(bill);
    when(billFactory.createForOffense(fee, offenseCode)).thenReturn(bill);
    when(billQueryFactory.create(billQueryParams)).thenReturn(billQuery);
    when(billRepository.getBill(bill.getId())).thenReturn(bill);
    when(billRepository.getAll(billQuery)).thenReturn(bills);
    when(billProfitsCalculator.calculate(bills)).thenReturn(fee);
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
  public void whenAddingBillForAccessPass_thenReturnBillId() {
    BillId billId = billService.addBillForAccessCode(fee, accessPassCode);

    assertThat(billId).isEqualTo(bill.getId());
  }

  @Test
  public void whenAddingBillForAccessPass_thenSaveBillToRepository() {
    billService.addBillForAccessCode(fee, accessPassCode);

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

  // TODO : Manque un test getAll est pas tester lol
}
