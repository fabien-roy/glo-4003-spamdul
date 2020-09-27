package ca.ulaval.glo4003.domain.car.LicensePlate;

import static com.google.common.truth.Truth.assertThat;

import ca.ulaval.glo4003.domain.car.exceptions.InvalidLicensePlateException;
import org.junit.Before;
import org.junit.Test;

public class LicensePlateAssemblerTest {

  private static final String LICENSE_PLATE_WITH_SPECIAL_CHARS = "@121!!!...";
  private static final String LICENSE_PLATE_WITH_INVALID_LENGTH = "B";
  private static final String VALID_LICENSE_PLATE = "SPEAD";

  private LicensePlateAssembler licensePlateAssembler;

  @Before
  public void setup() {
    licensePlateAssembler = new LicensePlateAssembler();
  }

  @Test
  public void whenAssemblingLicensePlate_shouldReturnLicensePlate() {
    LicensePlate licensePlate = licensePlateAssembler.assemble(VALID_LICENSE_PLATE);

    assertThat(licensePlate.equals(new LicensePlate(VALID_LICENSE_PLATE)));
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
