package ca.ulaval.glo4003.api.exceptionMapper;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class CatchAllExceptionMapper implements ExceptionMapper<Exception> {

  private static final String ERROR = "Unexpected error";
  private static final String DESCRIPTION = "An unexpected error as occurred";

  public static class CatchAllExceptionResponse {
    public String error;
    public String description;
  }

  @Override
  public Response toResponse(Exception e) {
    Response.Status responseStatus = Response.Status.INTERNAL_SERVER_ERROR;

    CatchAllExceptionResponse catchAllExceptionResponse = new CatchAllExceptionResponse();
    catchAllExceptionResponse.error = ERROR;
    catchAllExceptionResponse.description = DESCRIPTION;

    return Response.status(responseStatus)
        .entity(catchAllExceptionResponse)
        .type(MediaType.APPLICATION_JSON)
        .build();
  }
}
