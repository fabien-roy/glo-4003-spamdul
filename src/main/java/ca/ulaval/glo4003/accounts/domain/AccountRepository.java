package ca.ulaval.glo4003.accounts.domain;

import ca.ulaval.glo4003.accesspasses.domain.AccessPass;
import ca.ulaval.glo4003.accesspasses.domain.AccessPassCode;
import ca.ulaval.glo4003.cars.domain.LicensePlate;
import java.util.List;

public interface AccountRepository {
  AccountId save(Account account);

  Account get(AccountId id);

  AccessPass getAccessPass(AccessPassCode accessPassCode);

  List<AccessPass> getAccessPasses(LicensePlate licensePlate);

  void update(Account account);
}
