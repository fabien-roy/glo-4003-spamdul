package ca.ulaval.glo4003.cars.api;

import ca.ulaval.glo4003.cars.exceptions.CarException;
import ca.ulaval.glo4003.cars.exceptions.NotFoundCarException;
import ca.ulaval.glo4003.errors.services.dto.ErrorDto;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class CarExceptionMapper implements ExceptionMapper<CarException> {

  @Override
  public Response toResponse(CarException exception) {
    Response.Status responseStatus = Response.Status.BAD_REQUEST;

    if (exception instanceof NotFoundCarException) {
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
