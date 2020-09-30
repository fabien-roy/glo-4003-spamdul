package ca.ulaval.glo4003.parkings.infrastructure;

import static ca.ulaval.glo4003.parkings.helpers.ParkingAreaBuilder.aParkingArea;

import ca.ulaval.glo4003.parkings.domain.ParkingArea;
import ca.ulaval.glo4003.parkings.domain.ParkingAreaRepository;
import ca.ulaval.glo4003.parkings.exceptions.NotFoundParkingAreaException;
import com.google.common.truth.Truth;
import org.junit.Before;
import org.junit.Test;

public class ParkingAreaRepositoryInMemoryTest {
  private ParkingArea parkingArea;

  private ParkingAreaRepository parkingAreaRepository;

  @Before
  public void setUp() {
    parkingAreaRepository = new ParkingAreaRepositoryInMemory();
    parkingArea = aParkingArea().build();
  }

  @Test
  public void whenSavingParkingArea_thenParkingAreaCanBeFound() {
    parkingAreaRepository.save(parkingArea);

    ParkingArea foundParkingArea = parkingAreaRepository.findByCode(parkingArea.getCode());

    Truth.assertThat(foundParkingArea).isSameInstanceAs(parkingArea);
  }

  @Test(expected = NotFoundParkingAreaException.class)
  public void
      givenNonExistentParkingArea_whenGettingParkingArea_thenThrowNotFoundParkingAreaException() {
    parkingAreaRepository.findByCode(parkingArea.getCode());
  }
}
