package ca.ulaval.glo4003.times.domain;

public class SemesterCode {
  private final String code;

  public SemesterCode(String code) {
    this.code = code;
  }

  @Override
  public String toString() {
    return code;
  }

  @Override
  public boolean equals(Object object) {
    if (object == null || getClass() != object.getClass()) return false;

    SemesterCode semesterCode = (SemesterCode) object;

    return this.code.equals(semesterCode.toString());
  }

  @Override
  public int hashCode() {
    return code.hashCode();
  }

  public static String[] findScholarYearFromSemesterCode(SemesterCode semesterCode) {
    String[] scholarYear = new String[3];

    String currentSemester = semesterCode.toString();
    int currentYear = Integer.parseInt(currentSemester.substring(1, 3));
    SemesterSymbol currentSemesterSymbol = SemesterSymbol.get(currentSemester.charAt(0));

    switch (currentSemesterSymbol) {
      case AUTUMN:
        scholarYear[0] = currentSemester;
        scholarYear[1] = SemesterSymbol.WINTER + String.valueOf(currentYear + 1);
        scholarYear[2] = SemesterSymbol.SUMMER + String.valueOf(currentYear + 1);
        break;
      case WINTER:
        scholarYear[0] = SemesterSymbol.AUTUMN + String.valueOf(currentYear - 1);
        scholarYear[1] = currentSemester;
        scholarYear[2] = SemesterSymbol.SUMMER + String.valueOf(currentYear);
        break;
      case SUMMER:
        scholarYear[0] = SemesterSymbol.AUTUMN + String.valueOf(currentYear - 1);
        scholarYear[1] = SemesterSymbol.WINTER + String.valueOf(currentYear);
        scholarYear[2] = currentSemester;
        break;
      default:
    }
    return scholarYear;
  }
}
