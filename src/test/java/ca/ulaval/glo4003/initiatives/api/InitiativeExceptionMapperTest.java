package ca.ulaval.glo4003.initiatives.api;

import ca.ulaval.glo4003.initiatives.exception.InitiativeNotFoundException;
import com.google.common.truth.Truth;
import javax.ws.rs.core.Response;
import org.junit.Test;

public class InitiativeExceptionMapperTest {
  private InitiativeExceptionMapper initiativeExceptionMapper = new InitiativeExceptionMapper();

  @Test
  public void givenInitiativeNotFound_whenResponding_thenStatusIsNotFound() {
    InitiativeNotFoundException initiativeNotFoundException = new InitiativeNotFoundException();

    Response response = initiativeExceptionMapper.toResponse(initiativeNotFoundException);

    Truth.assertThat(response.getStatus()).isEqualTo(Response.Status.NOT_FOUND.getStatusCode());
  }
}
