package ca.ulaval.glo4003;

import ca.ulaval.glo4003.api.car.CarResourceImplementation;
import ca.ulaval.glo4003.api.contact.ContactResource;
import ca.ulaval.glo4003.api.contact.ContactResourceImplementation;
import ca.ulaval.glo4003.api.parking.ParkingResource;
import ca.ulaval.glo4003.api.parking.ParkingResourceImplementation;
import ca.ulaval.glo4003.api.user.UserResource;
import ca.ulaval.glo4003.api.user.UserResourceImplementation;
import ca.ulaval.glo4003.domain.account.AccountFactory;
import ca.ulaval.glo4003.domain.account.AccountIdAssembler;
import ca.ulaval.glo4003.domain.account.AccountIdGenerator;
import ca.ulaval.glo4003.domain.account.AccountRepository;
import ca.ulaval.glo4003.domain.account.AccountService;
import ca.ulaval.glo4003.domain.car.CarAssembler;
import ca.ulaval.glo4003.domain.car.CarService;
import ca.ulaval.glo4003.domain.car.CarValidator;
import ca.ulaval.glo4003.domain.car.exceptions.InvalidCarExceptionMapper;
import ca.ulaval.glo4003.domain.contact.Contact;
import ca.ulaval.glo4003.domain.contact.ContactAssembler;
import ca.ulaval.glo4003.domain.contact.ContactRepository;
import ca.ulaval.glo4003.domain.contact.ContactService;
import ca.ulaval.glo4003.domain.location.PostalCodeAssembler;
import ca.ulaval.glo4003.domain.parking.*;
import ca.ulaval.glo4003.domain.time.CustomDateAssembler;
import ca.ulaval.glo4003.domain.user.UserAssembler;
import ca.ulaval.glo4003.domain.user.UserService;
import ca.ulaval.glo4003.domain.user.exception.InvalidUserExceptionMapper;
import ca.ulaval.glo4003.http.CORSResponseFilter;
import ca.ulaval.glo4003.infrastructure.account.AccountRepositoryInMemory;
import ca.ulaval.glo4003.infrastructure.contact.ContactFakeFactory;
import ca.ulaval.glo4003.infrastructure.contact.ContactRepositoryInMemory;
import ca.ulaval.glo4003.infrastructure.parking.ParkingAreaFakeFactory;
import ca.ulaval.glo4003.infrastructure.parking.ParkingAreaRepositoryInMemory;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import javax.ws.rs.core.Application;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandlerCollection;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;

@SuppressWarnings("all")
public class Main {
  private static final boolean isDev =
      true; // TODO : Would be a JVM argument or in a .property file
  private static final int DEFAULT_PORT = 8080;
  private static final String PORT_ENV_VAR = "PORT";
  private static final String PROVIDED_PORT_MESSAGE = "INFO: Using the provided server port (%d).";
  private static final String MISSING_PORT_WARNING_MESSAGE =
      "INFO: The server port could not be found with '%s' env var. "
          + "\nINFO: Using the default one (%d).";

  public static void main(String[] args) throws Exception {
    // TODO : Move creation of resources elsewhere (custom injection)
    ContactResource contactResource = createContactResource(); // TODO : Remove demo Contact logic
    UserResource userResource = createUserResource();
    ParkingResource parkingResource = createParkingResource();
    InvalidUserExceptionMapper invalidUserExceptionMapper = new InvalidUserExceptionMapper();
    // TODO : Add ParkingExceptionMapper
    // TODO : Not the real AccountService, this one is a stub
    AccountService accountService = createAccountService();
    CarResourceImplementation carResource = createCarResource(accountService);
    InvalidCarExceptionMapper invalidCarExceptionMapper = new InvalidCarExceptionMapper();

    ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
    context.setContextPath("/api/");
    ResourceConfig resourceConfig =
        ResourceConfig.forApplication(
            new Application() {
              @Override
              public Set<Object> getSingletons() {
                HashSet<Object> resources = new HashSet<>();
                resources.add(contactResource);
                resources.add(userResource);
                resources.add(parkingResource);
                resources.add(invalidUserExceptionMapper);
                resources.add(carResource);
                resources.add(invalidCarExceptionMapper);
                return resources;
              }
            });
    resourceConfig.register(CORSResponseFilter.class);

    ServletContainer servletContainer = new ServletContainer(resourceConfig);
    ServletHolder servletHolder = new ServletHolder(servletContainer);
    context.addServlet(servletHolder, "/*");

    ContextHandlerCollection contexts = new ContextHandlerCollection();
    contexts.setHandlers(new Handler[] {context});
    Server server = new Server(retrievePortNumber());
    server.setHandler(contexts);

    try {
      server.start();
      server.join();
    } finally {
      server.destroy();
    }
  }

