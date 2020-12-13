package ca.ulaval.glo4003.initiatives.domain.exceptions;

import static ca.ulaval.glo4003.initiatives.helpers.InitiativeMother.createInitiativeCode;
import static com.google.common.truth.Truth.assertThat;

import ca.ulaval.glo4003.errors.domain.exceptions.ApplicationException;
import ca.ulaval.glo4003.initiatives.domain.InitiativeCode;
import org.junit.Before;
import org.junit.Test;

public class NotFoundInitiativeExceptionTest {
  private ApplicationException exception;

  private final InitiativeCode initiativeCode = createInitiativeCode();

  @Before
  public void setUp() {
    exception = new NotFoundInitiativeException(initiativeCode);
  }

  @Test
  public void whenGettingDescription_thenWriteInitiativeCode() {
    String expectedDescription =
        String.format("Initiative with code %s was not found", initiativeCode.toString());

    String description = exception.getDescription();

    assertThat(description).isEqualTo(expectedDescription);
  }
}
