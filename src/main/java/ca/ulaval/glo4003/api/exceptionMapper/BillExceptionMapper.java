package ca.ulaval.glo4003.api.exceptionMapper;

import ca.ulaval.glo4003.domain.bill.exceptions.BillException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class BillExceptionMapper implements ExceptionMapper<BillException> {
  public static class BillExceptionResponse {
    public String error;
    public String description;
  }

  @Override
  public Response toResponse(BillException exception) {
    Response.Status responseStatus = Response.Status.BAD_REQUEST;

    BillExceptionResponse billExceptionResponse = new BillExceptionResponse();
    billExceptionResponse.error = exception.error;
    billExceptionResponse.description = exception.description;

    return Response.status(responseStatus)
        .entity(billExceptionResponse)
        .type(MediaType.APPLICATION_JSON)
        .build();
  }
}
