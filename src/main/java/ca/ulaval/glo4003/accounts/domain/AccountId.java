package ca.ulaval.glo4003.accounts.domain;

import java.util.UUID;

public class AccountId {
  private UUID id;

  public AccountId(UUID id) {
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

    AccountId accountId = (AccountId) object;

    return id.equals(accountId.toUUID());
  }

  @Override
  public int hashCode() {
    return id.hashCode();
  }
}
