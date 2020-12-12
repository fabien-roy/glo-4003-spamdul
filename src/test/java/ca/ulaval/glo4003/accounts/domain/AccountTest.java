package ca.ulaval.glo4003.accounts.domain;

import ca.ulaval.glo4003.cars.domain.Car;
import ca.ulaval.glo4003.funds.domain.exceptions.NotFoundBillException;
import org.junit.Test;

import java.util.Collections;

import static ca.ulaval.glo4003.accounts.helpers.AccountBuilder.anAccount;
import static ca.ulaval.glo4003.cars.helpers.CarBuilder.aCar;

public class AccountTest {
  @Test(expected = NotFoundBillException.class)
  public void givenAlreadyExistingCar_whenAddingCar_thenAlreadyExistingCar() {
    Car car = aCar().build();
    Account account = anAccount().withCars(Collections.singletonList(car)).build();

    account.saveCar(car);
  }
}
