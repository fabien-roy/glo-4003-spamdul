package ca.ulaval.glo4003.times.helpers;

import ca.ulaval.glo4003.times.domain.SemesterCode;
import ca.ulaval.glo4003.times.domain.SemesterSymbol;
import com.github.javafaker.Faker;

public class SemesterCodeMother {
  private static final String POSSIBLE_SEMESTER_SYMBOL =
      SemesterSymbol.AUTUMN.toString() + SemesterSymbol.WINTER + SemesterSymbol.SUMMER;
  private static final String PATTERN = "[" + POSSIBLE_SEMESTER_SYMBOL + "]\\d\\d";

  public static SemesterCode createSemesterCode() {
    String code = Faker.instance().regexify(PATTERN);
    return new SemesterCode(code);
  }
}
