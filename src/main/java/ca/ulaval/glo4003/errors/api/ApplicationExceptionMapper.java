package ca.ulaval.glo4003.errors.api;

import ca.ulaval.glo4003.errors.domain.exceptions.ApplicationException;
import ca.ulaval.glo4003.errors.services.dto.ErrorDto;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ApplicationExceptionMapper implements ExceptionMapper<ApplicationException> {

  private final ErrorResponseStatusConverter errorResponseStatusConverter;

  public ApplicationExceptionMapper(ErrorResponseStatusConverter errorResponseStatusConverter) {
    this.errorResponseStatusConverter = errorResponseStatusConverter;
  }

  @Override
  public Response toResponse(ApplicationException exception) {
    Status status = errorResponseStatusConverter.convert(exception.getCode());

    ErrorDto errorDto = new ErrorDto();
    errorDto.error = exception.getError();
    errorDto.description = exception.getDescription();

    return Response.status(status).entity(errorDto).type(MediaType.APPLICATION_JSON).build();
  }
}
