package ca.ulaval.glo4003.errors.api;

import ca.ulaval.glo4003.errors.domain.exceptions.ApplicationException;
import ca.ulaval.glo4003.errors.services.dto.ErrorDto;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

// TODO #305 : Test this
@Provider
public class ApplicationExceptionMapper implements ExceptionMapper<ApplicationException> {

  private final ErrorResponseStatusConverter errorResponseStatusConverter;

  public ApplicationExceptionMapper(ErrorResponseStatusConverter errorResponseStatusConverter) {
    this.errorResponseStatusConverter = errorResponseStatusConverter;
  }

  @Override
  public Response toResponse(ApplicationException exception) {
    Status responseStatus = errorResponseStatusConverter.convert(exception.getErrorCode());

    ErrorDto errorDto = new ErrorDto();
    errorDto.error = exception.error;
    errorDto.description = exception.description;

    return Response.status(responseStatus)
        .entity(errorDto)
        .type(MediaType.APPLICATION_JSON)
        .build();
  }
}
