package ca.ulaval.glo4003.communications.api;

import ca.ulaval.glo4003.communications.domain.exceptions.CommunicationException;
import ca.ulaval.glo4003.communications.domain.exceptions.EmailSendingFailedException;
import ca.ulaval.glo4003.errors.services.dto.ErrorDto;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class CommunicationExceptionMapper implements ExceptionMapper<CommunicationException> {

  @Override
  public Response toResponse(CommunicationException exception) {
    Response.Status responseStatus = Response.Status.BAD_REQUEST;

    if (exception instanceof EmailSendingFailedException) {
      responseStatus = Response.Status.INTERNAL_SERVER_ERROR;
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
