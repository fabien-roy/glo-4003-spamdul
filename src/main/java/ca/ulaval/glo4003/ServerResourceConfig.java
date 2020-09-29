package ca.ulaval.glo4003;

import ca.ulaval.glo4003.http.CORSResponseFilter;
import ca.ulaval.glo4003.injection.ApplicationResourceConfig;
import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.core.Application;
import org.glassfish.jersey.server.ResourceConfig;

public class ServerResourceConfig extends ResourceConfig {
  private static final ApplicationResourceConfig applicationResourceConfig =
      new ApplicationResourceConfig();

  public static ResourceConfig getApplicationResourceConfig() {
    ResourceConfig resourceConfig =
        ResourceConfig.forApplication(
            new Application() {
              @Override
              public Set<Object> getSingletons() {
                HashSet<Object> resources = new HashSet<>();
                resources.add(applicationResourceConfig.createCarResource());
                resources.add(applicationResourceConfig.createContactResource());
                resources.add(applicationResourceConfig.createUserResource());
                resources.add(applicationResourceConfig.createParkingResource());
                return resources;
              }
            });

    resourceConfig.register(CORSResponseFilter.class);

    return resourceConfig;
  }
}
