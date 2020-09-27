package ca.ulaval.glo4003.api.account;

import ca.ulaval.glo4003.api.interfaces.dto.ErrorDto;
import ca.ulaval.glo4003.domain.account.exception.AccountException;
import ca.ulaval.glo4003.domain.account.exception.InvalidAccountIdException;
import ca.ulaval.glo4003.domain.account.exception.NotFoundAccountException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class AccountExceptionMapper implements ExceptionMapper<AccountException> {

  @Override
  public Response toResponse(AccountException exception) {
    Response.Status responseStatus = Response.Status.INTERNAL_SERVER_ERROR;

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
