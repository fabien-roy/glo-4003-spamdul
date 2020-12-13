package ca.ulaval.glo4003.times.domain;

import static com.google.common.truth.Truth.assertThat;

import org.junit.Test;

public class SemesterCodeTest {
  private SemesterCode semesterCode;

  @Test
  public void
      givenAutumnSemesterCode_whenFindingScholarYearFromSemesterCode_theReturnScholarYear() {
    String autumnCode = "A18";
    semesterCode = new SemesterCode(autumnCode);
    String[] scholarYear = semesterCode.findScholarYearFromSemesterCode(semesterCode);

    assertThat(scholarYear).isEqualTo(new String[] {"A18", "H19", "E19"});
  }

  @Test
  public void
      givenWinterSemesterCode_whenFindingScholarYearFromSemesterCode_theReturnScholarYear() {
    String winterCode = "H20";
    semesterCode = new SemesterCode(winterCode);
    String[] scholarYear = semesterCode.findScholarYearFromSemesterCode(semesterCode);

    assertThat(scholarYear).isEqualTo(new String[] {"A19", "H20", "E20"});
  }

  @Test
  public void
      givenSummerSemesterCode_whenFindingScholarYearFromSemesterCode_theReturnScholarYear() {
    String summerCode = "E17";
    semesterCode = new SemesterCode(summerCode);
    String[] scholarYear = semesterCode.findScholarYearFromSemesterCode(semesterCode);

    assertThat(scholarYear).isEqualTo(new String[] {"A16", "H17", "E17"});
  }
}
