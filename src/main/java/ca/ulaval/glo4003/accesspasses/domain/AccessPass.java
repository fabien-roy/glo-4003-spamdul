package ca.ulaval.glo4003.accesspasses.domain;

import ca.ulaval.glo4003.accesspasses.exceptions.InvalidAccessPassEntryException;
import ca.ulaval.glo4003.accesspasses.exceptions.InvalidAccessPassExitException;
import ca.ulaval.glo4003.accounts.domain.AccountId;
import ca.ulaval.glo4003.cars.domain.LicensePlate;
import ca.ulaval.glo4003.parkings.domain.ParkingAreaCode;
import ca.ulaval.glo4003.times.domain.CustomDateTime;
import ca.ulaval.glo4003.times.domain.DayOfWeek;
import ca.ulaval.glo4003.times.domain.TimePeriod;
import java.util.List;

public class AccessPass {
  private AccessPassCode accessPassCode;
  private final AccountId accountId; // TODO #313 : Do we really need AccountId here?
  private final DayOfWeek accessDay;
  private final LicensePlate licensePlate;
  private final List<TimePeriod> accessPeriods;
  private final ParkingAreaCode parkingAreaCode;
  private boolean isAdmittedOnCampus = false;

  public AccessPass(
      AccountId accountId,
      DayOfWeek accessDay,
      LicensePlate licensePlate,
      List<TimePeriod> accessPeriods,
      ParkingAreaCode parkingAreaCode) {
    this.accountId = accountId;
    this.accessDay = accessDay;
    this.licensePlate = licensePlate;
    this.accessPeriods = accessPeriods;
    this.parkingAreaCode = parkingAreaCode;
  }

  public ParkingAreaCode getParkingAreaCode() {
    return parkingAreaCode;
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

  public boolean validateAccess(CustomDateTime dateTime) {
    if (accessDay != null) {
      DayOfWeek day = dateTime.getDayOfWeek();
      if (!day.equals(accessDay)) {
        return false;
      }
    }
    return accessPeriods.stream().anyMatch(period -> period.contains(dateTime));
  }

  public void enterCampus() {
    if (!isAdmittedOnCampus) {
      isAdmittedOnCampus = true;
    } else {
      throw new InvalidAccessPassEntryException();
    }
  }

  public void exitCampus() {
    if (isAdmittedOnCampus) {
      isAdmittedOnCampus = false;
    } else {
      throw new InvalidAccessPassExitException();
    }
  }
}
