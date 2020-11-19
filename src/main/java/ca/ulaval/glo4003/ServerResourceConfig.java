package ca.ulaval.glo4003;

import ca.ulaval.glo4003.interfaces.http.CORSResponseFilter;
import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.core.Application;
import org.glassfish.jersey.server.ResourceConfig;
import org.quartz.Scheduler;

public class ServerResourceConfig {
  private static final ApplicationInjector APPLICATION_INJECTOR = new ApplicationInjector();

  public static ResourceConfig getApplicationResourceConfig() {
    ResourceConfig resourceConfig =
        ResourceConfig.forApplication(
            new Application() {
              @Override
              public Set<Object> getSingletons() {
                HashSet<Object> resources = new HashSet<>();
                resources.add(APPLICATION_INJECTOR.createUserResource());
                resources.add(APPLICATION_INJECTOR.createOffenseResource());
                resources.add(APPLICATION_INJECTOR.createGateResource());
                resources.add(APPLICATION_INJECTOR.createCarbonCreditResource());
                resources.add(APPLICATION_INJECTOR.createParkingAreaResource());
                resources.add(APPLICATION_INJECTOR.createInitiativeResource());
                resources.add(APPLICATION_INJECTOR.createReportResource());
                return resources;
              }

              @Override
              public Set<Class<?>> getClasses() {
                return new HashSet<>(APPLICATION_INJECTOR.getExceptionMappers());
              }
            });

    resourceConfig.register(CORSResponseFilter.class);

    return resourceConfig;
  }

  public static Scheduler getScheduler() {
    return APPLICATION_INJECTOR.createScheduler();
  }
}
