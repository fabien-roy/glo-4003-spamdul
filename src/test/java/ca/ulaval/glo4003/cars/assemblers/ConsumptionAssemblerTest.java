package ca.ulaval.glo4003.cars.assemblers;

import static com.google.common.truth.Truth.assertThat;

import ca.ulaval.glo4003.cars.domain.ConsommationType;
import ca.ulaval.glo4003.cars.domain.ConsumptionType;
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
    ConsommationType consommationType = ConsommationType.GOURMANDE;

    ConsumptionType consumptionType = consumptionAssembler.assemble(consommationType);

    assertThat(consumptionType).isEqualTo(ConsumptionType.GREEDY);
  }

  @Test
  public void givenEconomique_whenAssembling_thenReturnEconomic() {
    ConsommationType consommationType = ConsommationType.ECONOMIQUE;

    ConsumptionType consumptionType = consumptionAssembler.assemble(consommationType);

    assertThat(consumptionType).isEqualTo(ConsumptionType.ECONOMIC);
  }

  @Test
  public void givenHybridEconomique_whenAssembling_thenReturnEconomicalHybrid() {
    ConsommationType consommationType = ConsommationType.HYBRIDE_ECONOMIQUE;

    ConsumptionType consumptionType = consumptionAssembler.assemble(consommationType);

    assertThat(consumptionType).isEqualTo(ConsumptionType.ECONOMICAL_HYBRID);
  }

  @Test
  public void givenSuperEconomique_whenAssembling_thenReturnSuperEconomical() {
    ConsommationType consommationType = ConsommationType.SUPER_ECONOMIQUE;

    ConsumptionType consumptionType = consumptionAssembler.assemble(consommationType);

    assertThat(consumptionType).isEqualTo(ConsumptionType.SUPER_ECONOMICAL);
  }

  @Test
  public void givenZeroPollution_whenAssembling_thenReturnZeroPollution() {
    ConsommationType consommationType = ConsommationType.ZERO_POLLUTION;

    ConsumptionType consumptionType = consumptionAssembler.assemble(consommationType);

    assertThat(consumptionType).isEqualTo(ConsumptionType.ZERO_POLLUTION);
  }
}
