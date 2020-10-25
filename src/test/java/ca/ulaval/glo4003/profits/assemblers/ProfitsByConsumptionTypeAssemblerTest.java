package ca.ulaval.glo4003.profits.assemblers;

import static ca.ulaval.glo4003.cars.helpers.CarMother.createNotZeroPullutionConsumptionTypes;
import static ca.ulaval.glo4003.funds.helpers.MoneyMother.createMoney;
import static com.google.common.truth.Truth.assertThat;

import ca.ulaval.glo4003.cars.domain.ConsumptionType;
import ca.ulaval.glo4003.funds.domain.Money;
import ca.ulaval.glo4003.profits.api.dto.ProfitsByConsumptionTypeDto;
import ca.ulaval.glo4003.profits.domain.ProfitByConsumptionType;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

public class ProfitsByConsumptionTypeAssemblerTest {

  private ProfitsByConsumptionTypeAssembler profitsByConsumptionTypeAssembler =
      new ProfitsByConsumptionTypeAssembler();
  private static ConsumptionType consumptionType = createNotZeroPullutionConsumptionTypes();
  private static Money money = createMoney();

  @Test
  public void whenAssemblingManyProfitsByConsumptionDto_thenReturnProfitsByConsumptionDtos() {
    ProfitByConsumptionType profitByConsumptionType =
        new ProfitByConsumptionType(consumptionType, money);
    List<ProfitByConsumptionType> profitByConsumptionTypes = new ArrayList<>();

    profitByConsumptionTypes.add(profitByConsumptionType);
    List<ProfitsByConsumptionTypeDto> profitsByConsumptionTypeDtos =
        profitsByConsumptionTypeAssembler.assembleMany(profitByConsumptionTypes);

    assertThat(profitsByConsumptionTypeDtos.get(0).consumptionType)
        .isEqualTo(consumptionType.toString());
    assertThat(profitsByConsumptionTypeDtos.get(0).profits).isEqualTo(money.toDouble());
  }
}
