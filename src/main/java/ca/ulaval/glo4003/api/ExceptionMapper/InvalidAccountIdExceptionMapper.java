package ca.ulaval.glo4003.api.ExceptionMapper;

import ca.ulaval.glo4003.domain.account.exception.InvalidAccountIdException;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import java.time.format.DateTimeFormatter;

public class InvalidAccountIdExceptionMapper implements ExceptionMapper<InvalidAccountIdException> {

    private static final String ERROR = "INVALID_EVENT_DATE";
    private static final String DESCRIPTION = "event date should be between %s and %s";
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("MMMM d yyyy");

    public static class InvalidEventDateResponse {
        public String error;
        public String description;
    }

    @Override
    public Response toResponse(InvalidAccountIdException e) {
        InvalidEventDateResponse invalidEventDateResponse = new InvalidEventDateResponse();
        invalidEventDateResponse.error = ERROR;
        invalidEventDateResponse.description = String.format(DESCRIPTION,
                invalidEventDateException.eventStart.format(DATE_TIME_FORMATTER),
                invalidEventDateException.eventEnd.format(DATE_TIME_FORMATTER));

        return Response.status(Response.Status.BAD_REQUEST).entity(invalidEventDateResponse).type(MediaType.APPLICATION_JSON).build();
    }
}
