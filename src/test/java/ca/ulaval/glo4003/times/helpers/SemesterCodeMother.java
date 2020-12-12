package ca.ulaval.glo4003.times.helpers;

import ca.ulaval.glo4003.times.domain.Semester;
import ca.ulaval.glo4003.times.domain.SemesterCode;
import com.github.javafaker.Faker;

public class SemesterCodeMother {
  private static final String POSSIBLE_SEMESTER_SYMBOL =
      Semester.AUTUMN.toString() + Semester.WINTER + Semester.SUMMER;
  private static final String PATTERN = "[" + POSSIBLE_SEMESTER_SYMBOL + "]\\d\\d";

  public static SemesterCode createSemesterCode() {
    String code = Faker.instance().regexify(PATTERN);
    return new SemesterCode(code);
  }
}
