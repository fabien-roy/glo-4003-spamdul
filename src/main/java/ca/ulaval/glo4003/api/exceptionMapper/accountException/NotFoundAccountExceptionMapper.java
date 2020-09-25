package ca.ulaval.glo4003.api.exceptionMapper.accountException;

import ca.ulaval.glo4003.domain.account.exception.NotFoundAccountException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class NotFoundAccountExceptionMapper implements ExceptionMapper<NotFoundAccountException> {

  private static final String ERROR = "Account not found";
  private static final String DESCRIPTION = "Account was not found in repository";

  public static class NotFoundAccountExceptionResponse {
    public String error;
    public String description;
  }

  @Override
  public Response toResponse(NotFoundAccountException e) {
    NotFoundAccountExceptionResponse notFoundAccountExceptionResponse =
        new NotFoundAccountExceptionResponse();
    notFoundAccountExceptionResponse.error = ERROR;
    notFoundAccountExceptionResponse.description = DESCRIPTION;

    return Response.status(Response.Status.NOT_FOUND)
        .entity(notFoundAccountExceptionResponse)
        .type(MediaType.APPLICATION_JSON)
        .build();
  }
}
