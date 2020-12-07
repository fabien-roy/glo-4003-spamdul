package ca.ulaval.glo4003.cars.services;

import static ca.ulaval.glo4003.accounts.helpers.AccountBuilder.anAccount;
import static ca.ulaval.glo4003.accounts.helpers.AccountMother.createAccountId;
import static ca.ulaval.glo4003.cars.helpers.CarBuilder.aCar;
import static ca.ulaval.glo4003.cars.helpers.CarDtoBuilder.aCarDto;
import static ca.ulaval.glo4003.cars.helpers.CarMother.createCars;
import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import ca.ulaval.glo4003.accounts.domain.Account;
import ca.ulaval.glo4003.accounts.domain.AccountId;
import ca.ulaval.glo4003.accounts.services.AccountService;
import ca.ulaval.glo4003.cars.domain.Car;
import ca.ulaval.glo4003.cars.services.assemblers.CarAssembler;
import ca.ulaval.glo4003.cars.services.converters.CarConverter;
import ca.ulaval.glo4003.cars.services.dto.CarDto;
import java.util.Collections;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CarServiceTest {

  @Mock private CarConverter carConverter;
  @Mock private CarAssembler carAssembler;
  @Mock private AccountService accountService;

  private CarService carService;
  private final AccountId accountId = createAccountId();
  private final CarDto carDto = aCarDto().build();
  private final Car car = aCar().build();

  private final List<Car> cars = createCars();
  private final Car carWithLicensePlate =
      aCar().withLicensePlate(cars.get(0).getLicensePlate()).build();
  private final Account accountWithCars =
      anAccount().withCars(Collections.singletonList(carWithLicensePlate)).build();
  private final CarDto carDtoWithPlate =
      aCarDto().withLicensePlate(cars.get(0).getLicensePlate().toString()).build();

  @Before
  public void setUp() {
    carService = new CarService(carConverter, carAssembler, accountService);

    when(carConverter.convert(carDto)).thenReturn(car);
    when(accountService.getAccount(accountId.toString())).thenReturn(accountWithCars);
    when(carAssembler.assemble(Collections.singletonList(carWithLicensePlate)))
        .thenReturn(Collections.singletonList(carDtoWithPlate));
    when(accountService.getAccount(accountWithCars.getId().toString())).thenReturn(accountWithCars);
  }

  @Test
  public void whenAddingCar_thenAddCarToAccount() {
    carService.addCar(carDto, accountId.toString());

    verify(accountService).addCarToAccount(accountWithCars.getId(), car);
  }

  @Test
  public void whenGettingCars_ThenReturnAccountCars() {
    List<CarDto> carsFromService = carService.getCars(accountWithCars.getId().toString());

    assertThat(accountWithCars.getCars().get(0).getLicensePlate().toString())
        .isEqualTo(carsFromService.get(0).licensePlate);
  }
}
