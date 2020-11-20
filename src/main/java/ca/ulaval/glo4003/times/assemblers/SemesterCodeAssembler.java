package ca.ulaval.glo4003.times.assemblers;

import ca.ulaval.glo4003.times.domain.SemesterCode;
import ca.ulaval.glo4003.times.exceptions.InvalidSemesterCodeException;
import java.util.regex.Pattern;

public class SemesterCodeAssembler {
  private static final Pattern PATTERN = Pattern.compile("[AEH]\\d\\d");

  public SemesterCode assemble(String code) {
    if (code == null) {
      throw new InvalidSemesterCodeException();
    } else if (code.length() != 3) {
      throw new InvalidSemesterCodeException();
    } else if (!PATTERN.matcher(code).matches()) {
      throw new InvalidSemesterCodeException();
    }

    return new SemesterCode(code);
  }
}
