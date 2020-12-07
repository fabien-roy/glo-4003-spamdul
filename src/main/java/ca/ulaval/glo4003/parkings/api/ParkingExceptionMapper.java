package ca.ulaval.glo4003.parkings.api;

import ca.ulaval.glo4003.errors.services.dto.ErrorDto;
import ca.ulaval.glo4003.parkings.exceptions.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ParkingExceptionMapper implements ExceptionMapper<ParkingException> {

  @Override
  public Response toResponse(ParkingException exception) {
    Response.Status responseStatus = Response.Status.BAD_REQUEST;

    if (exception instanceof NotFoundParkingAreaException) {
      responseStatus = Response.Status.NOT_FOUND;
    } else if (exception instanceof NotFoundParkingStickerException) {
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
