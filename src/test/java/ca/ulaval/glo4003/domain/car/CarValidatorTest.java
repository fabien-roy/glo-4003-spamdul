package ca.ulaval.glo4003.domain.car;

import ca.ulaval.glo4003.api.Car.dto.CarDTO;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class CarValidatorTest {

  private static int INVALID_YEAR = 3000;
  private static String INVALID_LICENSE_PLATE = "C";

  private CarValidator carValidator;

  @Before
  public void setup() {
    carValidator = new CarValidator();
  }

  @Test
  public void givenValidCarDTO_whenValidatingDTO_shouldNotThrowException() {
    CarDTO validDTO = new CarDTO("manufacturer", "model", 2020, "XXXXXX");

    Assertions.assertDoesNotThrow(() -> carValidator.validate(validDTO));
  }

  @Test
  public void givenCarDTOWithInvalidYear_whenValidatingDTO_shouldThrowException() {
    CarDTO validDTO = new CarDTO("manufacturer", "model", INVALID_YEAR, "XXXXXX");

    Assertions.assertThrows(InvalidCarException.class, () -> carValidator.validate(validDTO));
  }

  @Test
  public void givenCarDTOWithInvalidLicensePlate_whenValidatingDTO_shouldThrowException() {
    CarDTO validDTO = new CarDTO("manufacturer", "model", 2020, INVALID_LICENSE_PLATE);

    Assertions.assertThrows(InvalidCarException.class, () -> carValidator.validate(validDTO));
  }
}
