package ca.ulaval.glo4003;

import ca.ulaval.glo4003.http.CORSResponseFilter;
import ca.ulaval.glo4003.injection.ApplicationResourceConfig;
import org.glassfish.jersey.server.ResourceConfig;

public class ServerResourceConfig extends ResourceConfig {
  public ServerResourceConfig() {
    packages("ca.ulaval.glo4003");
    register(CORSResponseFilter.class);

    ApplicationResourceConfig applicationResourceConfig = new ApplicationResourceConfig();
    register(applicationResourceConfig.createContactResource());
    register(applicationResourceConfig.createUserResource());
  }
}
