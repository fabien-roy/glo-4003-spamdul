package ca.ulaval.glo4003.api.exceptionMapper;

import ca.ulaval.glo4003.domain.time.exception.TimeException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class TimeExceptionMapper implements ExceptionMapper<TimeException> {

  public static class TimeExceptionResponse {
    public String error;
    public String description;
  }

  @Override
  public Response toResponse(TimeException exception) {
    Response.Status responseStatus = Response.Status.BAD_REQUEST;

    TimeExceptionResponse timeExceptionResponse = new TimeExceptionResponse();
    timeExceptionResponse.error = exception.error;
    timeExceptionResponse.description = exception.description;

    return Response.status(responseStatus)
        .entity(timeExceptionResponse)
        .type(MediaType.APPLICATION_JSON)
        .build();
  }
}
