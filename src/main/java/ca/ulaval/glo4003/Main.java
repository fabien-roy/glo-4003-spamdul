package ca.ulaval.glo4003;

import java.util.Optional;
import java.util.logging.Logger;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandlerCollection;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;
import org.quartz.Scheduler;

public class Main {
  private static final Logger LOGGER = Logger.getLogger(Main.class.getName());
  private static final int DEFAULT_PORT = 8080;
  private static final String PORT_ENV_VAR = "PORT";

  public static void main(String[] args) throws Exception {
    ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
    context.setContextPath("/api/");

    ResourceConfig serverResourceConfig = ServerResourceConfig.getApplicationResourceConfig();
    ServletContainer servletContainer = new ServletContainer(serverResourceConfig);
    ServletHolder servletHolder = new ServletHolder(servletContainer);
    context.addServlet(servletHolder, "/*");

    ContextHandlerCollection contexts = new ContextHandlerCollection();
    contexts.setHandlers(new Handler[] {context});
    Server server = new Server(retrievePortNumber());
    server.setHandler(contexts);

    Scheduler scheduler = ServerResourceConfig.getScheduler();
    scheduler.start();

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
    LOGGER.info(String.format("Using the provided server port (%d)", providedPort));

    return providedPort;
  }

  private static Integer useDefaultPort() {
    LOGGER.info(
        String.format("The server port could not be found with '%s' env var.", PORT_ENV_VAR));
    LOGGER.info(String.format("Using the default one (%d)", DEFAULT_PORT));

    return DEFAULT_PORT;
  }
}
