package ca.ulaval.glo4003;

import static ca.ulaval.glo4003.interfaces.systemtime.SchedulerBuilder.newScheduler;

import ca.ulaval.glo4003.accesspasses.AccessPassInjector;
import ca.ulaval.glo4003.accesspasses.api.AccessPassExceptionMapper;
import ca.ulaval.glo4003.accounts.AccountInjector;
import ca.ulaval.glo4003.accounts.api.AccountExceptionMapper;
import ca.ulaval.glo4003.carboncredits.CarbonCreditInjector;
import ca.ulaval.glo4003.carboncredits.api.CarbonCreditExceptionMapper;
import ca.ulaval.glo4003.carboncredits.api.CarbonCreditResource;
import ca.ulaval.glo4003.cars.CarInjector;
import ca.ulaval.glo4003.cars.api.CarExceptionMapper;
import ca.ulaval.glo4003.communications.CommunicationInjector;
import ca.ulaval.glo4003.communications.api.CommunicationExceptionMapper;
import ca.ulaval.glo4003.files.api.FileExceptionMapper;
import ca.ulaval.glo4003.funds.FundInjector;
import ca.ulaval.glo4003.funds.api.FundExceptionMapper;
import ca.ulaval.glo4003.gates.GateInjector;
import ca.ulaval.glo4003.gates.api.GateResource;
import ca.ulaval.glo4003.initiatives.InitiativeInjector;
import ca.ulaval.glo4003.initiatives.api.InitiativeExceptionMapper;
import ca.ulaval.glo4003.initiatives.api.InitiativeResource;
import ca.ulaval.glo4003.initiatives.domain.InitiativeAddedAllocatedAmountObserver;
import ca.ulaval.glo4003.interfaces.api.CatchAllExceptionMapper;
import ca.ulaval.glo4003.locations.LocationInjector;
import ca.ulaval.glo4003.locations.api.LocationExceptionMapper;
import ca.ulaval.glo4003.offenses.OffenseInjector;
import ca.ulaval.glo4003.offenses.api.OffenseExceptionMapper;
import ca.ulaval.glo4003.offenses.api.OffenseResource;
import ca.ulaval.glo4003.parkings.ParkingInjector;
import ca.ulaval.glo4003.parkings.api.ParkingAreaResource;
import ca.ulaval.glo4003.parkings.api.ParkingExceptionMapper;
import ca.ulaval.glo4003.parkings.domain.ParkingStickerCreationObserver;
import ca.ulaval.glo4003.reports.ReportInjector;
import ca.ulaval.glo4003.reports.api.ReportExceptionMapper;
import ca.ulaval.glo4003.reports.api.ReportParkingAreaResource;
import ca.ulaval.glo4003.reports.api.ReportProfitResource;
import ca.ulaval.glo4003.times.TimeInjector;
import ca.ulaval.glo4003.times.api.TimeExceptionMapper;
import ca.ulaval.glo4003.users.UserInjector;
import ca.ulaval.glo4003.users.api.UserExceptionMapper;
import ca.ulaval.glo4003.users.api.UserResource;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import javax.ws.rs.ext.ExceptionMapper;
import org.quartz.Scheduler;

public class ApplicationInjector {

  private static final boolean IS_DEV = true;

  private static final AccessPassInjector ACCESS_PASS_INJECTOR = new AccessPassInjector();
  private static final AccountInjector ACCOUNT_INJECTOR = new AccountInjector();
  private static final CarInjector CAR_INJECTOR = new CarInjector();
  private static final CommunicationInjector COMMUNICATION_INJECTOR = new CommunicationInjector();
  private static final GateInjector GATE_INJECTOR = new GateInjector();
  private static final FundInjector FUND_INJECTOR = new FundInjector();
  private static final LocationInjector LOCATION_INJECTOR = new LocationInjector();
  private static final OffenseInjector OFFENSE_INJECTOR = new OffenseInjector();
  private static final ParkingInjector PARKING_INJECTOR = new ParkingInjector();
  private static final TimeInjector TIME_INJECTOR = new TimeInjector();
  private static final UserInjector USER_INJECTOR = new UserInjector();
  private static final CarbonCreditInjector CARBON_CREDIT_INJECTOR = new CarbonCreditInjector();
  private static final InitiativeInjector INITIATIVE_INJECTOR = new InitiativeInjector();
  private static final ReportInjector REPORT_INJECTOR = new ReportInjector();

  public UserResource createUserResource() {
    List<ParkingStickerCreationObserver> parkingStickerCreationObservers =
        Arrays.asList(
            COMMUNICATION_INJECTOR.createEmailSender(),
            LOCATION_INJECTOR.createPostalCodeSender(),
            LOCATION_INJECTOR.createSspSender());

    return USER_INJECTOR.createUserResource(
        ACCOUNT_INJECTOR.getAccountRepository(),
        ACCOUNT_INJECTOR.createAccountFactory(),
        ACCESS_PASS_INJECTOR.createAccessPassService(
            CAR_INJECTOR.createCarService(
                ACCOUNT_INJECTOR.createAccountService(
                    FUND_INJECTOR.createBillService(REPORT_INJECTOR.createReportEventService()))),
            PARKING_INJECTOR.createParkingAreaService(),
            ACCOUNT_INJECTOR.createAccountService(
                FUND_INJECTOR.createBillService(REPORT_INJECTOR.createReportEventService())),
            FUND_INJECTOR.createBillService(REPORT_INJECTOR.createReportEventService()),
            TIME_INJECTOR.createSemesterService()),
        CAR_INJECTOR.createCarService(
            ACCOUNT_INJECTOR.createAccountService(
                FUND_INJECTOR.createBillService(REPORT_INJECTOR.createReportEventService()))),
        ACCOUNT_INJECTOR.createAccountService(
            FUND_INJECTOR.createBillService(REPORT_INJECTOR.createReportEventService())),
        PARKING_INJECTOR.createParkingStickerService(
            IS_DEV,
            ACCOUNT_INJECTOR.createAccountService(
                FUND_INJECTOR.createBillService(REPORT_INJECTOR.createReportEventService())),
            parkingStickerCreationObservers,
            FUND_INJECTOR.createBillService(REPORT_INJECTOR.createReportEventService())));
  }

