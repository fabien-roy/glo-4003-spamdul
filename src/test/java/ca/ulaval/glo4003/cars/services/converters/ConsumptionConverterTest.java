package ca.ulaval.glo4003.cars.services.converters;

import static com.google.common.truth.Truth.assertThat;

import ca.ulaval.glo4003.cars.domain.ConsumptionType;
import ca.ulaval.glo4003.cars.domain.ConsumptionTypeInFrench;
import org.junit.Before;
import org.junit.Test;

public class ConsumptionConverterTest {
  private ConsumptionConverter consumptionConverter;

  @Before
  public void setUp() {
    consumptionConverter = new ConsumptionConverter();
  }

  @Test
  public void givenGourmande_whenConverting_thenReturnGreedy() {
    ConsumptionTypeInFrench consumptionTypeInFrench = ConsumptionTypeInFrench.GOURMANDE;

    ConsumptionType consumptionType = consumptionConverter.convert(consumptionTypeInFrench);

    assertThat(consumptionType).isEqualTo(ConsumptionType.GREEDY);
  }

  @Test
  public void givenEconomique_whenConverting_thenReturnEconomic() {
    ConsumptionTypeInFrench consumptionTypeInFrench = ConsumptionTypeInFrench.ECONOMIQUE;

    ConsumptionType consumptionType = consumptionConverter.convert(consumptionTypeInFrench);

    assertThat(consumptionType).isEqualTo(ConsumptionType.ECONOMIC);
  }

  @Test
  public void givenHybridEconomique_whenConverting_thenReturnEconomicalHybrid() {
    ConsumptionTypeInFrench consumptionTypeInFrench = ConsumptionTypeInFrench.HYBRIDE_ECONOMIQUE;

    ConsumptionType consumptionType = consumptionConverter.convert(consumptionTypeInFrench);

    assertThat(consumptionType).isEqualTo(ConsumptionType.ECONOMICAL_HYBRID);
  }

  @Test
  public void givenSuperEconomique_whenConverting_thenReturnSuperEconomical() {
    ConsumptionTypeInFrench consumptionTypeInFrench = ConsumptionTypeInFrench.SUPER_ECONOMIQUE;

    ConsumptionType consumptionType = consumptionConverter.convert(consumptionTypeInFrench);

    assertThat(consumptionType).isEqualTo(ConsumptionType.SUPER_ECONOMICAL);
  }

  @Test
  public void givenZeroPollution_whenConverting_thenReturnZeroPollution() {
    ConsumptionTypeInFrench consumptionTypeInFrench = ConsumptionTypeInFrench.ZERO_POLLUTION;

    ConsumptionType consumptionType = consumptionConverter.convert(consumptionTypeInFrench);

    assertThat(consumptionType).isEqualTo(ConsumptionType.ZERO_POLLUTION);
  }
}
