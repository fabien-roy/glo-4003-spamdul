package ca.ulaval.glo4003.access.domain;

import ca.ulaval.glo4003.accounts.domain.AccountId;
import ca.ulaval.glo4003.times.domain.Days;

public class AccessPass {
  private AccessPassCode accessPassCode;
  private final AccountId accountId;
  private final Days validDay;

  public AccessPass(AccountId accountId, Days validDay) {
    this.accountId = accountId;
    this.validDay = validDay;
  }

  public void setAccessPassCode(AccessPassCode accessPassCode) {
    this.accessPassCode = accessPassCode;
  }

  public AccessPassCode getAccessPassCode() {
    return accessPassCode;
  }
}
