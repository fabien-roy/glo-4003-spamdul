package ca.ulaval.glo4003.schedulers.api;

import ca.ulaval.glo4003.errors.services.dto.ErrorDto;
import ca.ulaval.glo4003.schedulers.domain.exceptions.SchedulerException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class SchedulerExceptionMapper implements ExceptionMapper<SchedulerException> {

  @Override
  public Response toResponse(SchedulerException exception) {
    Response.Status responseStatus = Response.Status.INTERNAL_SERVER_ERROR;

    ErrorDto errorDto = new ErrorDto();
    errorDto.error = exception.error;
    errorDto.description = exception.description;

    return Response.status(responseStatus)
        .entity(errorDto)
        .type(MediaType.APPLICATION_JSON)
        .build();
  }
}
