package ca.ulaval.glo4003.access.domain;

public class AccessPassCode {
  private String code;

  public AccessPassCode(String code) {
    this.code = code;
  }

  @Override
  public String toString() {
    return code;
  }

  @Override
  public boolean equals(Object object) {
    if (object == null || getClass() != object.getClass()) return false;

    AccessPassCode accessPassCode = (AccessPassCode) object;

    return code.equals(accessPassCode.toString());
  }

  @Override
  public int hashCode() {
    return code.hashCode();
  }
}
