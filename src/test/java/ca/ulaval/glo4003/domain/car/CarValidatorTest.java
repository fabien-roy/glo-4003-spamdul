package ca.ulaval.glo4003.domain.car;

import ca.ulaval.glo4003.api.Car.dto.CarDTO;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

class CarValidatorTest {

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
  public void givenInvalidCarDTO_whenValidatingDTO_shouldThrowException() {}
}
