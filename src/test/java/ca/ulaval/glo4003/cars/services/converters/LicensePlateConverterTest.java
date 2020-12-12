package ca.ulaval.glo4003.cars.services.converters;

import static ca.ulaval.glo4003.cars.helpers.LicensePlateMother.createLicensePlate;
import static com.google.common.truth.Truth.assertThat;

import ca.ulaval.glo4003.cars.domain.LicensePlate;
import ca.ulaval.glo4003.cars.domain.exceptions.InvalidLicensePlateException;
import org.junit.Before;
import org.junit.Test;

public class LicensePlateConverterTest {

  private static final String LICENSE_PLATE_WITH_SPECIAL_CHARS = "@121!!!...";
  private static final String LICENSE_PLATE_WITH_INVALID_LENGTH = "B";
  private static final LicensePlate LICENSE_PLATE = createLicensePlate();

  private LicensePlateConverter licensePlateConverter;

  @Before
  public void setUp() {
    licensePlateConverter = new LicensePlateConverter();
  }

  @Test
  public void whenConverting_shouldReturnLicensePlate() {
    LicensePlate licensePlate = licensePlateConverter.convert(LICENSE_PLATE.toString());

    assertThat(licensePlate.equals(LICENSE_PLATE));
  }

  @Test(expected = InvalidLicensePlateException.class)
  public void
      givenLicensePlateWithSpecialChars_whenConverting_shouldThrowInvalidLicensePlateException() {
    licensePlateConverter.convert(LICENSE_PLATE_WITH_SPECIAL_CHARS);
  }

  @Test(expected = InvalidLicensePlateException.class)
  public void
      givenLicensePlateWithInvalidLength_whenConverting_shouldThrowInvalidLicensePlateException() {
    licensePlateConverter.convert(LICENSE_PLATE_WITH_INVALID_LENGTH);
  }

  @Test(expected = InvalidLicensePlateException.class)
  public void givenNullLicensePlate_whenConverting_shouldThrowInvalidLicensePlateException() {
    licensePlateConverter.convert(null);
  }
}
