package ca.ulaval.glo4003.access.infrastructure;

import static ca.ulaval.glo4003.access.helpers.AccessPassTypeBuilder.anAccessPassType;

import ca.ulaval.glo4003.access.domain.AccessPassType;
import ca.ulaval.glo4003.cars.exceptions.InvalidConsumptionType;
import com.google.common.truth.Truth;
import org.junit.Before;
import org.junit.Test;

public class AccessPassTypeInMemoryRepositoryTest {
  private AccessPassTypeInMemoryRepository accessPassPriceByCarConsumptionInMemoryRepository;
  private AccessPassType accessPassType = anAccessPassType().build();

  @Before
  public void setUp() {
    accessPassPriceByCarConsumptionInMemoryRepository = new AccessPassTypeInMemoryRepository();
  }

  @Test
  public void whenSavingAccessPassPriceByCArConsumption_thenItCanBeFound() {
    accessPassPriceByCarConsumptionInMemoryRepository.save(accessPassType);

    AccessPassType passPriceByCarConsumption =
        accessPassPriceByCarConsumptionInMemoryRepository.findByConsumptionType(
            accessPassType.getConsumptionTypes());

    Truth.assertThat(passPriceByCarConsumption).isSameInstanceAs(accessPassType);
  }

  @Test(expected = InvalidConsumptionType.class)
  public void
      givenNonExistentParkingArea_whenGettingParkingArea_thenThrowNotFoundParkingAreaException() {
    accessPassPriceByCarConsumptionInMemoryRepository.findByConsumptionType(
        accessPassType.getConsumptionTypes());
  }
}
