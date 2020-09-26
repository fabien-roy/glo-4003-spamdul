package ca.ulaval.glo4003.api.exceptionMapper;

import ca.ulaval.glo4003.domain.location.exception.InvalidPostalCodeException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class LocationExceptionMapper implements ExceptionMapper<InvalidPostalCodeException> {

  public static class LocationExceptionResponse {
    public String error;
    public String description;
  }

  @Override
  public Response toResponse(InvalidPostalCodeException exception) {
    Response.Status responseStatus = Response.Status.BAD_REQUEST;

    LocationExceptionResponse locationExceptionResponse = new LocationExceptionResponse();
    locationExceptionResponse.error = exception.error;
    locationExceptionResponse.description = exception.description;

    return Response.status(responseStatus)
        .entity(locationExceptionResponse)
        .type(MediaType.APPLICATION_JSON)
        .build();
  }
}
