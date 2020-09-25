package ca.ulaval.glo4003.api.exceptionMapper.parkingException;

import ca.ulaval.glo4003.domain.parking.exception.InvalidReceptionMethodException;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class InvalidReceptionMethodExceptionMapper implements ExceptionMapper<InvalidReceptionMethodException> {

    private static final String ERROR = "Invalid reception method";
    private static final String DESCRIPTION = "Reception method provided is not valid";

    public static class InvalidReceptionMethodExceptionResponse {
        public String error;
        public String description;
    }

    @Override
    public Response toResponse(InvalidReceptionMethodException e) {
        InvalidReceptionMethodExceptionResponse invalidReceptionMethodExceptionResponse = new InvalidReceptionMethodExceptionResponse();
        invalidReceptionMethodExceptionResponse.error = ERROR;
        invalidReceptionMethodExceptionResponse.description = DESCRIPTION;

        return Response.status(Response.Status.BAD_REQUEST).entity(invalidReceptionMethodExceptionResponse).type(MediaType.APPLICATION_JSON).build();
    }
}
