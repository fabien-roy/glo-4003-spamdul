package ca.ulaval.glo4003.api.exceptionMapper.parkingException;

import ca.ulaval.glo4003.domain.parking.exception.MissingAddressException;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class MissingAddressExceptionMapper implements ExceptionMapper<MissingAddressException> {

    private static final String ERROR = "Missing Address";
    private static final String DESCRIPTION = "Address is missing";

    public static class MissingAddressExceptionResponse {
        public String error;
        public String description;
    }

    @Override
    public Response toResponse(MissingAddressException e) {
        MissingAddressExceptionResponse missingAddressExceptionResponse = new MissingAddressExceptionResponse();
        missingAddressExceptionResponse.error = ERROR;
        missingAddressExceptionResponse.description = DESCRIPTION;

        return Response.status(Response.Status.BAD_REQUEST).entity(missingAddressExceptionResponse).type(MediaType.APPLICATION_JSON).build();
    }
}
