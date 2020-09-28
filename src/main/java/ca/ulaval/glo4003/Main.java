package ca.ulaval.glo4003;

import java.util.Optional;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandlerCollection;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.servlet.ServletContainer;

@SuppressWarnings("all")
public class Main {
  private static final boolean isDev = true;
  private static final int DEFAULT_PORT = 8080;
  private static final String PORT_ENV_VAR = "PORT";
  private static final String PROVIDED_PORT_MESSAGE = "INFO: Using the provided server port (%d).";
  private static final String MISSING_PORT_WARNING_MESSAGE =
      "INFO: The server port could not be found with '%s' env var. "
          + "\nINFO: Using the default one (%d).";

  public static void main(String[] args) throws Exception {
    ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
    context.setContextPath("/api/");

    ServerResourceConfig serverResourceConfig = new ServerResourceConfig();
    ServletContainer servletContainer = new ServletContainer(serverResourceConfig);
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
}
