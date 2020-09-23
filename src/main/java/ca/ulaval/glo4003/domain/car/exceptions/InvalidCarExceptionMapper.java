package ca.ulaval.glo4003.domain.car.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class InvalidCarExceptionMapper implements ExceptionMapper<InvalidCarException> {

  @Override
  public Response toResponse(InvalidCarException exception) {
    return Response.status(Response.Status.BAD_REQUEST.getStatusCode()).build();
  }
}
