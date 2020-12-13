package ca.ulaval.glo4003.accesspasses.domain.exceptions;

import static ca.ulaval.glo4003.accesspasses.helpers.AccessPassMother.createAccessPassCode;
import static ca.ulaval.glo4003.cars.helpers.LicensePlateMother.createLicensePlate;
import static com.google.common.truth.Truth.assertThat;

import ca.ulaval.glo4003.accesspasses.domain.AccessPassCode;
import ca.ulaval.glo4003.cars.domain.LicensePlate;
import ca.ulaval.glo4003.errors.domain.exceptions.ApplicationException;
import org.junit.Test;

public class NotFoundAccessPassExceptionTest {

  private final AccessPassCode accessPassCode = createAccessPassCode();
  private final LicensePlate licensePlate = createLicensePlate();

  @Test
  public void givenAccessPassCode_whenGettingDescription_thenWriteAccessPassCode() {
    ApplicationException exception = new NotFoundAccessPassException(accessPassCode);
    String expectedDescription =
        String.format("Access pass with code %s was not found", accessPassCode.toString());

    String description = exception.getDescription();

    assertThat(description).isEqualTo(expectedDescription);
  }

  @Test
  public void givenLicensePlate_whenGettingDescription_thenWriteLicensePlate() {
    ApplicationException exception = new NotFoundAccessPassException(licensePlate);
    String expectedDescription =
        String.format(
            "Access pass associated with license plate %s was not found", licensePlate.toString());

    String description = exception.getDescription();

    assertThat(description).isEqualTo(expectedDescription);
  }
}
