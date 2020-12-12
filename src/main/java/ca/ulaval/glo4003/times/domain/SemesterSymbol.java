package ca.ulaval.glo4003.times.domain;

import ca.ulaval.glo4003.times.domain.exceptions.InvalidSemesterSymbolException;
import java.util.HashMap;
import java.util.Map;

public enum SemesterSymbol {
  WINTER('H'),
  SUMMER('E'),
  AUTUMN('A');

  public final char semesterSymbol;
  private static final Map<String, SemesterSymbol> lookup = new HashMap<>();

  static {
    for (SemesterSymbol semester : SemesterSymbol.values()) {
      lookup.put(semester.toString(), semester);
    }
  }

  SemesterSymbol(char semesterSymbol) {
    this.semesterSymbol = semesterSymbol;
  }

  @Override
  public String toString() {
    return String.valueOf(semesterSymbol);
  }

  public static SemesterSymbol get(char semesterSymbol) {
    SemesterSymbol foundSemesterSymbol = lookup.get(semesterSymbol);

    if (foundSemesterSymbol == null) throw new InvalidSemesterSymbolException();

    return foundSemesterSymbol;
  }
}
