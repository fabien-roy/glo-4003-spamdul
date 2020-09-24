package ca.ulaval.glo4003.domain.car.exceptions;

import ca.ulaval.glo4003.api.interfaces.dto.RestErrorDto;
import javax.ws.rs.core.Response;

public class InvalidCarException extends RuntimeException {

  private String error;
  private String description;

  public InvalidCarException(String error, String description) {
    this.error = error;
    this.description = description;
  }

  public Response getResponse(int statusCode) {
    RestErrorDto restErrorDto = new RestErrorDto();
    restErrorDto.description = this.description;
    restErrorDto.error = this.error;

    return Response.status(statusCode).entity(restErrorDto).build();
  }
}
