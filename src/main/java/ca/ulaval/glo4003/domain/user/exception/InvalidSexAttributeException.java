package ca.ulaval.glo4003.domain.user.exception;

import javax.ws.rs.core.Response;

public class InvalidSexAttributeException extends RuntimeException {
  private final String error = "Invalid sex";
  private final String description = "Sex should be m, f or x";

  public Response getResponse(int statusCode) {
    return Response.status(statusCode).entity(this).build();
  }
}
