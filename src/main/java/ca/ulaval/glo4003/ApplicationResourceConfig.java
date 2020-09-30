package ca.ulaval.glo4003;

import ca.ulaval.glo4003.accounts.AccountResourceConfig;
import ca.ulaval.glo4003.accounts.api.AccountExceptionMapper;
import ca.ulaval.glo4003.api.time.TimeExceptionMapper;
import ca.ulaval.glo4003.api.user.UserExceptionMapper;
import ca.ulaval.glo4003.api.user.UserResource;
import ca.ulaval.glo4003.bills.api.BillExceptionMapper;
import ca.ulaval.glo4003.cars.CarResourceConfig;
import ca.ulaval.glo4003.cars.api.CarExceptionMapper;
import ca.ulaval.glo4003.cars.api.CarResource;
import ca.ulaval.glo4003.communications.CommunicationResourceConfig;
import ca.ulaval.glo4003.communications.api.CommunicationExceptionMapper;
import ca.ulaval.glo4003.files.api.FileExceptionMapper;
import ca.ulaval.glo4003.injection.time.TimeResourceConfig;
import ca.ulaval.glo4003.injection.user.UserResourceConfig;
import ca.ulaval.glo4003.interfaces.api.CatchAllExceptionMapper;
import ca.ulaval.glo4003.locations.LocationResourceConfig;
import ca.ulaval.glo4003.locations.api.LocationExceptionMapper;
import ca.ulaval.glo4003.offenses.OffenseResourceConfig;
import ca.ulaval.glo4003.offenses.api.OffenseResource;
import ca.ulaval.glo4003.parkings.ParkingResourceConfig;
import ca.ulaval.glo4003.parkings.api.ParkingExceptionMapper;
import ca.ulaval.glo4003.parkings.api.ParkingResource;
import java.util.Arrays;
import java.util.List;
import javax.ws.rs.ext.ExceptionMapper;

public class ApplicationResourceConfig {

  private static final boolean IS_DEV = true;

  private final AccountResourceConfig accountResourceConfig = new AccountResourceConfig();
  private final CarResourceConfig carResourceConfig = new CarResourceConfig();
  private final CommunicationResourceConfig communicationResourceConfig =
      new CommunicationResourceConfig();
  private final LocationResourceConfig locationResourceConfig = new LocationResourceConfig();
  private final ParkingResourceConfig parkingResourceConfig = new ParkingResourceConfig();
  private final TimeResourceConfig timeResourceConfig = new TimeResourceConfig();
  private final UserResourceConfig userResourceConfig = new UserResourceConfig();
  private final OffenseResourceConfig offenseResourceConfig = new OffenseResourceConfig();

  public CarResource createCarResource() {
    return carResourceConfig.createCarResource(accountResourceConfig.createAccountService());
  }

  public ParkingResource createParkingResource() {
    return parkingResourceConfig.createParkingResource(
        IS_DEV,
        accountResourceConfig.createAccountIdAssembler(),
        locationResourceConfig.createPostalCodeAssembler(),
        communicationResourceConfig.createEmailAddressAssembler(),
        communicationResourceConfig.createEmailSender(),
        accountResourceConfig.getAccountRepository());
  }

  public UserResource createUserResource() {
    return userResourceConfig.createUserResource(
        accountResourceConfig.getAccountRepository(),
        accountResourceConfig.createAccountFactory(),
        accountResourceConfig.createAccountIdAssembler(),
        timeResourceConfig.createCustomDateAssembler());
  }

  public OffenseResource createOffenseResource() {
    return offenseResourceConfig.createOffenseResource(
        parkingResourceConfig.getParkingStickerRepository());
  }

  public List<Class<? extends ExceptionMapper<? extends Exception>>> getExceptionMappers() {
    return Arrays.asList(
        CatchAllExceptionMapper.class,
        AccountExceptionMapper.class,
        BillExceptionMapper.class,
        CarExceptionMapper.class,
        CommunicationExceptionMapper.class,
        FileExceptionMapper.class,
        LocationExceptionMapper.class,
        ParkingExceptionMapper.class,
        TimeExceptionMapper.class,
        UserExceptionMapper.class);
  }
}
