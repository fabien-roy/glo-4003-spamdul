package ca.ulaval.glo4003.domain.parking;

import ca.ulaval.glo4003.domain.account.AccountId;

public class ParkingSticker {
  private final AccountId accountId;

  public ParkingSticker(AccountId accountId) {
    this.accountId = accountId;
  }

  public AccountId getAccountId() {
    return accountId;
  }
}
