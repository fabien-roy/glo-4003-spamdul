package ca.ulaval.glo4003.cars.services.assemblers;

import static ca.ulaval.glo4003.cars.helpers.CarBuilder.aCar;
import static com.google.common.truth.Truth.assertThat;

import ca.ulaval.glo4003.cars.domain.Car;
import ca.ulaval.glo4003.cars.services.dto.CarDto;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class CarAssemblerTest {

  private CarAssembler carAssembler;

  private final Car car = aCar().build();

  @Before
  public void setUp() {
    carAssembler = new CarAssembler();
  }

  @Test
  public void whenAssemblingCar_thenReturnCarDto() {
    CarDto carDto = carAssembler.assemble(car);

    assertThat(carDto.licensePlate).isEqualTo(car.getLicensePlate().toString());
  }

  @Test
  public void whenAssemblingCars_thenReturnCarsDto() {
    List<Car> cars = new ArrayList<>();
    cars.add(car);

    List<CarDto> carsDto = carAssembler.assemble(cars);

    assertThat(carsDto.get(0).licensePlate).isEqualTo(cars.get(0).getLicensePlate().toString());
  }
}
