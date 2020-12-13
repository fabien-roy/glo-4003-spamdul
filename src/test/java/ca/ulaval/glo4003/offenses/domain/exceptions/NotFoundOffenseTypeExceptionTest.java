package ca.ulaval.glo4003.offenses.domain.exceptions;

import static ca.ulaval.glo4003.offenses.helpers.OffenseTypeMother.createOffenseCode;
import static com.google.common.truth.Truth.assertThat;

import ca.ulaval.glo4003.errors.domain.exceptions.ApplicationException;
import ca.ulaval.glo4003.offenses.domain.OffenseCode;
import org.junit.Before;
import org.junit.Test;

public class NotFoundOffenseTypeExceptionTest {
  private ApplicationException exception;

  private final OffenseCode offenseCode = createOffenseCode();

  @Before
  public void setUp() {
    exception = new NotFoundOffenseTypeException(offenseCode);
  }

  @Test
  public void whenGettingDescription_thenWriteOffenseCode() {
    String expectedDescription =
        String.format("Offense type with code %s was not found", offenseCode.toString());

    String description = exception.getDescription();

    assertThat(description).isEqualTo(expectedDescription);
  }
}
