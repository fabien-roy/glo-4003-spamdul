package ca.ulaval.glo4003.domain.user.exception;

import ca.ulaval.glo4003.api.interfaces.dto.RestErrorDto;
import javax.ws.rs.core.Response;

public class InvalidUserException extends RuntimeException {
  private String error;
  private String description;

  public InvalidUserException(String error, String description) {
    this.error = error;
    this.description = description;
  }

  // TODO : This should be done by an exception mapper, not the exception itself
  public Response getResponse(int statusCode) {
    RestErrorDto restErrorDto = new RestErrorDto();
    restErrorDto.description = this.description;
    restErrorDto.error = this.error;

    return Response.status(statusCode).entity(restErrorDto).build();
  }
}
