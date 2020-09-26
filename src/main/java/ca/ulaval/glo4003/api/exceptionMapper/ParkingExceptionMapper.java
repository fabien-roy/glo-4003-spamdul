package ca.ulaval.glo4003.api.exceptionMapper;

import ca.ulaval.glo4003.domain.parking.exception.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ParkingExceptionMapper implements ExceptionMapper<ParkingException> {

  public static class ParkingExceptionResponse {
    public String error;
    public String description;
  }

  @Override
  public Response toResponse(ParkingException exception) {
    Response.Status responseStatus = Response.Status.INTERNAL_SERVER_ERROR;

    if (exception instanceof InvalidReceptionMethodException) {
      responseStatus = Response.Status.BAD_REQUEST;
    }
    if (exception instanceof MissingPostalCodeException) {
      responseStatus = Response.Status.BAD_REQUEST;
    }
    if (exception instanceof NotFoundParkingAreaException) {
      responseStatus = Response.Status.NOT_FOUND;
    }
    if (exception instanceof NotFoundParkingStickerCodeException) {
      responseStatus = Response.Status.NOT_FOUND;
    }

    ParkingExceptionResponse parkingExceptionResponse = new ParkingExceptionResponse();
    parkingExceptionResponse.error = exception.error;
    parkingExceptionResponse.description = exception.description;

    return Response.status(responseStatus)
        .entity(parkingExceptionResponse)
        .type(MediaType.APPLICATION_JSON)
        .build();
  }
}
