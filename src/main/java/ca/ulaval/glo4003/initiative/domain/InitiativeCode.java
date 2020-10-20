package ca.ulaval.glo4003.initiative.domain;

public class InitiativeCode {
  private String code;

  public InitiativeCode(String code) {
    this.code = code;
  }

  @Override
  public String toString() {
    return code.toString();
  }

  @Override
  public boolean equals(Object object) {
    if (object == null || getClass() != object.getClass()) return false;

    InitiativeCode initiativeCode = (InitiativeCode) object;

    return code.equals(initiativeCode.toString());
  }

  @Override
  public int hashCode() {
    return code.hashCode();
  }
}