  public OffenseResource createOffenseResource() {
    return OFFENSE_INJECTOR.createOffenseResource(
        PARKING_INJECTOR.getParkingAreaRepository(),
        PARKING_INJECTOR.getParkingStickerRepository(),
        FUND_INJECTOR.createMoneyConverter(),
        FUND_INJECTOR.createBillService(REPORT_INJECTOR.createReportEventService()),
        ACCOUNT_INJECTOR.createAccountService(
            FUND_INJECTOR.createBillService(REPORT_INJECTOR.createReportEventService())));
  }

  public GateResource createGateResource() {
    return GATE_INJECTOR.createGateResource(
        ACCESS_PASS_INJECTOR.createAccessPassService(
            CAR_INJECTOR.createCarService(
                ACCOUNT_INJECTOR.createAccountService(
                    FUND_INJECTOR.createBillService(REPORT_INJECTOR.createReportEventService()))),
            PARKING_INJECTOR.createParkingAreaService(),
            ACCOUNT_INJECTOR.createAccountService(
                FUND_INJECTOR.createBillService(REPORT_INJECTOR.createReportEventService())),
            FUND_INJECTOR.createBillService(REPORT_INJECTOR.createReportEventService()),
            TIME_INJECTOR.createSemesterService()),
        REPORT_INJECTOR.createReportEventService());
  }

  public CarbonCreditResource createCarbonCreditResource() {
    return CARBON_CREDIT_INJECTOR.createCarbonCreditResource(
        INITIATIVE_INJECTOR.createService(
            FUND_INJECTOR.getSustainableMobilityProgramBankRepository(),
            getInitiativeAddedAllocatedAmountObservers()),
        FUND_INJECTOR.getSustainableMobilityProgramBankRepository());
  }

  public ParkingAreaResource createParkingAreaResource() {
    return PARKING_INJECTOR.createParkingAreaResource();
  }

  public InitiativeResource createInitiativeResource() {
    List<InitiativeAddedAllocatedAmountObserver> initiativeAddedAllocatedAmountObservers =
        getInitiativeAddedAllocatedAmountObservers();

    return INITIATIVE_INJECTOR.createInitiativeResource(
        INITIATIVE_INJECTOR.createService(
            FUND_INJECTOR.getSustainableMobilityProgramBankRepository(),
            initiativeAddedAllocatedAmountObservers));
  }

  public ReportProfitResource createReportProfitResource() {
    return REPORT_INJECTOR.createReportProfitResource();
  }

  public ReportParkingAreaResource createReportParkingAreaResource() {
    return REPORT_INJECTOR.createReportParkingAreaResource(
        PARKING_INJECTOR.createParkingAreaService());
  }

  public List<Class<? extends ExceptionMapper<? extends Exception>>> getExceptionMappers() {
    return Arrays.asList(
        CatchAllExceptionMapper.class,
        AccessPassExceptionMapper.class,
        AccountExceptionMapper.class,
        CarExceptionMapper.class,
        CarbonCreditExceptionMapper.class,
        CommunicationExceptionMapper.class,
        FileExceptionMapper.class,
        FundExceptionMapper.class,
        InitiativeExceptionMapper.class,
        LocationExceptionMapper.class,
        ParkingExceptionMapper.class,
        ReportExceptionMapper.class,
        TimeExceptionMapper.class,
        UserExceptionMapper.class,
        OffenseExceptionMapper.class);
  }

  public Scheduler createScheduler() {
    List<InitiativeAddedAllocatedAmountObserver> initiativeAddedAllocatedAmountObservers =
        getInitiativeAddedAllocatedAmountObservers();

    return newScheduler()
        .withJobHandlers(
            Collections.singletonList(
                CARBON_CREDIT_INJECTOR.createConvertCarbonCreditHandler(
                    INITIATIVE_INJECTOR.createService(
                        FUND_INJECTOR.getSustainableMobilityProgramBankRepository(),
                        initiativeAddedAllocatedAmountObservers),
                    FUND_INJECTOR.getSustainableMobilityProgramBankRepository())))
        .build();
  }

  private List<InitiativeAddedAllocatedAmountObserver>
      getInitiativeAddedAllocatedAmountObservers() {
    return Arrays.asList(
        CARBON_CREDIT_INJECTOR.createCarbonCreditService(
            INITIATIVE_INJECTOR.createService(
                FUND_INJECTOR.getSustainableMobilityProgramBankRepository(),
                Collections.emptyList()),
            FUND_INJECTOR.getSustainableMobilityProgramBankRepository()));
  }
}
