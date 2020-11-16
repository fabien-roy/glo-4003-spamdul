package ca.ulaval.glo4003.accesspasses.domain;

import ca.ulaval.glo4003.accesspasses.exceptions.InvalidAccessPassEntryException;
import ca.ulaval.glo4003.accesspasses.exceptions.InvalidAccessPassExitException;
import ca.ulaval.glo4003.accounts.domain.AccountId;
import ca.ulaval.glo4003.cars.domain.LicensePlate;
import ca.ulaval.glo4003.times.domain.DayOfWeek;

public class AccessPass {
  private AccessPassCode accessPassCode;
  private final AccountId accountId;
  private final DayOfWeek accessDay;
  private final LicensePlate licensePlate;
  private boolean isAdmittedOnCampus;

  public AccessPass(
      AccountId accountId,
      DayOfWeek accessDay,
      LicensePlate licensePlate,
      boolean isAdmittedOnCampus) {
    this.accountId = accountId;
    this.accessDay = accessDay;
    this.licensePlate = licensePlate;
    this.isAdmittedOnCampus = isAdmittedOnCampus;
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

  public boolean isAdmittedOnCampus() {
    return isAdmittedOnCampus;
  }

  public boolean validateAccessDay(DayOfWeek accessDay) {
    return this.accessDay.equals(accessDay);
  }

  public void exitCampus() {
    if (isAdmittedOnCampus) {
      isAdmittedOnCampus = false;
    } else {
      throw new InvalidAccessPassExitException();
    }
  }

  public void enterCampus() {
    if (!isAdmittedOnCampus) {
      isAdmittedOnCampus = true;
    } else {
      throw new InvalidAccessPassEntryException();
    }
  }
}
