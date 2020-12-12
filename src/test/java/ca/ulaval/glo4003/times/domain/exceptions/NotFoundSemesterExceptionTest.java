package ca.ulaval.glo4003.times.domain.exceptions;

import static ca.ulaval.glo4003.times.helpers.SemesterCodeMother.createSemesterCode;
import static com.google.common.truth.Truth.assertThat;

import ca.ulaval.glo4003.errors.domain.exceptions.ApplicationException;
import ca.ulaval.glo4003.times.domain.SemesterCode;
import java.util.Arrays;
import org.junit.Before;
import org.junit.Test;

public class NotFoundSemesterExceptionTest {
  private ApplicationException exception;

  private final SemesterCode firstSemesterCode = createSemesterCode();
  private final SemesterCode secondSemesterCode = createSemesterCode();

  @Before
  public void setUp() {
    exception = new NotFoundSemesterException(Arrays.asList(firstSemesterCode, secondSemesterCode));
  }

  @Test
  public void whenGettingDescription_thenEnumerateSemesters() {
    String expectedDescription =
        String.format(
            "Semester should be one of %s or %s",
            firstSemesterCode.toString(), secondSemesterCode.toString());

    String description = exception.getDescription();

    assertThat(description).isEqualTo(expectedDescription);
  }
}
