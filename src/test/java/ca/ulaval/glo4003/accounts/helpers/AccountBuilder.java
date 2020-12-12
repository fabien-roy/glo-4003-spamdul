package ca.ulaval.glo4003.accounts.helpers;

import static ca.ulaval.glo4003.accounts.helpers.AccountMother.createAccountId;
import static ca.ulaval.glo4003.users.helpers.UserBuilder.aUser;

import ca.ulaval.glo4003.accesspasses.domain.AccessPass;
import ca.ulaval.glo4003.accounts.domain.Account;
import ca.ulaval.glo4003.accounts.domain.AccountId;
import ca.ulaval.glo4003.cars.domain.Car;
import ca.ulaval.glo4003.funds.domain.Bill;
import ca.ulaval.glo4003.parkings.domain.ParkingSticker;
import ca.ulaval.glo4003.users.domain.User;
import java.util.ArrayList;
import java.util.List;

public class AccountBuilder {
  private AccountId id = createAccountId();
  private User user = aUser().build();
  private List<AccessPass> accessPasses = new ArrayList<>();
  private List<Bill> bills = new ArrayList<>();
  private List<Car> cars = new ArrayList<>();
  private List<ParkingSticker> parkingStickers = new ArrayList<>();

  private AccountBuilder() {}

  public static AccountBuilder anAccount() {
    return new AccountBuilder();
  }

  public AccountBuilder withId(AccountId id) {
    this.id = id;
    return this;
  }

  public AccountBuilder withAccessPasses(List<AccessPass> accessPasses) {
    this.accessPasses = accessPasses;
    return this;
  }

  public AccountBuilder withBills(List<Bill> bills) {
    this.bills = bills;
    return this;
  }

  public AccountBuilder withCars(List<Car> cars) {
    this.cars = cars;
    return this;
  }

  public AccountBuilder withParkingSticker(List<ParkingSticker> parkingStickers) {
    this.parkingStickers = parkingStickers;
    return this;
  }

  public Account build() {
    Account account = new Account(id, user);
    accessPasses.forEach(account::addAccessPass);
    bills.forEach(account::addBill);
    cars.forEach(account::saveCar);
    parkingStickers.forEach(account::addParkingSticker);
    return account;
  }
}
