package ca.ulaval.glo4003.api.contact;

import ca.ulaval.glo4003.api.interfaces.dto.ErrorDto;
import ca.ulaval.glo4003.domain.contact.exception.ContactNotFoundException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ContactExceptionMapper implements ExceptionMapper<ContactNotFoundException> {

  @Override
  public Response toResponse(ContactNotFoundException exception) {
    Response.Status responseStatus = Response.Status.NOT_FOUND;

    ErrorDto contactExceptionResponse = new ErrorDto();
    contactExceptionResponse.error = exception.error;
    contactExceptionResponse.description = exception.description;

    return Response.status(responseStatus)
        .entity(contactExceptionResponse)
        .type(MediaType.APPLICATION_JSON)
        .build();
  }
}
