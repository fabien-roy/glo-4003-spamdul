package ca.ulaval.glo4003;

import ca.ulaval.glo4003.accounts.AccountInjector;
import ca.ulaval.glo4003.accounts.api.AccountExceptionMapper;
import ca.ulaval.glo4003.bills.api.BillExceptionMapper;
import ca.ulaval.glo4003.cars.CarInjector;
import ca.ulaval.glo4003.cars.api.CarExceptionMapper;
import ca.ulaval.glo4003.cars.api.CarResource;
import ca.ulaval.glo4003.communications.CommunicationInjector;
import ca.ulaval.glo4003.communications.api.CommunicationExceptionMapper;
import ca.ulaval.glo4003.files.api.FileExceptionMapper;
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

  private final AccountInjector accountInjector = new AccountInjector();
  private final CarInjector carInjector = new CarInjector();
  private final CommunicationInjector communicationInjector = new CommunicationInjector();
  private final LocationInjector locationInjector = new LocationInjector();
  private final ParkingInjector parkingInjector = new ParkingInjector();
  private final TimeInjector timeInjector = new TimeInjector();
  private final UserInjector userInjector = new UserInjector();
  private final OffenseInjector offenseInjector = new OffenseInjector();

  public CarResource createCarResource() {
    return carInjector.createCarResource(accountInjector.createAccountService());
  }

  public ParkingResource createParkingResource() {
    return parkingInjector.createParkingResource(
        IS_DEV,
        accountInjector.createAccountIdAssembler(),
        locationInjector.createPostalCodeAssembler(),
        communicationInjector.createEmailAddressAssembler(),
        communicationInjector.createEmailSender(),
        accountInjector.getAccountRepository());
  }

  public UserResource createUserResource() {
    return userInjector.createUserResource(
        accountInjector.getAccountRepository(),
        accountInjector.createAccountFactory(),
        accountInjector.createAccountIdAssembler(),
        timeInjector.createCustomDateAssembler());
  }

  public OffenseResource createOffenseResource() {
    return offenseInjector.createOffenseResource(parkingInjector.getParkingStickerRepository());
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
