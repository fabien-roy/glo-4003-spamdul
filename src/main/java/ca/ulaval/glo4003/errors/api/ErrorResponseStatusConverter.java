package ca.ulaval.glo4003.errors.api;

import ca.ulaval.glo4003.errors.domain.ErrorCode;
import javax.ws.rs.core.Response.Status;

public class ErrorResponseStatusConverter {

  // TODO #305 : Write code for this
  public Status convert(ErrorCode errorCode) {
    return Status.BAD_REQUEST;
  }
}
