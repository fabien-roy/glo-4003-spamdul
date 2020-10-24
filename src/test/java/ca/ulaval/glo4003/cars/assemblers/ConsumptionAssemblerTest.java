package ca.ulaval.glo4003.cars.assemblers;

import static com.google.common.truth.Truth.assertThat;

import ca.ulaval.glo4003.cars.domain.ConsumptionType;
import ca.ulaval.glo4003.cars.domain.ConsumptionTypeInFrench;
import org.junit.Before;
import org.junit.Test;

public class ConsumptionAssemblerTest {
  private ConsumptionAssembler consumptionAssembler;

  @Before
  public void setUp() {
    consumptionAssembler = new ConsumptionAssembler();
  }

  @Test
  public void givenGourmande_whenAssembling_thenReturnGreedy() {
    ConsumptionTypeInFrench consumptionTypeInFrench = ConsumptionTypeInFrench.GOURMANDE;

    ConsumptionType consumptionType = consumptionAssembler.assemble(consumptionTypeInFrench);

    assertThat(consumptionType).isEqualTo(ConsumptionType.GREEDY);
  }

  @Test
  public void givenEconomique_whenAssembling_thenReturnEconomic() {
    ConsumptionTypeInFrench consumptionTypeInFrench = ConsumptionTypeInFrench.ECONOMIQUE;

    ConsumptionType consumptionType = consumptionAssembler.assemble(consumptionTypeInFrench);

    assertThat(consumptionType).isEqualTo(ConsumptionType.ECONOMIC);
  }

  @Test
  public void givenHybridEconomique_whenAssembling_thenReturnEconomicalHybrid() {
    ConsumptionTypeInFrench consumptionTypeInFrench = ConsumptionTypeInFrench.HYBRIDE_ECONOMIQUE;

    ConsumptionType consumptionType = consumptionAssembler.assemble(consumptionTypeInFrench);

    assertThat(consumptionType).isEqualTo(ConsumptionType.ECONOMICAL_HYBRID);
  }

  @Test
  public void givenSuperEconomique_whenAssembling_thenReturnSuperEconomical() {
    ConsumptionTypeInFrench consumptionTypeInFrench = ConsumptionTypeInFrench.SUPER_ECONOMIQUE;

    ConsumptionType consumptionType = consumptionAssembler.assemble(consumptionTypeInFrench);

    assertThat(consumptionType).isEqualTo(ConsumptionType.SUPER_ECONOMICAL);
  }

  @Test
  public void givenZeroPollution_whenAssembling_thenReturnZeroPollution() {
    ConsumptionTypeInFrench consumptionTypeInFrench = ConsumptionTypeInFrench.ZERO_POLLUTION;

    ConsumptionType consumptionType = consumptionAssembler.assemble(consumptionTypeInFrench);

    assertThat(consumptionType).isEqualTo(ConsumptionType.ZERO_POLLUTION);
  }
}
