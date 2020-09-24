package ca.ulaval.glo4003.domain.car;

import ca.ulaval.glo4003.api.car.dto.CarDTO;
import ca.ulaval.glo4003.domain.car.exceptions.InvalidCarYearException;
import ca.ulaval.glo4003.domain.car.exceptions.InvalidLicenseNumberException;
import org.junit.Before;
import org.junit.Test;

public class CarValidatorTest {

  private static int INVALID_YEAR = 3000;
  private static String INVALID_LICENSE_PLATE = "C";

  private CarValidator carValidator;

  @Before
  public void setup() {
    carValidator = new CarValidator();
  }

  @Test(expected = InvalidCarYearException.class)
  public void givenCarDTOWithInvalidYear_whenValidatingDTO_shouldThrowException() {
    CarDTO validDTO = new CarDTO("manufacturer", "model", INVALID_YEAR, "XXXXXX");

    carValidator.validate(validDTO);
  }

  @Test(expected = InvalidLicenseNumberException.class)
  public void givenCarDTOWithInvalidLicensePlate_whenValidatingDTO_shouldThrowException() {
    CarDTO validDTO = new CarDTO("manufacturer", "model", 2020, INVALID_LICENSE_PLATE);

    carValidator.validate(validDTO);
  }
}
