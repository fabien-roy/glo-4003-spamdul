package ca.ulaval.glo4003.accounts.infrastructure;

import ca.ulaval.glo4003.accesspasses.domain.AccessPass;
import ca.ulaval.glo4003.accesspasses.domain.AccessPassCode;
import ca.ulaval.glo4003.accesspasses.exceptions.NotFoundAccessPassException;
import ca.ulaval.glo4003.accounts.domain.Account;
import ca.ulaval.glo4003.accounts.domain.AccountId;
import ca.ulaval.glo4003.accounts.domain.AccountRepository;
import ca.ulaval.glo4003.accounts.exceptions.NotFoundAccountException;
import ca.ulaval.glo4003.cars.domain.LicensePlate;
import java.util.*;
import java.util.stream.Collectors;

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

  @Override
  public AccessPass getAccessPass(AccessPassCode accessPassCode) {
    Optional<AccessPass> accessPass =
        accounts.values().stream()
            .map(account -> account.getAccessPass(accessPassCode))
            .findFirst();

    if (accessPass.isPresent()) {
      return accessPass.get();
    } else {
      throw new NotFoundAccessPassException();
    }
  }

  @Override
  public List<AccessPass> getAccessPasses(LicensePlate licensePlate) {
    return accounts.values().stream()
        .flatMap(account -> account.getAccessPasses(licensePlate).stream())
        .collect(Collectors.toList());
  }

  @Override
  public void update(Account account) {
    Account foundAccount = get(account.getId());

    accounts.put(foundAccount.getId(), account);
  }

  // TODO #313 : Test AccountRepository.update with access pass
  @Override
  public void update(AccessPass accessPass) {}
}
