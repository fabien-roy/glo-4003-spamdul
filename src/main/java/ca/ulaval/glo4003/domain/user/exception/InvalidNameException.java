package ca.ulaval.glo4003.domain.user.exception;

import javax.ws.rs.core.Response;

public class InvalidNameException extends RuntimeException {
  private final String error = "Invalid name";
  private final String description = "Name can't be empty";

  public Response getResponse(int statusCode) {
    return Response.status(statusCode).entity(this).build();
  }
}
