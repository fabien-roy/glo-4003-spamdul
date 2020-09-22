package ca.ulaval.glo4003.domain.account;

import java.util.UUID;

public class AccountNumberGenerator {

  public AccountId getUserNextNumber() {
    return new AccountId(UUID.randomUUID().toString());
  }
}