  private static Integer retrievePortNumber() {
    return Optional.ofNullable(System.getenv(PORT_ENV_VAR))
        .map(Main::useProvidedPort)
        .orElseGet(Main::useDefaultPort);
  }

  private static Integer useProvidedPort(String providedPortValue) {
    Integer providedPort = Integer.valueOf(providedPortValue);
    System.out.println(String.format(PROVIDED_PORT_MESSAGE, providedPort));

    return providedPort;
  }

  private static Integer useDefaultPort() {
    System.out.println(String.format(MISSING_PORT_WARNING_MESSAGE, PORT_ENV_VAR, DEFAULT_PORT));

    return DEFAULT_PORT;
  }

  private static ContactResource createContactResource() {
    ContactRepository contactRepository = new ContactRepositoryInMemory();

    if (isDev) {
      ContactFakeFactory contactFakeFactory = new ContactFakeFactory();
      List<Contact> contacts = contactFakeFactory.createMockData();
      contacts.stream().forEach(contactRepository::save);
    }

    ContactAssembler contactAssembler = new ContactAssembler();
    ContactService contactService = new ContactService(contactRepository, contactAssembler);

    return new ContactResourceImplementation(contactService);
  }

  private static UserResource createUserResource() {
    AccountRepository accountRepository = new AccountRepositoryInMemory();
    AccountIdGenerator accountIdGenerator = new AccountIdGenerator();
    AccountIdAssembler accountIdAssembler = new AccountIdAssembler();
    CustomDateAssembler customDateAssembler = new CustomDateAssembler();
    UserAssembler userAssembler = new UserAssembler(customDateAssembler);
    AccountFactory accountFactory = new AccountFactory(accountIdGenerator, userAssembler);

    UserService userService =
        new UserService(accountRepository, accountFactory, accountIdAssembler, userAssembler);

    return new UserResourceImplementation(userService);
  }

  private static AccountService createAccountService() {
    return new AccountService();
  }

  private static CarResourceImplementation createCarResource(AccountService accountService) {
    CarValidator carValidator = new CarValidator();
    CarAssembler carAssembler = new CarAssembler(carValidator);
    CarService carService = new CarService(carAssembler, accountService);

    return new CarResourceImplementation(carService);
  }

  private static ParkingResource createParkingResource() {
    AccountRepository accountRepository = new AccountRepositoryInMemory();
    ParkingAreaRepository parkingAreaRepository = new ParkingAreaRepositoryInMemory();

    if (isDev) {
      ParkingAreaFakeFactory parkingAreaFakeFactory = new ParkingAreaFakeFactory();
      List<ParkingArea> parkingAreas = parkingAreaFakeFactory.createMockData();
      parkingAreas.stream().forEach(parkingAreaRepository::save);
    }

    AccountIdAssembler accountIdAssembler = new AccountIdAssembler();
    PostalCodeAssembler postalCodeAssembler = new PostalCodeAssembler();
    ParkingStickerAssembler parkingStickerAssembler =
        new ParkingStickerAssembler(accountIdAssembler, postalCodeAssembler);
    ParkingStickerCodeAssembler parkingStickerCodeAssembler = new ParkingStickerCodeAssembler();
    ParkingStickerCodeGenerator parkingStickerCodeGenerator = new ParkingStickerCodeGenerator();
    ParkingStickerFactory parkingStickerFactory =
        new ParkingStickerFactory(parkingStickerCodeGenerator);
    ParkingService parkingService =
        new ParkingService(
            parkingStickerAssembler,
            parkingStickerCodeAssembler,
            parkingStickerFactory,
            accountRepository,
            parkingAreaRepository);

    return new ParkingResourceImplementation(parkingService);
  }
}
