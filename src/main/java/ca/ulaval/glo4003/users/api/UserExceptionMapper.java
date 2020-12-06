package ca.ulaval.glo4003.users.api;

import ca.ulaval.glo4003.errors.services.dto.ErrorDto;
import ca.ulaval.glo4003.users.exceptions.UserException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class UserExceptionMapper implements ExceptionMapper<UserException> {

  @Override
  public Response toResponse(UserException exception) {
    Response.Status responseStatus = Response.Status.BAD_REQUEST;

    ErrorDto errorDto = new ErrorDto();
    errorDto.error = exception.error;
    errorDto.description = exception.description;

    return Response.status(responseStatus)
        .entity(errorDto)
        .type(MediaType.APPLICATION_JSON)
        .build();
  }
}
