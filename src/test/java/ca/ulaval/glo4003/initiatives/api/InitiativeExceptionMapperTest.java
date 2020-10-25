package ca.ulaval.glo4003.initiatives.api;

import ca.ulaval.glo4003.initiatives.exception.InitiativeException;
import ca.ulaval.glo4003.initiatives.exception.InitiativeNotFoundException;
import ca.ulaval.glo4003.initiatives.exception.InvalidInitiativeNameException;
import com.google.common.truth.Truth;
import javax.ws.rs.core.Response;
import org.junit.Before;
import org.junit.Test;

public class InitiativeExceptionMapperTest {
  private InitiativeExceptionMapper initiativeExceptionMapper;

  @Before
  public void setUp() {
    initiativeExceptionMapper = new InitiativeExceptionMapper();
  }

  @Test
  public void givenInitiativeNotFoundException_whenResponding_thenStatusIsNotFound() {
    InitiativeException initiativeNotFoundException = new InitiativeNotFoundException();

    Response response = initiativeExceptionMapper.toResponse(initiativeNotFoundException);

    Truth.assertThat(response.getStatus()).isEqualTo(Response.Status.NOT_FOUND.getStatusCode());
  }

  @Test
  public void givenInvalidInitiativeNameException_whenResponding_thenStatusIsBadRequest() {
    InitiativeException invalidInitiativeNameException = new InvalidInitiativeNameException();

    Response response = initiativeExceptionMapper.toResponse(invalidInitiativeNameException);

    Truth.assertThat(response.getStatus()).isEqualTo(Response.Status.BAD_REQUEST.getStatusCode());
  }
}
