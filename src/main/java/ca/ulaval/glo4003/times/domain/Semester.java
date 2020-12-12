package ca.ulaval.glo4003.times.domain;

public enum Semester {
  WINTER('H'),
  SUMMER('E'),
  AUTUMN('A');

  private char semesterSymbol;

  Semester(char semesterSymbol) {
    this.semesterSymbol = semesterSymbol;
  }

  @Override
  public String toString() {
    return String.valueOf(semesterSymbol);
  }
}
