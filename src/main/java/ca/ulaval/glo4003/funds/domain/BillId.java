package ca.ulaval.glo4003.funds.domain;

import java.util.UUID;

public class BillId {
  private UUID id;

  public BillId(UUID id) {
    this.id = id;
  }

  @Override
  public String toString() {
    return id.toString();
  }

  public UUID toUUID() {
    return id;
  }

  @Override
  public boolean equals(Object object) {
    if (object == null || getClass() != object.getClass()) return false;

    BillId accountId = (BillId) object;

    return id.equals(accountId.toUUID());
  }

  @Override
  public int hashCode() {
    return id.hashCode();
  }
}
