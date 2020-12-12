package ca.ulaval.glo4003.accounts.domain;

import static ca.ulaval.glo4003.accounts.helpers.AccountBuilder.anAccount;
import static ca.ulaval.glo4003.cars.helpers.CarBuilder.aCar;
import static ca.ulaval.glo4003.funds.helpers.BillMother.createBillId;

import ca.ulaval.glo4003.cars.domain.Car;
import ca.ulaval.glo4003.cars.domain.exceptions.AlreadyExistingCarException;
import ca.ulaval.glo4003.funds.domain.BillId;
import ca.ulaval.glo4003.funds.domain.exceptions.BillNotFoundException;
import java.util.Collections;
import org.junit.Test;

public class AccountTest {
  @Test(expected = BillNotFoundException.class)
  public void whenVerifyAccountHasBillIdAndItDoesNot_thenThrowBillNotFound() {
    Account account = anAccount().build();
    BillId billId = createBillId();

    account.verifyAccountHasBillId(billId);
  }

  @Test(expected = AlreadyExistingCarException.class)
  public void givenAlreadyExistingCar_whenAddingCar_thenAlreadyExistingCar() {
    Car car = aCar().build();
    Account account = anAccount().withCars(Collections.singletonList(car)).build();

    account.saveCar(car);
  }
}
