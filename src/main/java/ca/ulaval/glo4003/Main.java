package ca.ulaval.glo4003;

import ca.ulaval.glo4003.api.contact.ContactResource;
import ca.ulaval.glo4003.api.contact.ContactResourceImpl;
import ca.ulaval.glo4003.api.parking.ParkingResource;
import ca.ulaval.glo4003.api.parking.ParkingResourceImpl;
import ca.ulaval.glo4003.domain.account.AccountIdAssembler;
import ca.ulaval.glo4003.domain.account.AccountRepository;
import ca.ulaval.glo4003.domain.contact.Contact;
import ca.ulaval.glo4003.domain.contact.ContactAssembler;
import ca.ulaval.glo4003.domain.contact.ContactRepository;
import ca.ulaval.glo4003.domain.contact.ContactService;
import ca.ulaval.glo4003.domain.parking.*;
import ca.ulaval.glo4003.http.CORSResponseFilter;
import ca.ulaval.glo4003.infrastructure.account.AccountRepositoryInMemory;
import ca.ulaval.glo4003.infrastructure.contact.ContactDevDataFactory;
import ca.ulaval.glo4003.infrastructure.contact.ContactRepositoryInMemory;
import ca.ulaval.glo4003.infrastructure.parking.ParkingAreaRepositoryInMemory;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.ws.rs.core.Application;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandlerCollection;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;

/** RESTApi setup without using DI or spring */
@SuppressWarnings("all")
public class Main {
  public static boolean isDev = true; // TODO : Would be a JVM argument or in a .property file

  public static void main(String[] args) throws Exception {
    // TODO : Move creation of resources elsewhere (custom injection)
    ContactResource contactResource = createContactResource(); // TODO : Remove demo Contact logic
    ParkingResource parkingResource = createParkingResource();

    ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
    context.setContextPath("/api/");
    ResourceConfig resourceConfig =
        ResourceConfig.forApplication(
            new Application() {
              @Override
              public Set<Object> getSingletons() {
                HashSet<Object> resources = new HashSet<>();
                resources.add(contactResource);
                resources.add(parkingResource);
                return resources;
              }
            });
    resourceConfig.register(CORSResponseFilter.class);

    ServletContainer servletContainer = new ServletContainer(resourceConfig);
    ServletHolder servletHolder = new ServletHolder(servletContainer);
    context.addServlet(servletHolder, "/*");

    ContextHandlerCollection contexts = new ContextHandlerCollection();
    contexts.setHandlers(new Handler[] {context});
    Server server = new Server(8080);
    server.setHandler(contexts);

    try {
      server.start();
      server.join();
    } finally {
      server.destroy();
    }
  }

  private static ContactResource createContactResource() {
    ContactRepository contactRepository = new ContactRepositoryInMemory();

    if (isDev) {
      ContactDevDataFactory contactDevDataFactory = new ContactDevDataFactory();
      List<Contact> contacts = contactDevDataFactory.createMockData();
      contacts.stream().forEach(contactRepository::save);
    }

    ContactAssembler contactAssembler = new ContactAssembler();
    ContactService contactService = new ContactService(contactRepository, contactAssembler);

    return new ContactResourceImpl(contactService);
  }

  private static ParkingResource createParkingResource() {
    AccountRepository accountRepository = new AccountRepositoryInMemory();
    ParkingAreaRepository parkingAreaRepository = new ParkingAreaRepositoryInMemory();
    ParkingStickerCodeRepository parkingStickerCodeRepository = new ParkingStickerCodeRepository();

    // TODO : Dev mock data for account and parking area repository

    AccountIdAssembler accountIdAssembler = new AccountIdAssembler();
    ParkingStickerAssembler parkingStickerAssembler =
        new ParkingStickerAssembler(accountIdAssembler);
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
            parkingAreaRepository,
            parkingStickerCodeRepository);

    return new ParkingResourceImpl(parkingService);
  }
}
