package ca.ulaval.glo4003.profits.services;

import static ca.ulaval.glo4003.funds.helpers.MoneyMother.createMoney;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import ca.ulaval.glo4003.funds.domain.Bill;
import ca.ulaval.glo4003.funds.domain.BillProfitsCalculator;
import ca.ulaval.glo4003.funds.domain.BillType;
import ca.ulaval.glo4003.funds.domain.Money;
import ca.ulaval.glo4003.funds.domain.queries.BillQueryParams;
import ca.ulaval.glo4003.funds.domain.queries.BillQueryParamsAssembler;
import ca.ulaval.glo4003.funds.services.BillService;
import ca.ulaval.glo4003.profits.assemblers.ProfitsAssembler;
import ca.ulaval.glo4003.profits.assemblers.ProfitsByConsumptionTypeAssembler;
import ca.ulaval.glo4003.profits.domain.ProfitByConsumptionType;
import ca.ulaval.glo4003.profits.domain.ProfitsByConsumptionTypeFactory;
import ca.ulaval.glo4003.times.helpers.CustomDateMother;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ProfitsServiceTest {

  private static final int A_YEAR = CustomDateMother.createFutureDate().toLocalDate().getYear();
  private static Money AN_AMOUNT = createMoney();

  private ProfitsService profitsService;

  private List<Bill> bills = new ArrayList<>();
  private List<ProfitByConsumptionType> profitByConsumptionTypes = new ArrayList<>();

  @Mock private ProfitsAssembler profitsAssembler;
  @Mock private BillQueryParamsAssembler billQueryParamsAssembler;
  @Mock private BillQueryParams billQueryParams;
  @Mock private BillService billService;
  @Mock private BillProfitsCalculator billProfitsCalculator;
  @Mock private ProfitsByConsumptionTypeFactory profitsByConsumptionTypeFactory;
  @Mock private ProfitsByConsumptionTypeAssembler profitsByConsumptionTypeAssembler;

  @Before
  public void setup() {
    profitsService =
        new ProfitsService(
            profitsAssembler,
            billService,
            billQueryParamsAssembler,
            billProfitsCalculator,
            profitsByConsumptionTypeFactory,
            profitsByConsumptionTypeAssembler);
    when(billQueryParamsAssembler.assemble(A_YEAR, BillType.PARKING_STICKER))
        .thenReturn(billQueryParams);
    when(billQueryParamsAssembler.assemble(A_YEAR, BillType.OFFENSE)).thenReturn(billQueryParams);
    when(billQueryParamsAssembler.assemble(A_YEAR, BillType.ACCESS_PASS))
        .thenReturn(billQueryParams);
    when(billService.getAllBillsByQueryParams(billQueryParams)).thenReturn(bills);
    when(billProfitsCalculator.calculatePaidPrice(bills)).thenReturn(AN_AMOUNT);
    when(billProfitsCalculator.calculateTotalPrice(bills)).thenReturn(AN_AMOUNT);
  }

  @Test
  public void whenGettingProfitsFromParkingSticker_shouldAssembleProfits() {
    profitsService.getParkingStickerProfits(A_YEAR);

    verify(profitsAssembler).assemble(AN_AMOUNT);
  }

  @Test
  public void whenGettingProfitsFromOffsense_shouldAssembleProfits() {
    profitsService.getOffenseProfits(A_YEAR);

    verify(profitsAssembler).assemble(AN_AMOUNT);
  }

  @Test
  public void whenGettingProfitsFromAccessPass_shouldAssembleProfits() {
    profitsService.getAccessPassProfits(A_YEAR);

    verify(profitsAssembler).assemble(AN_AMOUNT);
  }

  @Test
  public void
      whenGettingProfitsFromAccessPassByConsumptionTest_shouldAssembleProfitsByConsumptionType() {
    profitsService.getAccessPassProfitsByConsumptionType(A_YEAR);

    verify(profitsByConsumptionTypeAssembler).assembleMany(profitByConsumptionTypes);
  }
}
