package ca.ulaval.glo4003.times.services.converters;

import ca.ulaval.glo4003.times.domain.SemesterCode;
import ca.ulaval.glo4003.times.domain.exceptions.InvalidSemesterCodeException;
import java.util.regex.Pattern;

public class SemesterCodeConverter {
  private static final String FORMAT = "XNN, where X is A, E or H and N is a number";
  private static final Pattern PATTERN = Pattern.compile("[AEH]\\d\\d");

  public SemesterCode convert(String code) {
    if (code == null || !PATTERN.matcher(code).matches()) {
      throw new InvalidSemesterCodeException(FORMAT);
    }

    return new SemesterCode(code);
  }
}
