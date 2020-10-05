package ca.ulaval.glo4003;

import ca.ulaval.glo4003.accounts.AccountInjector;
import ca.ulaval.glo4003.accounts.api.AccountExceptionMapper;
import ca.ulaval.glo4003.bills.api.BillExceptionMapper;
import ca.ulaval.glo4003.cars.CarInjector;
import ca.ulaval.glo4003.cars.api.CarExceptionMapper;
import ca.ulaval.glo4003.cars.api.CarResource;
import ca.ulaval.glo4003.communications.CommunicationInjector;
import ca.ulaval.glo4003.communications.api.CommunicationExceptionMapper;
import ca.ulaval.glo4003.files.FileInjector;
import ca.ulaval.glo4003.files.api.FileExceptionMapper;
import ca.ulaval.glo4003.funds.FundInjector;
import ca.ulaval.glo4003.interfaces.api.CatchAllExceptionMapper;
import ca.ulaval.glo4003.locations.LocationInjector;
import ca.ulaval.glo4003.locations.api.LocationExceptionMapper;
import ca.ulaval.glo4003.offenses.OffenseInjector;
import ca.ulaval.glo4003.offenses.api.OffenseResource;
import ca.ulaval.glo4003.parkings.ParkingInjector;
import ca.ulaval.glo4003.parkings.api.ParkingExceptionMapper;
import ca.ulaval.glo4003.parkings.api.ParkingResource;
import ca.ulaval.glo4003.times.TimeInjector;
import ca.ulaval.glo4003.times.api.TimeExceptionMapper;
import ca.ulaval.glo4003.users.UserInjector;
import ca.ulaval.glo4003.users.api.UserExceptionMapper;
import ca.ulaval.glo4003.users.api.UserResource;
import java.util.Arrays;
import java.util.List;
import javax.ws.rs.ext.ExceptionMapper;

public class ApplicationInjector {

  private static final boolean IS_DEV = true;

  private static final AccountInjector ACCOUNT_INJECTOR = new AccountInjector();
  private static final CarInjector CAR_INJECTOR = new CarInjector();
  private static final CommunicationInjector COMMUNICATION_INJECTOR = new CommunicationInjector();
  private static final FileInjector FILE_INJECTOR = new FileInjector();
  private static final FundInjector FUND_INJECTOR = new FundInjector();
  private static final LocationInjector LOCATION_INJECTOR = new LocationInjector();
  private static final ParkingInjector PARKING_INJECTOR = new ParkingInjector();
  private static final TimeInjector TIME_INJECTOR = new TimeInjector();
  private static final UserInjector USER_INJECTOR = new UserInjector();
  private static final OffenseInjector OFFENSE_INJECTOR = new OffenseInjector();

  public CarResource createCarResource() {
    return CAR_INJECTOR.createCarResource(ACCOUNT_INJECTOR.createAccountService());
  }

  public ParkingResource createParkingResource() {
    return PARKING_INJECTOR.createParkingResource(
        IS_DEV,
        ACCOUNT_INJECTOR.createAccountIdAssembler(),
        LOCATION_INJECTOR.createPostalCodeAssembler(),
        COMMUNICATION_INJECTOR.createEmailAddressAssembler(),
        COMMUNICATION_INJECTOR.createEmailSender(),
        LOCATION_INJECTOR.createPostalCodeSender(),
        ACCOUNT_INJECTOR.getAccountRepository());
  }

  public UserResource createUserResource() {
    return USER_INJECTOR.createUserResource(
        ACCOUNT_INJECTOR.getAccountRepository(),
        ACCOUNT_INJECTOR.createAccountFactory(),
        ACCOUNT_INJECTOR.createAccountIdAssembler(),
        TIME_INJECTOR.createCustomDateAssembler());
  }

  public OffenseResource createOffenseResource() {
    return OFFENSE_INJECTOR.createOffenseResource(
        PARKING_INJECTOR.getParkingStickerRepository(),
        PARKING_INJECTOR.createParkingStickerCodeAssembler(),
        PARKING_INJECTOR.createParkingAreaCodeAssembler(),
        TIME_INJECTOR.createTimeOfDayAssembler(),
        FILE_INJECTOR.createJsonHelper(),
        FUND_INJECTOR.createMoneyAssembler());
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
