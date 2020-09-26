package ca.ulaval.glo4003.api.exceptionMapper;

import ca.ulaval.glo4003.domain.contact.exception.ContactNotFoundException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ContactExceptionMapper implements ExceptionMapper<ContactNotFoundException> {

  public static class ContactExceptionResponse {
    public String error;
    public String description;
  }

  @Override
  public Response toResponse(ContactNotFoundException exception) {
    Response.Status responseStatus = Response.Status.NOT_FOUND;

    ContactExceptionResponse contactExceptionResponse = new ContactExceptionResponse();
    contactExceptionResponse.error = exception.error;
    contactExceptionResponse.description = exception.description;

    return Response.status(responseStatus)
        .entity(contactExceptionResponse)
        .type(MediaType.APPLICATION_JSON)
        .build();
  }
}
