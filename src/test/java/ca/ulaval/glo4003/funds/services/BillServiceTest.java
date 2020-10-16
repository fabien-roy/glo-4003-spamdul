package ca.ulaval.glo4003.funds.services;

import static ca.ulaval.glo4003.access.helpers.AccessPassMother.createAccessPassCode;
import static ca.ulaval.glo4003.funds.helpers.BillBuilder.aBill;
import static ca.ulaval.glo4003.funds.helpers.MoneyMother.createMoney;
import static ca.ulaval.glo4003.offenses.helpers.OffenseTypeMother.createOffenseCode;
import static ca.ulaval.glo4003.parkings.helpers.ParkingAreaBuilder.aParkingArea;
import static ca.ulaval.glo4003.parkings.helpers.ParkingStickerBuilder.aParkingSticker;
import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import ca.ulaval.glo4003.access.domain.AccessPassCode;
import ca.ulaval.glo4003.funds.assemblers.BillAssembler;
import ca.ulaval.glo4003.funds.domain.*;
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

  @Mock BillFactory billFactory;
  @Mock BillRepository billRepository;
  @Mock BillAssembler billAssembler;
  @Mock BillQueryFactory billQueryFactory;
  @Mock BillQuery billQuery;

  private BillService billService;

  private final ParkingSticker parkingSticker = aParkingSticker().build();
  private final Money parkingPeriodFee = createMoney();
  private final Bill bill = aBill().build();
  private ParkingArea parkingArea;
  private final Money fee = createMoney();
  private final Money amountDue = new Money(1);
  private final AccessPassCode accessPassCode = createAccessPassCode();
  private final OffenseCode offenseCode = createOffenseCode();
  private Map<String, List<String>> params = new HashMap<>();

  @Before
  public void setUp() {
    billService = new BillService(billFactory, billRepository, billAssembler, billQueryFactory);

    Map<ParkingPeriod, Money> feePerPeriod = new HashMap<>();
    feePerPeriod.put(ParkingPeriod.ONE_DAY, parkingPeriodFee);
    parkingArea = aParkingArea().withFeePerPeriod(feePerPeriod).build();

    when(billFactory.createForParkingSticker(
            parkingPeriodFee, parkingSticker.getCode(), parkingSticker.getReceptionMethod()))
        .thenReturn(bill);
    when(billFactory.createForAccessPass(fee, accessPassCode)).thenReturn(bill);
    when(billFactory.createForOffense(fee, offenseCode)).thenReturn(bill);
    when(billQueryFactory.create(params)).thenReturn(billQuery);
    when(billRepository.getBill(bill.getId())).thenReturn(bill);
    when(billRepository.getAll(billQuery)).thenReturn(Arrays.asList(bill));
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
    billService.payBill(bill.getId(), amountDue);

    verify(billRepository).getBill(bill.getId());
  }

  @Test
  public void whenPayingBill_thenSaveBillRepositoryIsCalled() {
    billService.payBill(bill.getId(), amountDue);
    bill.pay(amountDue);

    verify(billRepository).updateBill(bill);
  }

  @Test
  public void whenPayingBill_thenBillAssemblerIsCalled() {
    billService.payBill(bill.getId(), amountDue);
    bill.pay(amountDue);

    verify(billAssembler).assemble(bill);
  }

  @Test
  public void whenGettingAllBills_thenShouldGetBillsWithQuery() {
    billService.getAllBills(params);

    verify(billRepository).getAll(billQuery);
  }

  @Test
  public void whenGettingAllBills_thenShouldReturnProfits() {
    Money total = billService.getAllBills(params);

    assertThat(total).isEqualTo(bill.getAmountPaid());
  }
}
