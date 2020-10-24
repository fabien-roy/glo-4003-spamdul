package ca.ulaval.glo4003.profits.services;

import static ca.ulaval.glo4003.funds.helpers.MoneyMother.createMoney;
import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.when;

import ca.ulaval.glo4003.funds.domain.Bill;
import ca.ulaval.glo4003.funds.domain.BillType;
import ca.ulaval.glo4003.funds.domain.Money;
import ca.ulaval.glo4003.funds.domain.queries.BillQueryParams;
import ca.ulaval.glo4003.funds.domain.queries.BillQueryParamsAssembler;
import ca.ulaval.glo4003.funds.services.BillProfitsCalculator;
import ca.ulaval.glo4003.funds.services.BillService;
import ca.ulaval.glo4003.profits.assemblers.ProfitByConsumptionTypeAssembler;
import ca.ulaval.glo4003.profits.assemblers.ProfitsAssembler;
import ca.ulaval.glo4003.profits.domain.ProfitByConsumptionTypeFactory;
import ca.ulaval.glo4003.times.helpers.CustomDateMother;
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

  private ProfitsAssembler profitsAssembler;
  private List<Bill> bills;

  @Mock private BillQueryParamsAssembler billQueryParamsAssembler;
  @Mock private BillQueryParams billQueryParams;
  @Mock private BillService billService;
  @Mock private BillProfitsCalculator billProfitsCalculator;
  @Mock private ProfitByConsumptionTypeFactory profitByConsumptionTypeFactory;
  @Mock private ProfitByConsumptionTypeAssembler profitByConsumptionTypeAssembler;

  @Before
  public void setup() {
    profitsAssembler = new ProfitsAssembler();
    profitsService =
        new ProfitsService(
            profitsAssembler,
            billService,
            billQueryParamsAssembler,
            billProfitsCalculator,
            profitByConsumptionTypeFactory,
            profitByConsumptionTypeAssembler);
    when(billQueryParamsAssembler.assembleWithYear(A_YEAR, BillType.PARKING_STICKER))
        .thenReturn(billQueryParams);
    when(billQueryParamsAssembler.assembleWithYear(A_YEAR, BillType.OFFENSE))
        .thenReturn(billQueryParams);
    when(billQueryParamsAssembler.assembleWithYear(A_YEAR, BillType.ACCESS_PASS))
        .thenReturn(billQueryParams);
    when(billService.getAllBillsByQueryParams(billQueryParams)).thenReturn(bills);
    when(billProfitsCalculator.calculate(bills)).thenReturn(AN_AMOUNT);
  }

  @Test
  public void whenGettingProfitsFromParkingSticker_shouldAssembleProfits() {
    profitsService.getParkingStickerProfits(A_YEAR);

    assertThat(profitsAssembler.assemble(AN_AMOUNT));
  }

  @Test
  public void whenGettingProfitsFromOffsense_shouldAssembleProfits() {
    profitsService.getOffenseProfits(A_YEAR);

    assertThat(profitsAssembler.assemble(AN_AMOUNT));
  }

  @Test
  public void whenGettingProfitsFromAccessPass_shouldAssembleProfits() {
    profitsService.getAccessPassProfits(A_YEAR);

    assertThat(profitsAssembler.assemble(AN_AMOUNT));
  }
}
