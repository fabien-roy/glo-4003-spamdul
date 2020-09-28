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

  private final AccountResourceConfig accountResourceConfig;
  private final CarResourceConfig carResourceConfig;
  private final ContactResourceConfig contactResourceConfig;
  private final LocationResourceConfig locationResourceConfig;
  private final ParkingResourceConfig parkingResourceConfig;
  private final TimeResourceConfig timeResourceConfig;
  private final UserResourceConfig userResourceConfig;

  public ApplicationResourceConfig() {
    accountResourceConfig = new AccountResourceConfig();
    carResourceConfig = new CarResourceConfig();
    contactResourceConfig = new ContactResourceConfig();
    locationResourceConfig = new LocationResourceConfig();
    parkingResourceConfig = new ParkingResourceConfig();
    userResourceConfig = new UserResourceConfig();
    timeResourceConfig = new TimeResourceConfig();
  }

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
