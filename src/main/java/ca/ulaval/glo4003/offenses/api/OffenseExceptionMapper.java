package ca.ulaval.glo4003.offenses.api;

import ca.ulaval.glo4003.interfaces.api.dto.ErrorDto;
import ca.ulaval.glo4003.offenses.exceptions.InvalidOffenseCodeException;
import ca.ulaval.glo4003.offenses.exceptions.OffenseException;
import ca.ulaval.glo4003.offenses.exceptions.OffenseNotFoundException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class OffenseExceptionMapper implements ExceptionMapper<OffenseException> {

  @Override
  public Response toResponse(OffenseException exception) {
    Response.Status responseStatus = Response.Status.INTERNAL_SERVER_ERROR;

    if (exception instanceof InvalidOffenseCodeException) {
      responseStatus = Response.Status.BAD_REQUEST;
    } else if (exception instanceof OffenseNotFoundException) {
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
