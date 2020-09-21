package ca.ulaval.glo4003.domain.account.helpers;

import ca.ulaval.glo4003.domain.account.AccountId;
import java.util.UUID;

public class AccountObjectMother {
  public static AccountId createAccountId() {
    return new AccountId(UUID.randomUUID());
  }
}
