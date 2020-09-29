package ca.ulaval.glo4003.api.email;

import ca.ulaval.glo4003.api.interfaces.dto.ErrorDto;
import ca.ulaval.glo4003.domain.Email.exception.EmailException;
import ca.ulaval.glo4003.domain.Email.exception.EmailSendingFailedException;
import ca.ulaval.glo4003.domain.Email.exception.InvalidEmailAddressException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class EmailExceptionMapper implements ExceptionMapper<EmailException> {

  @Override
  public Response toResponse(EmailException exception) {
    Response.Status responseStatus = Response.Status.INTERNAL_SERVER_ERROR;

    if (exception instanceof InvalidEmailAddressException) {
      responseStatus = Response.Status.BAD_REQUEST;
    } else if (exception instanceof EmailSendingFailedException) {
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
