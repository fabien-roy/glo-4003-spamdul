package ca.ulaval.glo4003.initiative.api;

import ca.ulaval.glo4003.initiative.exception.InitiativeException;
import ca.ulaval.glo4003.initiative.exception.InitiativeNotFoundException;
import ca.ulaval.glo4003.interfaces.api.dto.ErrorDto;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class InitiativeExceptionMapper implements ExceptionMapper<InitiativeException> {

  @Override
  public Response toResponse(InitiativeException exception) {
    Response.Status responseStatus = Response.Status.BAD_REQUEST;

    if (exception instanceof InitiativeNotFoundException) {
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
