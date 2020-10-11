package ca.ulaval.glo4003.funds.api;

import ca.ulaval.glo4003.funds.exception.BillNotFound;
import ca.ulaval.glo4003.funds.exception.FundsException;
import ca.ulaval.glo4003.funds.exception.TooMuchMoney;
import ca.ulaval.glo4003.interfaces.api.dto.ErrorDto;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class FundsExceptionMapper implements ExceptionMapper<FundsException> {

  @Override
  public Response toResponse(FundsException exception) {
    Response.Status responseStatus = Response.Status.INTERNAL_SERVER_ERROR;

    if (exception instanceof BillNotFound) {
      responseStatus = Response.Status.NOT_FOUND;
    } else if (exception instanceof TooMuchMoney) {
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
