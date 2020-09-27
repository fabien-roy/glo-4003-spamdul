package ca.ulaval.glo4003;

import ca.ulaval.glo4003.http.CORSResponseFilter;
import org.glassfish.jersey.server.ResourceConfig;

public class ServerResourceConfig extends ResourceConfig {
  public ServerResourceConfig() {
    packages("ca.ulaval.glo4003");
    register(CORSResponseFilter.class);
    register(new ApplicationBinder());
  }
}
