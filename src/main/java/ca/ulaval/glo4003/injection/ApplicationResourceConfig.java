package ca.ulaval.glo4003.injection;

import ca.ulaval.glo4003.api.car.CarResource;
import ca.ulaval.glo4003.api.contact.ContactResource;
import ca.ulaval.glo4003.api.parking.ParkingResource;
import ca.ulaval.glo4003.api.user.UserResource;
import ca.ulaval.glo4003.injection.account.AccountResourceConfig;
import ca.ulaval.glo4003.injection.car.CarResourceConfig;
import ca.ulaval.glo4003.injection.contact.ContactResourceConfig;
import ca.ulaval.glo4003.injection.location.LocationResourceConfig;
import ca.ulaval.glo4003.injection.parking.ParkingResourceConfig;
import ca.ulaval.glo4003.injection.time.TimeResourceConfig;
import ca.ulaval.glo4003.injection.user.UserResourceConfig;

public class ApplicationResourceConfig {

  private static final boolean IS_DEV = true;

  private final AccountResourceConfig accountResourceConfig = new AccountResourceConfig();
  private final CarResourceConfig carResourceConfig = new CarResourceConfig();
  private final ContactResourceConfig contactResourceConfig = new ContactResourceConfig();
  private final LocationResourceConfig locationResourceConfig = new LocationResourceConfig();
  private final ParkingResourceConfig parkingResourceConfig = new ParkingResourceConfig();
  private final TimeResourceConfig timeResourceConfig = new TimeResourceConfig();
  private final UserResourceConfig userResourceConfig = new UserResourceConfig();

  public CarResource createCarResource() {
    return carResourceConfig.createCarResource(accountResourceConfig.createAccountService());
  }

  public ContactResource createContactResource() {
    return contactResourceConfig.createContactResource(IS_DEV);
  }

  public ParkingResource createParkingResource() {
    return parkingResourceConfig.createParkingResource(
        IS_DEV,
        accountResourceConfig.createAccountIdAssembler(),
        locationResourceConfig.createPostalCodeAssembler(),
        accountResourceConfig.getAccountRepository());
  }

  public UserResource createUserResource() {
    return userResourceConfig.createUserResource(
        accountResourceConfig.getAccountRepository(),
        accountResourceConfig.createAccountFactory(),
        accountResourceConfig.createAccountIdAssembler(),
        timeResourceConfig.createCustomDateAssembler());
  }
}
