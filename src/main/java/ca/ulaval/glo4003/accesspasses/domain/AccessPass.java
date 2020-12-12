package ca.ulaval.glo4003.accesspasses.domain;

import ca.ulaval.glo4003.accesspasses.domain.exceptions.InvalidAccessPassEntryException;
import ca.ulaval.glo4003.accesspasses.domain.exceptions.InvalidAccessPassExitException;
import ca.ulaval.glo4003.cars.domain.LicensePlate;
import ca.ulaval.glo4003.parkings.domain.ParkingAreaCode;
import ca.ulaval.glo4003.parkings.domain.ReceptionMethod;
import ca.ulaval.glo4003.times.domain.CustomDateTime;
import ca.ulaval.glo4003.times.domain.DayOfWeek;
import ca.ulaval.glo4003.times.domain.TimePeriod;
import java.util.List;

public class AccessPass {
  private AccessPassCode accessPassCode;
  private AccessPeriod accessPeriod;
  private final DayOfWeek accessDay;
  private final LicensePlate licensePlate;
  private final List<TimePeriod> accessPeriods;
  private final ParkingAreaCode parkingAreaCode;
  private boolean isAdmittedOnCampus = false;
  private ReceptionMethod receptionMethod;

  public AccessPass(
      AccessPeriod accessPeriod,
      DayOfWeek accessDay,
      LicensePlate licensePlate,
      List<TimePeriod> accessPeriods,
      ParkingAreaCode parkingAreaCode,
      ReceptionMethod receptionMethod) {
    this.accessPeriod = accessPeriod;
    this.accessDay = accessDay;
    this.licensePlate = licensePlate;
    this.accessPeriods = accessPeriods;
    this.parkingAreaCode = parkingAreaCode;
    this.receptionMethod = receptionMethod;
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

  public AccessPeriod getAccessPeriod() {
    return accessPeriod;
  }

  public DayOfWeek getAccessDay() {
    return accessDay;
  }

  public LicensePlate getLicensePlate() {
    return licensePlate;
  }

  public ReceptionMethod getReceptionMethod() {
    return receptionMethod;
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
