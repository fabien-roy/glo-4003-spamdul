package ca.ulaval.glo4003.access.infrastructure;

import static ca.ulaval.glo4003.access.helper.AccessPassPriceByCarConsumptionBuilder.anAccessPassPriceByConsumption;

import ca.ulaval.glo4003.access.domain.AccessPassPriceByCarConsumption;
import ca.ulaval.glo4003.cars.exceptions.InvalidConsumptionType;
import com.google.common.truth.Truth;
import org.junit.Before;
import org.junit.Test;

public class AccessPassPriceByCarConsumptionInMemoryRepositoryTest {
  private AccessPassPriceByCarConsumptionInMemoryRepository
      accessPassPriceByCarConsumptionInMemoryRepository;
  private AccessPassPriceByCarConsumption accessPassPriceByCarConsumption =
      anAccessPassPriceByConsumption().build();

  @Before
  public void setUp() {
    accessPassPriceByCarConsumptionInMemoryRepository =
        new AccessPassPriceByCarConsumptionInMemoryRepository();
  }

  @Test
  public void whenSavingAccessPassPriceByCArConsumption_thenItCanBeFound() {
    accessPassPriceByCarConsumptionInMemoryRepository.save(accessPassPriceByCarConsumption);

    AccessPassPriceByCarConsumption passPriceByCarConsumption =
        accessPassPriceByCarConsumptionInMemoryRepository.findByConsumptionType(
            accessPassPriceByCarConsumption.getConsumptionTypes());

    Truth.assertThat(passPriceByCarConsumption).isSameInstanceAs(accessPassPriceByCarConsumption);
  }

  @Test(expected = InvalidConsumptionType.class)
  public void
      givenNonExistentParkingArea_whenGettingParkingArea_thenThrowNotFoundParkingAreaException() {
    accessPassPriceByCarConsumptionInMemoryRepository.findByConsumptionType(
        accessPassPriceByCarConsumption.getConsumptionTypes());
  }
}
