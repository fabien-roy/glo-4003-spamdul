package ca.ulaval.glo4003;

import ca.ulaval.glo4003.interfaces.http.CORSResponseFilter;
import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.core.Application;
import org.glassfish.jersey.server.ResourceConfig;

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
                resources.add(APPLICATION_INJECTOR.createParkingResource());
                resources.add(APPLICATION_INJECTOR.createOffenseResource());
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
}
