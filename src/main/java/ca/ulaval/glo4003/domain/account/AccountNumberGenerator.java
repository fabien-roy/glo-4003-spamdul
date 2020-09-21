package ca.ulaval.glo4003.domain.account;

import java.util.UUID;

// TODO : Test this class
public class AccountNumberGenerator {
  public AccountId generate() {
    return new AccountId(UUID.randomUUID());
  }
}
