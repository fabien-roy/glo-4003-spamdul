package ca.ulaval.glo4003.api.contact;

import ca.ulaval.glo4003.api.interfaces.dto.ErrorDto;
import ca.ulaval.glo4003.domain.contact.exception.ContactException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ContactExceptionMapper implements ExceptionMapper<ContactException> {

  @Override
  public Response toResponse(ContactException exception) {
    Response.Status responseStatus = Response.Status.NOT_FOUND;

    ErrorDto errorDto = new ErrorDto();
    errorDto.error = exception.error;
    errorDto.description = exception.description;

    return Response.status(responseStatus)
        .entity(errorDto)
        .type(MediaType.APPLICATION_JSON)
        .build();
  }
}
