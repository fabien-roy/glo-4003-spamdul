package ca.ulaval.glo4003.times.services.converters;

import static ca.ulaval.glo4003.times.helpers.SemesterCodeMother.createSemesterCode;
import static com.google.common.truth.Truth.assertThat;

import ca.ulaval.glo4003.times.domain.SemesterCode;
import ca.ulaval.glo4003.times.domain.exceptions.InvalidSemesterCodeException;
import org.junit.Before;
import org.junit.Test;

public class SemesterCodeConverterTest {
  private static final String CODE_WITH_INVALID_LENGTH = "E200";
  private static final String CODE_WITH_IMPROPER_FORMAT = "20E";
  private static final SemesterCode SEMESTER_CODE = createSemesterCode();

  private SemesterCodeConverter semesterCodeConverter;

  @Before
  public void setUp() {
    semesterCodeConverter = new SemesterCodeConverter();
  }

  @Test
  public void whenConverting_shouldReturnSemesterCode() {
    SemesterCode semesterCode = semesterCodeConverter.convert(SEMESTER_CODE.toString());

    assertThat(semesterCode).isEqualTo(SEMESTER_CODE);
  }

  @Test(expected = InvalidSemesterCodeException.class)
  public void givenCodeWithInvalidLength_whenConverting_shouldThrowInvalidException() {
    semesterCodeConverter.convert(CODE_WITH_INVALID_LENGTH);
  }

  @Test(expected = InvalidSemesterCodeException.class)
  public void givenCodeWithImproperFormat_whenConverting_shouldThrowInvalidException() {
    semesterCodeConverter.convert(CODE_WITH_IMPROPER_FORMAT);
  }

  @Test(expected = InvalidSemesterCodeException.class)
  public void givenNullCode_whenConverting_shouldThrowInvalidException() {
    semesterCodeConverter.convert(null);
  }
}
