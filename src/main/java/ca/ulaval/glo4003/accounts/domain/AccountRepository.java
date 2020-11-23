package ca.ulaval.glo4003.accounts.domain;

import ca.ulaval.glo4003.accesspasses.domain.AccessPass;
import ca.ulaval.glo4003.accesspasses.domain.AccessPassCode;

public interface AccountRepository {
  AccountId save(Account account);

  Account get(AccountId id);

  AccessPass getAccessPass(AccessPassCode accessPassCode);

  void update(Account account);
}
