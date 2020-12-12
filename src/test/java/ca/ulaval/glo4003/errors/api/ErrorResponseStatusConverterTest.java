package ca.ulaval.glo4003.errors.api;

import static com.google.common.truth.Truth.assertThat;

import ca.ulaval.glo4003.errors.domain.ErrorCode;
import javax.ws.rs.core.Response.Status;
import org.junit.Before;
import org.junit.Test;

public class ErrorResponseStatusConverterTest {

  private ErrorResponseStatusConverter converter;

  @Before
  public void setUp() {
    converter = new ErrorResponseStatusConverter();
  }

  @Test
  public void givenNotFoundErrorCode_whenConverting_thenRespondNotFoundStatus() {
    ErrorCode code = ErrorCode.NOT_FOUND;

    Status status = converter.convert(code);

    assertThat(status).isEqualTo(Status.NOT_FOUND);
  }

  @Test
  public void givenAlreadyExistingErrorCode_whenConverting_thenRespondConflictStatus() {
    ErrorCode code = ErrorCode.ALREADY_EXISTING;

    Status status = converter.convert(code);

    assertThat(status).isEqualTo(Status.CONFLICT);
  }

  @Test
  public void givenUnsupportedOperationErrorCode_whenConverting_thenRespondNotImplementedStatus() {
    ErrorCode code = ErrorCode.UNSUPPORTED_OPERATION;

    Status status = converter.convert(code);

    assertThat(status).isEqualTo(Status.NOT_IMPLEMENTED);
  }

  @Test
  public void
      givenApplicationFailureErrorCode_whenConverting_thenRespondInternalServerErrorStatus() {
    ErrorCode code = ErrorCode.APPLICATION_FAILURE;

    Status status = converter.convert(code);

    assertThat(status).isEqualTo(Status.INTERNAL_SERVER_ERROR);
  }

  @Test
  public void givenInvalidRequestErrorCode_whenConverting_thenRespondBadRequestStatus() {
    ErrorCode code = ErrorCode.INVALID_REQUEST;

    Status status = converter.convert(code);

    assertThat(status).isEqualTo(Status.BAD_REQUEST);
  }
}
