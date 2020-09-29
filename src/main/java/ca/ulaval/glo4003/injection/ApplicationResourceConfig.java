package ca.ulaval.glo4003.injection;

import ca.ulaval.glo4003.api.account.AccountExceptionMapper;
import ca.ulaval.glo4003.api.bill.BillExceptionMapper;
import ca.ulaval.glo4003.api.car.CarExceptionMapper;
import ca.ulaval.glo4003.api.car.CarResource;
import ca.ulaval.glo4003.api.file.FileExceptionMapper;
import ca.ulaval.glo4003.api.interfaces.CatchAllExceptionMapper;
import ca.ulaval.glo4003.api.location.LocationExceptionMapper;
import ca.ulaval.glo4003.api.parking.ParkingExceptionMapper;
import ca.ulaval.glo4003.api.parking.ParkingResource;
import ca.ulaval.glo4003.api.time.TimeExceptionMapper;
import ca.ulaval.glo4003.api.user.UserExceptionMapper;
import ca.ulaval.glo4003.api.user.UserResource;
import ca.ulaval.glo4003.injection.account.AccountResourceConfig;
import ca.ulaval.glo4003.injection.car.CarResourceConfig;
import ca.ulaval.glo4003.injection.location.LocationResourceConfig;
import ca.ulaval.glo4003.injection.parking.ParkingResourceConfig;
import ca.ulaval.glo4003.injection.time.TimeResourceConfig;
import ca.ulaval.glo4003.injection.user.UserResourceConfig;
import java.util.Arrays;
import java.util.List;
import javax.ws.rs.ext.ExceptionMapper;

public class ApplicationResourceConfig {

  private static final boolean IS_DEV = true;

  private final AccountResourceConfig accountResourceConfig = new AccountResourceConfig();
  private final CarResourceConfig carResourceConfig = new CarResourceConfig();
  private final LocationResourceConfig locationResourceConfig = new LocationResourceConfig();
  private final ParkingResourceConfig parkingResourceConfig = new ParkingResourceConfig();
  private final TimeResourceConfig timeResourceConfig = new TimeResourceConfig();
  private final UserResourceConfig userResourceConfig = new UserResourceConfig();

  public CarResource createCarResource() {
    return carResourceConfig.createCarResource(accountResourceConfig.createAccountService());
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

  public List<Class<? extends ExceptionMapper<? extends Exception>>> getExceptionMappers() {
    return Arrays.asList(
        CatchAllExceptionMapper.class,
        AccountExceptionMapper.class,
        BillExceptionMapper.class,
        CarExceptionMapper.class,
        FileExceptionMapper.class,
        LocationExceptionMapper.class,
        ParkingExceptionMapper.class,
        TimeExceptionMapper.class,
        UserExceptionMapper.class);
  }
}
