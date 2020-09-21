package ca.ulaval.glo4003.domain.user.exception;

import javax.ws.rs.core.Response;

public class InvalidBirthDateException extends RuntimeException {
  private final String error = "Invalid birth date";
  private final String description = "Birth date should be in this format : dd-mm-yyyy";

  public Response getResponse(int statusCode) {
    return Response.status(statusCode).entity(this).build();
  }
}
