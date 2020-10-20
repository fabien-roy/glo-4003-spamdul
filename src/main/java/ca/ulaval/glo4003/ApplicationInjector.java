package ca.ulaval.glo4003;

import ca.ulaval.glo4003.accesspasses.AccessPassInjector;
import ca.ulaval.glo4003.accesspasses.api.AccessPassExceptionMapper;
import ca.ulaval.glo4003.accounts.AccountInjector;
import ca.ulaval.glo4003.accounts.api.AccountExceptionMapper;
import ca.ulaval.glo4003.carboncredits.CarbonCreditInjector;
import ca.ulaval.glo4003.carboncredits.api.CarbonCreditResource;
import ca.ulaval.glo4003.cars.CarInjector;
import ca.ulaval.glo4003.cars.api.CarExceptionMapper;
import ca.ulaval.glo4003.communications.CommunicationInjector;
import ca.ulaval.glo4003.communications.api.CommunicationExceptionMapper;
import ca.ulaval.glo4003.files.api.FileExceptionMapper;
import ca.ulaval.glo4003.funds.FundInjector;
import ca.ulaval.glo4003.funds.api.FundExceptionMapper;
import ca.ulaval.glo4003.gateentries.GateEntryInjector;
import ca.ulaval.glo4003.gateentries.api.GateEntryResource;
import ca.ulaval.glo4003.initiative.InitiativeInjector;
import ca.ulaval.glo4003.initiative.api.InitiativeResource;
import ca.ulaval.glo4003.interfaces.api.CatchAllExceptionMapper;
import ca.ulaval.glo4003.locations.LocationInjector;
import ca.ulaval.glo4003.locations.api.LocationExceptionMapper;
import ca.ulaval.glo4003.offenses.OffenseInjector;
import ca.ulaval.glo4003.offenses.api.OffenseResource;
import ca.ulaval.glo4003.parkings.ParkingInjector;
import ca.ulaval.glo4003.parkings.api.ParkingExceptionMapper;
import ca.ulaval.glo4003.parkings.domain.ParkingStickerCreationObserver;
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

  private static final AccessPassInjector ACCESS_PASS_INJECTOR = new AccessPassInjector();
  private static final AccountInjector ACCOUNT_INJECTOR = new AccountInjector();
  private static final CarInjector CAR_INJECTOR = new CarInjector();
  private static final CommunicationInjector COMMUNICATION_INJECTOR = new CommunicationInjector();
  private static final GateEntryInjector GATE_ENTRY_INJECTOR = new GateEntryInjector();
  private static final FundInjector FUND_INJECTOR = new FundInjector();
  private static final LocationInjector LOCATION_INJECTOR = new LocationInjector();
  private static final OffenseInjector OFFENSE_INJECTOR = new OffenseInjector();
  private static final ParkingInjector PARKING_INJECTOR = new ParkingInjector();
  private static final TimeInjector TIME_INJECTOR = new TimeInjector();
  private static final UserInjector USER_INJECTOR = new UserInjector();
  private static final CarbonCreditInjector CARBON_CREDIT_INJECTOR = new CarbonCreditInjector();
  private static final InitiativeInjector INITIATIVE_INJECTOR = new InitiativeInjector();

  public UserResource createUserResource() {
    List<ParkingStickerCreationObserver> parkingStickerCreationObservers =
        Arrays.asList(
            COMMUNICATION_INJECTOR.createEmailSender(), LOCATION_INJECTOR.createPostalCodeSender());

    return USER_INJECTOR.createUserResource(
        ACCOUNT_INJECTOR.getAccountRepository(),
        ACCOUNT_INJECTOR.createAccountFactory(),
        ACCOUNT_INJECTOR.createAccountIdAssembler(),
        TIME_INJECTOR.createCustomDateAssembler(),
        ACCESS_PASS_INJECTOR.createAccessPassService(
            CAR_INJECTOR.createCarService(
                ACCOUNT_INJECTOR.createAccountService(FUND_INJECTOR.createBillService()),
                ACCOUNT_INJECTOR.createAccountIdAssembler()),
            ACCOUNT_INJECTOR.createAccountService(FUND_INJECTOR.createBillService()),
            FUND_INJECTOR.createBillService()),
        CAR_INJECTOR.createCarService(
            ACCOUNT_INJECTOR.createAccountService(FUND_INJECTOR.createBillService()),
            ACCOUNT_INJECTOR.createAccountIdAssembler()),
        ACCOUNT_INJECTOR.createAccountService(FUND_INJECTOR.createBillService()),
        PARKING_INJECTOR.createParkingStickerService(
            IS_DEV,
            ACCOUNT_INJECTOR.createAccountIdAssembler(),
            LOCATION_INJECTOR.createPostalCodeAssembler(),
            COMMUNICATION_INJECTOR.createEmailAddressAssembler(),
            ACCOUNT_INJECTOR.createAccountService(FUND_INJECTOR.createBillService()),
            parkingStickerCreationObservers,
            FUND_INJECTOR.createBillService()));
  }

  public OffenseResource createOffenseResource() {
    return OFFENSE_INJECTOR.createOffenseResource(
        PARKING_INJECTOR.getParkingAreaRepository(),
        PARKING_INJECTOR.getParkingStickerRepository(),
        PARKING_INJECTOR.createParkingStickerCodeAssembler(),
        PARKING_INJECTOR.createParkingAreaCodeAssembler(),
        TIME_INJECTOR.createTimeOfDayAssembler(),
        FUND_INJECTOR.createMoneyAssembler(),
        FUND_INJECTOR.createBillService(),
        ACCOUNT_INJECTOR.createAccountService(FUND_INJECTOR.createBillService()));
  }

  public GateEntryResource createGateEntryResource() {
    return GATE_ENTRY_INJECTOR.createGateEntryResource(
        ACCESS_PASS_INJECTOR.createAccessPassService(
            CAR_INJECTOR.createCarService(
                ACCOUNT_INJECTOR.createAccountService(FUND_INJECTOR.createBillService()),
                ACCOUNT_INJECTOR.createAccountIdAssembler()),
            ACCOUNT_INJECTOR.createAccountService(FUND_INJECTOR.createBillService()),
            FUND_INJECTOR.createBillService()));
  }

  public CarbonCreditResource createCarbonCreditResource() {
    return CARBON_CREDIT_INJECTOR.createCarbonCreditResource();
  }
  public InitiativeResource createInitiativeResource() {
    return INITIATIVE_INJECTOR.createInitiativeResource();

  }

  public List<Class<? extends ExceptionMapper<? extends Exception>>> getExceptionMappers() {
    return Arrays.asList(
        CatchAllExceptionMapper.class,
        AccountExceptionMapper.class,
        CarExceptionMapper.class,
        CommunicationExceptionMapper.class,
        FileExceptionMapper.class,
        LocationExceptionMapper.class,
        ParkingExceptionMapper.class,
        TimeExceptionMapper.class,
        UserExceptionMapper.class,
        AccessPassExceptionMapper.class,
        FundExceptionMapper.class);
  }
}
