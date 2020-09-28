package ca.ulaval.glo4003.api.parking;

import ca.ulaval.glo4003.api.interfaces.dto.ErrorDto;
import ca.ulaval.glo4003.domain.parking.exception.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ParkingExceptionMapper implements ExceptionMapper<ParkingException> {

  @Override
  public Response toResponse(ParkingException exception) {
    Response.Status responseStatus = Response.Status.INTERNAL_SERVER_ERROR;

    if (exception instanceof InvalidParkingAreaCodeException) {
      responseStatus = Response.Status.BAD_REQUEST;
    } else if (exception instanceof InvalidReceptionMethodException) {
      responseStatus = Response.Status.BAD_REQUEST;
    } else if (exception instanceof MissingPostalCodeException) {
      responseStatus = Response.Status.BAD_REQUEST;
    } else if (exception instanceof NotFoundParkingAreaException) {
      responseStatus = Response.Status.NOT_FOUND;
    } else if (exception instanceof NotFoundParkingStickerException) {
      responseStatus = Response.Status.NOT_FOUND;
    }
    if (exception instanceof InvalidParkingStickerCodeException) {
      responseStatus = Response.Status.BAD_REQUEST;
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
