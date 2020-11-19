package ca.ulaval.glo4003.times.assemblers;

import static ca.ulaval.glo4003.times.helpers.SemesterCodeMother.createSemesterCode;
import static com.google.common.truth.Truth.assertThat;

import ca.ulaval.glo4003.times.domain.SemesterCode;
import ca.ulaval.glo4003.times.exceptions.InvalidSemesterCodeException;
import org.junit.Before;
import org.junit.Test;

public class SemesterCodeAssemblerTest {
  private static final String CODE_WITH_INVALID_LENGTH = "E200";
  private static final String CODE_WITH_IMPROPER_FORMAT = "20E";
  private static final SemesterCode SEMESTER_CODE = createSemesterCode();

  private SemesterCodeAssembler semesterCodeAssembler;

  @Before
  public void setUp() {
    semesterCodeAssembler = new SemesterCodeAssembler();
  }

  @Test
  public void whenAssemblingCode_shouldReturnSemesterCode() {
    SemesterCode semesterCode = semesterCodeAssembler.assemble(SEMESTER_CODE.toString());

    assertThat(semesterCode).isEqualTo(SEMESTER_CODE);
  }

  @Test(expected = InvalidSemesterCodeException.class)
  public void givenCodeWithInvalidLength_whenAssemblingCode_shouldThrowInvalidException() {
    semesterCodeAssembler.assemble(CODE_WITH_INVALID_LENGTH);
  }

  @Test(expected = InvalidSemesterCodeException.class)
  public void givenCodeWithImproperFormat_whenAssemblingCode_shouldThrowInvalidException() {
    semesterCodeAssembler.assemble(CODE_WITH_IMPROPER_FORMAT);
  }

  @Test(expected = InvalidSemesterCodeException.class)
  public void givenNullCode_whenAssemblingCode_shouldThrowInvalidException() {
    semesterCodeAssembler.assemble(null);
  }
}
