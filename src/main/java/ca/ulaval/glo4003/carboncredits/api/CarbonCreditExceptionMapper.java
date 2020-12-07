package ca.ulaval.glo4003.carboncredits.api;

import ca.ulaval.glo4003.carboncredits.exceptions.CarbonCreditException;
import ca.ulaval.glo4003.errors.services.dto.ErrorDto;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class CarbonCreditExceptionMapper implements ExceptionMapper<CarbonCreditException> {

  @Override
  public Response toResponse(CarbonCreditException exception) {
    Response.Status responseStatus = Response.Status.BAD_REQUEST;

    ErrorDto errorDto = new ErrorDto();
    errorDto.error = exception.error;
    errorDto.description = exception.description;

    return Response.status(responseStatus)
        .entity(errorDto)
        .type(MediaType.APPLICATION_JSON)
        .build();
  }
}
