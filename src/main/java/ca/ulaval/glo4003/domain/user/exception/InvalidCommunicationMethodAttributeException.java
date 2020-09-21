package ca.ulaval.glo4003.domain.user.exception;

import javax.ws.rs.core.Response;

public class InvalidCommunicationMethodAttributeException extends RuntimeException {
  private final String error = "Invalid communication method";
  private final String description = "Communication method should be postal or email";

  public Response getResponse(int statusCode) {
    return Response.status(statusCode).entity(this).build();
  }
}
