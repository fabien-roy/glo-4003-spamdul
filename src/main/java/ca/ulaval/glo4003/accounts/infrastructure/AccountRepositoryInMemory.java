package ca.ulaval.glo4003.accounts.infrastructure;

import ca.ulaval.glo4003.accesspasses.domain.AccessPass;
import ca.ulaval.glo4003.accesspasses.domain.AccessPassCode;
import ca.ulaval.glo4003.accounts.domain.Account;
import ca.ulaval.glo4003.accounts.domain.AccountId;
import ca.ulaval.glo4003.accounts.domain.AccountRepository;
import ca.ulaval.glo4003.accounts.exceptions.NotFoundAccountException;
import ca.ulaval.glo4003.cars.domain.LicensePlate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AccountRepositoryInMemory implements AccountRepository {
  private final Map<AccountId, Account> accounts = new HashMap<>();

  @Override
  public AccountId save(Account account) {
    accounts.put(account.getId(), account);
    return account.getId();
  }

  @Override
  public Account get(AccountId accountId) {
    Account foundAccount = accounts.get(accountId);

    if (foundAccount == null) throw new NotFoundAccountException();

    return foundAccount;
  }

  // TODO #313 : Test AccountRepository.getAccessPass
  @Override
  public AccessPass getAccessPass(AccessPassCode accessPassCode) {
    return null;
  }

  // TODO #313 : Test AccountRepository.getAccessPasses with license plate
  @Override
  public List<AccessPass> getAccessPasses(LicensePlate licensePlate) {
    return new ArrayList<>();
  }

  @Override
  public void update(Account account) {
    Account foundAccount = get(account.getId());

    accounts.put(foundAccount.getId(), account);
  }
}
