package ca.ulaval.glo4003.api.exceptionMapper;

import ca.ulaval.glo4003.domain.user.exception.InvalidUserException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class UserExceptionMapper implements ExceptionMapper<InvalidUserException> {

  public static class InvalidUserExceptionResponse {
    public String error;
    public String description;
  }

  @Override
  public Response toResponse(InvalidUserException exception) {
    Response.Status responseStatus = Response.Status.BAD_REQUEST;

    InvalidUserExceptionResponse invalidUserExceptionResponse = new InvalidUserExceptionResponse();
    invalidUserExceptionResponse.error = exception.error;
    invalidUserExceptionResponse.description = exception.description;

    return Response.status(responseStatus)
        .entity(invalidUserExceptionResponse)
        .type(MediaType.APPLICATION_JSON)
        .build();
  }
}
