package ca.ulaval.glo4003.errors.api;

import static ca.ulaval.glo4003.errors.helpers.ApplicationExceptionMother.*;
import static ca.ulaval.glo4003.errors.helpers.ResponseStatusMother.createStatus;
import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.when;

import ca.ulaval.glo4003.errors.domain.ErrorCode;
import ca.ulaval.glo4003.errors.domain.exceptions.ApplicationException;
import ca.ulaval.glo4003.errors.services.dto.ErrorDto;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ApplicationExceptionMapperTest {

  @Mock private ErrorResponseStatusConverter converter;
  @Mock private ApplicationException exception;

  private ApplicationExceptionMapper mapper;

  private final String error = createError();
  private final String description = createDescription();
  private final ErrorCode code = createCode();
  private final Status status = createStatus();

  @Before
  public void setUp() {
    mapper = new ApplicationExceptionMapper(converter);

    when(exception.getError()).thenReturn(error);
    when(exception.getDescription()).thenReturn(description);
    when(exception.getCode()).thenReturn(code);
    when(converter.convert(code)).thenReturn(status);
  }

  @Test
  public void whenMappingException_thenUseStatusFromConverter() {
    Response response = mapper.toResponse(exception);

    assertThat(response.getStatus()).isEqualTo(status.getStatusCode());
  }

  @Test
  public void whenMappingException_thenUseErrorFromException() {
    Response response = mapper.toResponse(exception);
    ErrorDto errorDto = (ErrorDto) response.getEntity();

    assertThat(errorDto.error).isSameInstanceAs(error);
  }

  @Test
  public void whenMappingException_thenUseDescriptionFromException() {
    Response response = mapper.toResponse(exception);
    ErrorDto errorDto = (ErrorDto) response.getEntity();

    assertThat(errorDto.description).isSameInstanceAs(description);
  }
}
