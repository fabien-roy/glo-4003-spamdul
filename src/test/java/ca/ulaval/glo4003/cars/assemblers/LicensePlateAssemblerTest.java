package ca.ulaval.glo4003.cars.assemblers;

import static ca.ulaval.glo4003.cars.helpers.LicensePlateMother.createLicensePlate;
import static com.google.common.truth.Truth.assertThat;

import ca.ulaval.glo4003.cars.domain.LicensePlate;
import ca.ulaval.glo4003.cars.exceptions.InvalidLicensePlateException;
import org.junit.Before;
import org.junit.Test;

public class LicensePlateAssemblerTest {

  private static final String LICENSE_PLATE_WITH_SPECIAL_CHARS = "@121!!!...";
  private static final String LICENSE_PLATE_WITH_INVALID_LENGTH = "B";
  private static final LicensePlate LICENSE_PLATE = createLicensePlate();

  private LicensePlateAssembler licensePlateAssembler;

  @Before
  public void setup() {
    licensePlateAssembler = new LicensePlateAssembler();
  }

  @Test
  public void whenAssemblingLicensePlate_shouldReturnLicensePlate() {
    LicensePlate licensePlate = licensePlateAssembler.assemble(LICENSE_PLATE.toString());

    assertThat(licensePlate.equals(LICENSE_PLATE));
  }

  @Test(expected = InvalidLicensePlateException.class)
  public void
      givenLicensePlateWithSpecialChars_whenAssemblingLicensePlate_shouldThrowInvalidLicensePlateException() {
    licensePlateAssembler.assemble(LICENSE_PLATE_WITH_SPECIAL_CHARS);
  }

  @Test(expected = InvalidLicensePlateException.class)
  public void
      givenLicensePlateWithInvalidLength_whenAssemblingLicensePlate_shouldThrowInvalidLicensePlateException() {
    licensePlateAssembler.assemble(LICENSE_PLATE_WITH_INVALID_LENGTH);
  }
}
