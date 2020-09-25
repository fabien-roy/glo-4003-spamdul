package ca.ulaval.glo4003.api.exceptionMapper.parkingException;

import ca.ulaval.glo4003.domain.parking.exception.NotFoundParkingAreaException;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class NotFoundParkingAreaExceptionMapper implements ExceptionMapper<NotFoundParkingAreaException> {

    private static final String ERROR = "Missing Address";
    private static final String DESCRIPTION = "Address is missing";

    public static class NotFoundParkingAreaExceptionResponse {
        public String error;
        public String description;
    }

    @Override
    public Response toResponse(NotFoundParkingAreaException e) {
        NotFoundParkingAreaExceptionResponse notFoundParkingAreaExceptionResponse = new NotFoundParkingAreaExceptionResponse();
        notFoundParkingAreaExceptionResponse.error = ERROR;
        notFoundParkingAreaExceptionResponse.description = DESCRIPTION;

        return Response.status(Response.Status.NOT_FOUND).entity(notFoundParkingAreaExceptionResponse).type(MediaType.APPLICATION_JSON).build();
    }
}
