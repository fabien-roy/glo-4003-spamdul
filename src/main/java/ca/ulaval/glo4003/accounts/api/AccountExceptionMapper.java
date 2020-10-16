package ca.ulaval.glo4003.accounts.api;

import ca.ulaval.glo4003.accounts.exceptions.AccountException;
import ca.ulaval.glo4003.accounts.exceptions.InvalidAccountIdException;
import ca.ulaval.glo4003.accounts.exceptions.NotFoundAccountException;
import ca.ulaval.glo4003.interfaces.api.dto.ErrorDto;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class AccountExceptionMapper implements ExceptionMapper<AccountException> {

  @Override
  public Response toResponse(AccountException exception) {
    Response.Status responseStatus = Response.Status.BAD_REQUEST;

    if (exception instanceof InvalidAccountIdException) {
      responseStatus = Response.Status.BAD_REQUEST;
    } else if (exception instanceof NotFoundAccountException) {
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
