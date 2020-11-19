package ca.ulaval.glo4003.times.helpers;

import ca.ulaval.glo4003.times.domain.SemesterCode;
import com.github.javafaker.Faker;

public class SemesterCodeMother {
  private static final String PATTERN = "[AEH]\\d\\d";

  public static SemesterCode createSemesterCode() {
    String code = Faker.instance().regexify(PATTERN);
    return new SemesterCode(code);
  }
}
