package ca.ulaval.glo4003.api.exceptionMapper.accountException;

import ca.ulaval.glo4003.domain.account.exception.InvalidAccountIdException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class InvalidAccountIdExceptionMapper implements ExceptionMapper<InvalidAccountIdException> {

  private static final String ERROR = "Invalid Account Id";
  private static final String DESCRIPTION = "Account Id is invalid";

  public static class InvalidAccountIdExceptionResponse {
    public String error;
    public String description;
  }

  @Override
  public Response toResponse(InvalidAccountIdException e) {
    InvalidAccountIdExceptionResponse invalidAccountIdExceptionResponse =
        new InvalidAccountIdExceptionResponse();
    invalidAccountIdExceptionResponse.error = ERROR;
    invalidAccountIdExceptionResponse.description = DESCRIPTION;

    return Response.status(Response.Status.BAD_REQUEST)
        .entity(invalidAccountIdExceptionResponse)
        .type(MediaType.APPLICATION_JSON)
        .build();
  }
}
