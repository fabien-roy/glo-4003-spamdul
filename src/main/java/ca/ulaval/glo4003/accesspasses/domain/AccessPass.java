package ca.ulaval.glo4003.accesspasses.domain;

import ca.ulaval.glo4003.accounts.domain.AccountId;
import ca.ulaval.glo4003.cars.domain.LicensePlate;
import ca.ulaval.glo4003.times.domain.DayOfWeek;

public class AccessPass {
  private AccessPassCode accessPassCode;
  private final AccountId accountId;
  private final DayOfWeek accessDay;
  private final LicensePlate licensePlate;

  public AccessPass(AccountId accountId, DayOfWeek accessDay, LicensePlate licensePlate) {
    this.accountId = accountId;
    this.accessDay = accessDay;
    this.licensePlate = licensePlate;
  }

  public void setCode(AccessPassCode accessPassCode) {
    this.accessPassCode = accessPassCode;
  }

  public AccessPassCode getCode() {
    return accessPassCode;
  }

  public AccountId getAccountId() {
    return accountId;
  }

  public DayOfWeek getAccessDay() {
    return accessDay;
  }

  public LicensePlate getLicensePlate() {
    return licensePlate;
  }

  public boolean validateAccessDay(DayOfWeek accessDay) {
    return this.accessDay.equals(accessDay);
  }
}
