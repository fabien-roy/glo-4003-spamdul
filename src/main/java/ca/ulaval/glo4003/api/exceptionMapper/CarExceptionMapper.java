package ca.ulaval.glo4003.api.exceptionMapper;

import ca.ulaval.glo4003.domain.car.exceptions.CarException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class CarExceptionMapper implements ExceptionMapper<CarException> {

  public static class CarExceptionResponse {
    public String error;
    public String description;
  }

  @Override
  public Response toResponse(CarException exception) {
    Response.Status responseStatus = Response.Status.BAD_REQUEST;

    CarExceptionResponse carExceptionResponse = new CarExceptionResponse();
    carExceptionResponse.error = exception.error;
    carExceptionResponse.description = exception.description;

    return Response.status(responseStatus)
        .entity(carExceptionResponse)
        .type(MediaType.APPLICATION_JSON)
        .build();
  }
}
