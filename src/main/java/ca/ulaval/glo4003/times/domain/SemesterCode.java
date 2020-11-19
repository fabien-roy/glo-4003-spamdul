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
}
