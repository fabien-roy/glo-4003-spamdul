package ca.ulaval.glo4003.times.services.converters;

import ca.ulaval.glo4003.times.domain.SemesterCode;
import ca.ulaval.glo4003.times.domain.SemesterSymbol;
import ca.ulaval.glo4003.times.domain.exceptions.InvalidSemesterCodeException;
import java.util.regex.Pattern;

public class SemesterCodeConverter {
  private static final String FORMAT =
      "XNN, where X is "
          + SemesterSymbol.AUTUMN
          + ", "
          + SemesterSymbol.WINTER
          + " or "
          + SemesterSymbol.SUMMER
          + " and N is a number";
  private static final String POSSIBLE_SEMESTER_SYMBOL =
      SemesterSymbol.AUTUMN.toString()
          + SemesterSymbol.WINTER.toString()
          + SemesterSymbol.SUMMER.toString();
  private static final Pattern PATTERN =
      Pattern.compile("[" + POSSIBLE_SEMESTER_SYMBOL + "]\\d\\d");

  public SemesterCode convert(String code) {
    if (code == null || !PATTERN.matcher(code).matches()) {
      throw new InvalidSemesterCodeException(FORMAT);
    }

    return new SemesterCode(code);
  }
}
