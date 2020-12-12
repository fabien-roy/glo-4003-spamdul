package ca.ulaval.glo4003.parkings.domain.exceptions;

import static ca.ulaval.glo4003.parkings.helpers.ParkingAreaMother.createParkingAreaCode;
import static com.google.common.truth.Truth.assertThat;

import ca.ulaval.glo4003.errors.domain.exceptions.ApplicationException;
import ca.ulaval.glo4003.parkings.domain.ParkingAreaCode;
import java.util.Arrays;
import org.junit.Before;
import org.junit.Test;

public class NotFoundParkingAreaExceptionTest {
  private ApplicationException exception;

  private final ParkingAreaCode firstParkingAreaCode = createParkingAreaCode();
  private final ParkingAreaCode secondParkingAreaCode = createParkingAreaCode();

  @Before
  public void setUp() {
    exception =
        new NotFoundParkingAreaException(
            Arrays.asList(firstParkingAreaCode, secondParkingAreaCode));
  }

  @Test
  public void whenGettingDescription_thenEnumerateParkingAreas() {
    String expectedDescription =
        String.format(
            "Parking area should be one of %s or %s",
            firstParkingAreaCode.toString(), secondParkingAreaCode.toString());

    String description = exception.getDescription();

    assertThat(description).isEqualTo(expectedDescription);
  }
}
