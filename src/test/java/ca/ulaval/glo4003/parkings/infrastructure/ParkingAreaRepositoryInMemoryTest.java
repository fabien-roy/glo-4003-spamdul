package ca.ulaval.glo4003.parkings.infrastructure;

import static ca.ulaval.glo4003.parkings.helpers.ParkingAreaBuilder.aParkingArea;
import static com.google.common.truth.Truth.assertThat;

import ca.ulaval.glo4003.parkings.domain.ParkingArea;
import ca.ulaval.glo4003.parkings.domain.ParkingAreaRepository;
import ca.ulaval.glo4003.parkings.exceptions.NotFoundParkingAreaException;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class ParkingAreaRepositoryInMemoryTest {
  private ParkingAreaRepository parkingAreaRepository;

  private final ParkingArea parkingArea = aParkingArea().build();

  @Before
  public void setUp() {
    parkingAreaRepository = new ParkingAreaRepositoryInMemory();
  }

  @Test
  public void givenSavedParkingArea_whenGetting_thenReturnParkingArea() {
    parkingAreaRepository.save(parkingArea);

    ParkingArea foundParkingArea = parkingAreaRepository.get(parkingArea.getCode());

    assertThat(foundParkingArea).isSameInstanceAs(parkingArea);
  }

  @Test
  public void whenGettingAllParkingArea_thenReturnAllParkingArea() {
    parkingAreaRepository.save(parkingArea);

    List<ParkingArea> parkingArea = parkingAreaRepository.getAllArea();

    assertThat(parkingArea.size()).isEqualTo(1);
  }

  @Test(expected = NotFoundParkingAreaException.class)
  public void givenNoSavedParkingArea_whenGetting_thenThrowNotFoundParkingAreaException() {
    parkingAreaRepository.get(parkingArea.getCode());
  }
}
