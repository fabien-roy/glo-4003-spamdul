package ca.ulaval.glo4003.cars.domain.exceptions;

import static ca.ulaval.glo4003.cars.helpers.LicensePlateMother.createLicensePlate;
import static com.google.common.truth.Truth.assertThat;

import ca.ulaval.glo4003.cars.domain.LicensePlate;
import ca.ulaval.glo4003.errors.domain.exceptions.ApplicationException;
import org.junit.Before;
import org.junit.Test;

public class NotFoundCarExceptionTest {

  private ApplicationException exception;

  private final LicensePlate licensePlate = createLicensePlate();

  @Before
  public void setUp() {
    exception = new NotFoundCarException(licensePlate);
  }

  @Test
  public void whenGettingDescription_thenWriteLicensePlate() {
    String expectedDescription =
        String.format("Car with license plate %s was not found", licensePlate.toString());

    String description = exception.getDescription();

    assertThat(description).isEqualTo(expectedDescription);
  }
}
