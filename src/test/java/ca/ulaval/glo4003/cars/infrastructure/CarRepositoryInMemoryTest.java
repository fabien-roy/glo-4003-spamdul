package ca.ulaval.glo4003.cars.infrastructure;

import static ca.ulaval.glo4003.cars.helpers.CarBuilder.aCar;
import static ca.ulaval.glo4003.cars.helpers.LicensePlateMother.createLicensePlate;

import ca.ulaval.glo4003.cars.domain.Car;
import ca.ulaval.glo4003.cars.domain.CarRepository;
import ca.ulaval.glo4003.cars.domain.LicensePlate;
import ca.ulaval.glo4003.cars.exceptions.NotFoundLicensePlate;
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

  @Test
  public void whenGettingCarByLicensePlate_thenReturnCar() {
    LicensePlate licensePlate = carRepository.save(car);
    Car car = carRepository.getCarByLicensePlate(licensePlate);
    Truth.assertThat(car.getLicensePlate()).isEqualTo(licensePlate);
  }

  @Test(expected = NotFoundLicensePlate.class)
  public void
      whenGettingCarByLicensePlateWithANotFoundLicensePlate_thenThrowInvalidLicensePlateException() {
    carRepository.getCarByLicensePlate(createLicensePlate());
  }
}
