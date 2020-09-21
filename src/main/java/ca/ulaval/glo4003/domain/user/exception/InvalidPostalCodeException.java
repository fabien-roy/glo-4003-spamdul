package ca.ulaval.glo4003.domain.user.exception;

import javax.ws.rs.core.Response;

public class InvalidPostalCodeException extends RuntimeException {
  private final String error = "Invalid postal code";
  private final String description =
      "Postal code can't be empty when communication method is postal";

  public Response getResponse(int statusCode) {
    return Response.status(statusCode).entity(this).build();
  }
}
