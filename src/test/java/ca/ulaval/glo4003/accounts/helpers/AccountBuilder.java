package ca.ulaval.glo4003.accounts.helpers;

import static ca.ulaval.glo4003.accounts.helpers.AccountMother.createAccountId;
import static ca.ulaval.glo4003.users.helpers.UserBuilder.aUser;

import ca.ulaval.glo4003.accesspasses.domain.AccessPass;
import ca.ulaval.glo4003.accounts.domain.Account;
import ca.ulaval.glo4003.accounts.domain.AccountId;
import ca.ulaval.glo4003.cars.domain.LicensePlate;
import ca.ulaval.glo4003.funds.domain.BillId;
import ca.ulaval.glo4003.users.domain.User;
import java.util.ArrayList;
import java.util.List;

public class AccountBuilder {
  private AccountId id = createAccountId();
  private User user = aUser().build();
  private List<AccessPass> accessPasses = new ArrayList<>();
  private List<BillId> billIds = new ArrayList<>();
  private List<LicensePlate> licensePlates = new ArrayList<>();

  private AccountBuilder() {}

  public static AccountBuilder anAccount() {
    return new AccountBuilder();
  }

  public AccountBuilder withId(AccountId id) {
    this.id = id;
    return this;
  }

  public AccountBuilder withAccessPasses(List<AccessPass> accessPasses) {
    this.accessPasses = accessPasses;
    return this;
  }

  public AccountBuilder withBillIds(List<BillId> billIds) {
    this.billIds = billIds;
    return this;
  }

  public AccountBuilder withLicensePlate(List<LicensePlate> licensePlates) {
    this.licensePlates = licensePlates;
    return this;
  }

  public Account build() {
    Account account = new Account(id, user);
    accessPasses.forEach(account::saveAccessPass);
    billIds.forEach(account::addBillId);
    licensePlates.forEach(account::addLicensePlate);
    return account;
  }
}
