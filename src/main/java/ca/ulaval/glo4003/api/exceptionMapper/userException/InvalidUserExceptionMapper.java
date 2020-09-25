package ca.ulaval.glo4003.api.exceptionMapper.userException;

import ca.ulaval.glo4003.api.exceptionMapper.parkingException.NotFoundParkingAreaExceptionMapper;
import ca.ulaval.glo4003.domain.parking.exception.NotFoundParkingAreaException;
import ca.ulaval.glo4003.domain.user.exception.InvalidUserException;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class InvalidUserExceptionMapper implements ExceptionMapper<InvalidUserException> {

    public static class InvalidUserExceptionResponse {
        public String error;
        public String description;
    }

    @Override
    public Response toResponse(InvalidUserException exception) {
        InvalidUserExceptionResponse invalidUserExceptionResponse = new InvalidUserExceptionResponse();
        invalidUserExceptionResponse.error = exception.error;
        invalidUserExceptionResponse.description = exception.description;

        return Response.status(Response.Status.BAD_REQUEST).entity(invalidUserExceptionResponse).type(MediaType.APPLICATION_JSON).build();
    }
}
