package ca.ulaval.glo4003.domain.user.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class InvalidUserExceptionMapper implements ExceptionMapper<InvalidUserException> {

  @Override
  public Response toResponse(InvalidUserException invalidUserException) {

    return invalidUserException.getResponse(Response.Status.BAD_REQUEST.getStatusCode());
  }
}
