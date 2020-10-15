package ca.ulaval.glo4003.initiative.domain;

import java.util.UUID;

public class InitiativeCode {
  private UUID code;

  public InitiativeCode(UUID code) {
    this.code = code;
  }

  @Override
  public String toString() {
    return code.toString();
  }

  public UUID toUUID() {
    return code;
  }

  @Override
  public boolean equals(Object object) {
    if (object == null || getClass() != object.getClass()) return false;

    InitiativeCode initiativeCode = (InitiativeCode) object;

    return code.equals(initiativeCode.toUUID());
  }

  @Override
  public int hashCode() {
    return code.hashCode();
  }
}
