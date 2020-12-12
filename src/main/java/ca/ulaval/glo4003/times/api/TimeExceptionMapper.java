package ca.ulaval.glo4003.times.api;

import ca.ulaval.glo4003.errors.services.dto.ErrorDto;
import ca.ulaval.glo4003.times.domain.exceptions.SemesterNotFoundException;
import ca.ulaval.glo4003.times.domain.exceptions.TimeException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class TimeExceptionMapper implements ExceptionMapper<TimeException> {

  @Override
  public Response toResponse(TimeException exception) {
    Response.Status responseStatus = Response.Status.BAD_REQUEST;

    if (exception instanceof SemesterNotFoundException) {
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
