package ca.ulaval.glo4003.domain.account;

import java.util.UUID;

public class AccountIdGenerator {
  public AccountId generate() {
    return new AccountId(UUID.randomUUID());
  }
}
