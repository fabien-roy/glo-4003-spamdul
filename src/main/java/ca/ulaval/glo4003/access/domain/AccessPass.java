package ca.ulaval.glo4003.access.domain;

import ca.ulaval.glo4003.accounts.domain.AccountId;
import ca.ulaval.glo4003.cars.domain.LicensePlate;
import ca.ulaval.glo4003.times.domain.Days;

public class AccessPass {
  private AccessPassCode accessPassCode;
  private final AccountId accountId;
  private final Days validDay;
  private final LicensePlate licensePlate;

  public AccessPass(AccountId accountId, Days validDay, LicensePlate licensePlate) {
    this.accountId = accountId;
    this.validDay = validDay;
    this.licensePlate = licensePlate;
  }

  public LicensePlate getLicensePlate() {
    return licensePlate;
  }

  public void setAccessPassCode(AccessPassCode accessPassCode) {
    this.accessPassCode = accessPassCode;
  }

  public AccessPassCode getAccessPassCode() {
    return accessPassCode;
  }
}
