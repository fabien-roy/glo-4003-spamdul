package ca.ulaval.glo4003.domain.car;

import ca.ulaval.glo4003.api.car.dto.CarDTO;
import ca.ulaval.glo4003.domain.car.exceptions.InvalidCarYearException;
import ca.ulaval.glo4003.domain.car.exceptions.InvalidLicensePlateException;
import org.junit.Before;
import org.junit.Test;

public class CarValidatorTest {

  private static String VALID_LICENSE_PLATE = "B2C WW9";
  private static int VALID_YEAR = 2010;
  private static int INVALID_YEAR = 3000;
  private static String INVALID_LICENSE_PLATE_LENGTH = "C";
  private static String INVALID_LICENSE_PLATE_CHARACTERS = "C1V $W9";

  private CarValidator carValidator;

  @Before
  public void setup() {
    carValidator = new CarValidator();
  }

  @Test(expected = InvalidCarYearException.class)
  public void givenCarDTOWithInvalidYear_whenValidatingDTO_shouldThrowException() {
    CarDTO validDTO = new CarDTO("manufacturer", "model", INVALID_YEAR, VALID_LICENSE_PLATE);

    carValidator.validate(validDTO);
  }

  @Test(expected = InvalidLicensePlateException.class)
  public void givenCarDTOWithInvalidLicensePlateLength_whenValidatingDTO_shouldThrowException() {
    CarDTO invalidDTO =
        new CarDTO("manufacturer", "model", VALID_YEAR, INVALID_LICENSE_PLATE_LENGTH);

    carValidator.validate(invalidDTO);
  }

  @Test(expected = InvalidLicensePlateException.class)
  public void
      givenCarDTOWithInvalidLicensePlateCharacters_whenValidatingDTO_shouldThrowException() {
    CarDTO invalidDTO =
        new CarDTO("manufacturer", "model", VALID_YEAR, INVALID_LICENSE_PLATE_CHARACTERS);

    carValidator.validate(invalidDTO);
  }
}
