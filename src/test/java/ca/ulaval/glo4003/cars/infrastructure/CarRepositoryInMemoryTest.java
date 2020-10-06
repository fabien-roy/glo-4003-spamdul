package ca.ulaval.glo4003.cars.infrastructure;

import static ca.ulaval.glo4003.cars.helpers.CarBuilder.aCar;

import ca.ulaval.glo4003.cars.domain.Car;
import ca.ulaval.glo4003.cars.domain.CarRepository;
import ca.ulaval.glo4003.cars.domain.LicensePlate;
import com.google.common.truth.Truth;
import org.junit.Before;
import org.junit.Test;

public class CarRepositoryInMemoryTest {
  private CarRepository carRepository;

  private final Car car = aCar().build();

  @Before
  public void setUp() {
    carRepository = new CarRepositoryInMemory();
  }

  @Test
  public void whenSavingCar_thenReturnId() {
    LicensePlate licensePlate = carRepository.save(car);

    Truth.assertThat(licensePlate).isSameInstanceAs(car.getLicensePlate());
  }
}
