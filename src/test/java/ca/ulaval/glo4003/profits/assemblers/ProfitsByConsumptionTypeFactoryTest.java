package ca.ulaval.glo4003.profits.assemblers;

import static ca.ulaval.glo4003.cars.helpers.CarMother.createConsumptionType;
import static ca.ulaval.glo4003.funds.helpers.BillBuilder.aBill;
import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.when;

import ca.ulaval.glo4003.cars.domain.ConsumptionType;
import ca.ulaval.glo4003.funds.domain.Bill;
import ca.ulaval.glo4003.funds.domain.BillsByConsumptionTypes;
import ca.ulaval.glo4003.funds.domain.Money;
import ca.ulaval.glo4003.funds.services.BillProfitsCalculator;
import ca.ulaval.glo4003.profits.domain.ProfitByConsumptionType;
import ca.ulaval.glo4003.profits.domain.ProfitsByConsumptionTypeFactory;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ProfitsByConsumptionTypeFactoryTest {

  private ProfitsByConsumptionTypeFactory profitsByConsumptionTypeFactory;
  private static final int SIZE_OF_ARRAY = 5;

  @Mock private BillProfitsCalculator billProfitsCalculator;
  private BillsByConsumptionTypes billsByConsumptionTypes;
  private ConsumptionType consumptionType = createConsumptionType();
  private Bill bill = aBill().build();
  private Money total = bill.getAmountDue().plus(bill.getAmountPaid());
  private List<ProfitByConsumptionType> profitByConsumptionTypes = new ArrayList<>();

  @Before
  public void setup() {
    profitsByConsumptionTypeFactory = new ProfitsByConsumptionTypeFactory(billProfitsCalculator);
    billsByConsumptionTypes = new BillsByConsumptionTypes();
    billsByConsumptionTypes.addBillWithConsumptionsType(consumptionType, bill);
    when(billProfitsCalculator.calculate(
            billsByConsumptionTypes.getBillByConsumptionType(consumptionType)))
        .thenReturn(total);
  }

  @Test
  public void whenCreatingProfitByConsumptionTypes_thenReturnProfitByConsumptionTypes() {
    profitByConsumptionTypes = profitsByConsumptionTypeFactory.create(billsByConsumptionTypes);

    assertThat(profitByConsumptionTypes.size()).isEqualTo(SIZE_OF_ARRAY);
    assertThat(
            findProfitByConsumptionTypeFromConsumptionType(consumptionType).getMoney().toDouble())
        .isEqualTo(total.toDouble());
  }

  private ProfitByConsumptionType findProfitByConsumptionTypeFromConsumptionType(
      ConsumptionType consumptionType) {
    for (ProfitByConsumptionType profitByConsumptionType : profitByConsumptionTypes) {
      if (profitByConsumptionType.getConsumptionType() == consumptionType) {
        return profitByConsumptionType;
      }
    }
    throw new RuntimeException();
  }
}
