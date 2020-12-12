package ca.ulaval.glo4003.times.services.converters;

import ca.ulaval.glo4003.times.domain.SemesterCode;
import ca.ulaval.glo4003.times.domain.exceptions.InvalidSemesterCodeException;
import java.util.regex.Pattern;

public class SemesterCodeConverter {
  private static final Pattern PATTERN = Pattern.compile("[AEH]\\d\\d");

  public SemesterCode convert(String code) {
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
