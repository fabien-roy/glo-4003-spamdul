package ca.ulaval.glo4003.api.exceptionMapper;

import ca.ulaval.glo4003.domain.account.exception.AccountException;
import ca.ulaval.glo4003.domain.account.exception.InvalidAccountIdException;
import ca.ulaval.glo4003.domain.account.exception.NotFoundAccountException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class AccountExceptionMapper implements ExceptionMapper<AccountException> {

  public static class AccountExceptionResponse {
    public String error;
    public String description;
  }

  @Override
  public Response toResponse(AccountException exception) {
    Response.Status responseStatus = Response.Status.INTERNAL_SERVER_ERROR;

    if (exception instanceof InvalidAccountIdException) {
      responseStatus = Response.Status.BAD_REQUEST;
    }
    if (exception instanceof NotFoundAccountException) {
      responseStatus = Response.Status.NOT_FOUND;
    }

    AccountExceptionResponse accountExceptionResponse = new AccountExceptionResponse();
    accountExceptionResponse.error = exception.error;
    accountExceptionResponse.description = exception.description;

    return Response.status(responseStatus)
        .entity(accountExceptionResponse)
        .type(MediaType.APPLICATION_JSON)
        .build();
  }
}
