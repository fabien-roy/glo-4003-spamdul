package ca.ulaval.glo4003.accesspasses.infrastructure;

import static ca.ulaval.glo4003.accesspasses.helpers.AccessPassTypeBuilder.anAccessPassType;
import static com.google.common.truth.Truth.assertThat;

import ca.ulaval.glo4003.accesspasses.domain.AccessPassType;
import ca.ulaval.glo4003.cars.domain.exceptions.InvalidConsumptionTypeException;
import org.junit.Before;
import org.junit.Test;

public class AccessPassTypeInMemoryRepositoryTest {
  private AccessPassTypeInMemoryRepository accessPassPriceByCarConsumptionInMemoryRepository;

  private final AccessPassType accessPassType = anAccessPassType().build();

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

    assertThat(passPriceByCarConsumption).isSameInstanceAs(accessPassType);
  }

  @Test(expected = InvalidConsumptionTypeException.class)
  public void
      givenNonExistentParkingArea_whenGettingParkingArea_thenThrowNotFoundParkingAreaException() {
    accessPassPriceByCarConsumptionInMemoryRepository.findByConsumptionType(
        accessPassType.getConsumptionTypes());
  }
}
