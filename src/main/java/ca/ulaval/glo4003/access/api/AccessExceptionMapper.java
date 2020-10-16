package ca.ulaval.glo4003.access.api;

import ca.ulaval.glo4003.access.exceptions.AccessException;
import ca.ulaval.glo4003.access.exceptions.NotFoundAccessPassException;
import ca.ulaval.glo4003.interfaces.api.dto.ErrorDto;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class AccessExceptionMapper implements ExceptionMapper<AccessException> {

  @Override
  public Response toResponse(AccessException exception) {
    Response.Status responseStatus = Response.Status.BAD_REQUEST;

    if (exception instanceof NotFoundAccessPassException) {
      responseStatus = Response.Status.NOT_FOUND;
    }

    ErrorDto errorDto = new ErrorDto();
    errorDto.error = exception.error;
    errorDto.description = exception.description;

    return Response.status(responseStatus)
        .entity(errorDto)
        .type(MediaType.APPLICATION_JSON)
        .build();
  }
}
