package ca.ulaval.glo4003.cars.infrastructure;

import static ca.ulaval.glo4003.cars.helpers.CarBuilder.aCar;
import static ca.ulaval.glo4003.cars.helpers.LicensePlateMother.createLicensePlate;
import static com.google.common.truth.Truth.assertThat;

import ca.ulaval.glo4003.cars.domain.Car;
import ca.ulaval.glo4003.cars.domain.CarRepository;
import ca.ulaval.glo4003.cars.domain.LicensePlate;
import ca.ulaval.glo4003.cars.exceptions.NotFoundLicensePlateException;
import java.util.ArrayList;
import java.util.List;
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

    assertThat(licensePlate).isSameInstanceAs(car.getLicensePlate());
  }

  @Test
  public void whenGettingCar_thenReturnCar() {
    carRepository.save(car);

    Car foundCar = carRepository.get(car.getLicensePlate());

    assertThat(foundCar).isSameInstanceAs(car);
  }

  @Test
  public void whenGettingCars_thenReturnCars() {
    List<LicensePlate> licensePlates = new ArrayList<>();
    licensePlates.add(car.getLicensePlate());
    carRepository.save(car);

    List<Car> cars = carRepository.getCars(licensePlates);

    assertThat(cars).contains(car);
  }

  @Test(expected = NotFoundLicensePlateException.class)
  public void givenNonExistentLicensePlate_whenGettingCar_thenThrowNotFoundLicensePlateException() {
    LicensePlate nonExistentLicensePlate = createLicensePlate();

    carRepository.get(nonExistentLicensePlate);
  }
}
