package ca.ulaval.glo4003.funds.api;

import ca.ulaval.glo4003.errors.services.dto.ErrorDto;
import ca.ulaval.glo4003.funds.exception.BillNotFoundException;
import ca.ulaval.glo4003.funds.exception.FundException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class FundExceptionMapper implements ExceptionMapper<FundException> {

  @Override
  public Response toResponse(FundException exception) {
    Response.Status responseStatus = Response.Status.BAD_REQUEST;

    if (exception instanceof BillNotFoundException) {
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
