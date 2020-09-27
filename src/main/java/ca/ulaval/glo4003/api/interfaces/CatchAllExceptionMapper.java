package ca.ulaval.glo4003.api.interfaces;

import ca.ulaval.glo4003.api.interfaces.dto.ErrorDto;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class CatchAllExceptionMapper implements ExceptionMapper<Exception> {

  private static final String ERROR = "Unexpected error";
  private static final String DESCRIPTION = "An unexpected error as occurred";

  @Override
  public Response toResponse(Exception e) {
    Response.Status responseStatus = Response.Status.INTERNAL_SERVER_ERROR;

    ErrorDto errorDto = new ErrorDto();
    errorDto.error = ERROR;
    errorDto.description = DESCRIPTION;

    return Response.status(responseStatus)
        .entity(errorDto)
        .type(MediaType.APPLICATION_JSON)
        .build();
  }
}
