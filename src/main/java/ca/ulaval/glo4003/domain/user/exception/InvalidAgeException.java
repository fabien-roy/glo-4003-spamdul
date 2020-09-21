package ca.ulaval.glo4003.domain.user.exception;

import javax.ws.rs.core.Response;

public class InvalidAgeException extends RuntimeException {
  private final String error = "Invalid age";
  private final String description = "Age can't be empty";

  public Response getResponse(int statusCode) {
    return Response.status(statusCode).entity(this).build();
  }
}
