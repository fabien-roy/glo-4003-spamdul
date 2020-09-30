package ca.ulaval.glo4003.accounts.helpers;

import ca.ulaval.glo4003.accounts.domain.AccountId;
import java.util.UUID;

public class AccountMother {
  public static AccountId createAccountId() {
    return new AccountId(UUID.randomUUID());
  }
}
