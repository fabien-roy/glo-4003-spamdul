package ca.ulaval.glo4003.accesspasses.api;

import ca.ulaval.glo4003.accesspasses.exceptions.AccessPassException;
import ca.ulaval.glo4003.accesspasses.exceptions.NotFoundAccessPassException;
import ca.ulaval.glo4003.accesspasses.exceptions.UnsupportedAccessPeriodException;
import ca.ulaval.glo4003.errors.services.dto.ErrorDto;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class AccessPassExceptionMapper implements ExceptionMapper<AccessPassException> {

  @Override
  public Response toResponse(AccessPassException exception) {
    Response.Status responseStatus = Response.Status.BAD_REQUEST;

    if (exception instanceof NotFoundAccessPassException) {
      responseStatus = Response.Status.NOT_FOUND;
    } else if (exception instanceof UnsupportedAccessPeriodException) {
      responseStatus = Response.Status.NOT_IMPLEMENTED;
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
