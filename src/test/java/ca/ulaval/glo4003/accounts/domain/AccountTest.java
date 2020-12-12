package ca.ulaval.glo4003.accounts.domain;

import static ca.ulaval.glo4003.accounts.helpers.AccountBuilder.anAccount;
import static ca.ulaval.glo4003.cars.helpers.CarBuilder.aCar;

import ca.ulaval.glo4003.cars.domain.Car;
import ca.ulaval.glo4003.cars.domain.exceptions.AlreadyExistingCarException;
import java.util.Collections;
import org.junit.Test;

public class AccountTest {
  @Test(expected = AlreadyExistingCarException.class)
  public void givenAlreadyExistingCar_whenAddingCar_thenThrowAlreadyExistingCarException() {
    Car car = aCar().build();
    Account account = anAccount().withCars(Collections.singletonList(car)).build();

    account.saveCar(car);
  }
}
