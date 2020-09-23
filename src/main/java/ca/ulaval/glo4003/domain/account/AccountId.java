package ca.ulaval.glo4003.domain.account;

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
}
