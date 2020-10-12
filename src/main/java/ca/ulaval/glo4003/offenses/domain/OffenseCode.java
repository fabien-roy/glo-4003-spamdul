package ca.ulaval.glo4003.offenses.domain;

public class OffenseCode {
  private String code;

  public OffenseCode(String code) {
    this.code = code;
  }

  @Override
  public String toString() {
    return code;
  }

  @Override
  public boolean equals(Object object) {
    if (object == null || getClass() != object.getClass()) return false;

    OffenseCode offenseCode = (OffenseCode) object;

    return this.code.equals(offenseCode.toString());
  }

  @Override
  public int hashCode() {
    return code.hashCode();
  }
}
