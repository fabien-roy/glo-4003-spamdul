package ca.ulaval.glo4003.accesspasses.domain;

import ca.ulaval.glo4003.accesspasses.domain.exceptions.InvalidAccessPassEntryException;
import ca.ulaval.glo4003.accesspasses.domain.exceptions.InvalidAccessPassExitException;
import ca.ulaval.glo4003.cars.domain.LicensePlate;
import ca.ulaval.glo4003.communications.domain.EmailAddress;
import ca.ulaval.glo4003.communications.domain.PostalCode;
import ca.ulaval.glo4003.communications.domain.ReceptionMethod;
import ca.ulaval.glo4003.parkings.domain.ParkingAreaCode;
import ca.ulaval.glo4003.times.domain.CustomDateTime;
import ca.ulaval.glo4003.times.domain.DayOfWeek;
import ca.ulaval.glo4003.times.domain.TimePeriod;
import java.util.List;

public class AccessPass {
  private AccessPassCode accessPassCode;
  private final AccessPeriod accessPeriod;
  private final List<TimePeriod> accessPeriods;
  private DayOfWeek accessDay;
  private LicensePlate licensePlate;
  private ParkingAreaCode parkingAreaCode;
  private ReceptionMethod receptionMethod;
  private PostalCode postalCode;
  private EmailAddress emailAddress;
  private boolean isAdmittedOnCampus = false;

  public AccessPass(
      AccessPeriod accessPeriod,
      DayOfWeek accessDay,
      LicensePlate licensePlate,
      List<TimePeriod> accessPeriods,
      ParkingAreaCode parkingAreaCode,
      ReceptionMethod receptionMethod,
      PostalCode postalCode,
      EmailAddress emailAddress) {
    this.accessPeriod = accessPeriod;
    this.accessDay = accessDay;
    this.licensePlate = licensePlate;
    this.accessPeriods = accessPeriods;
    this.parkingAreaCode = parkingAreaCode;
    this.receptionMethod = receptionMethod;
    this.postalCode = postalCode;
    this.emailAddress = emailAddress;
  }

  public AccessPass(
      AccessPeriod accessPeriod, DayOfWeek accessDay, List<TimePeriod> accessPeriods) {
    this.accessPeriod = accessPeriod;
    this.accessDay = accessDay;
    this.accessPeriods = accessPeriods;
  }

  public AccessPass(
      AccessPeriod accessPeriod,
      DayOfWeek accessDay,
      LicensePlate licensePlate,
      List<TimePeriod> accessPeriods,
      ParkingAreaCode parkingAreaCode) {
    this.accessPeriod = accessPeriod;
    this.accessDay = accessDay;
    this.licensePlate = licensePlate;
    this.accessPeriods = accessPeriods;
    this.parkingAreaCode = parkingAreaCode;
  }

  public AccessPass(
      AccessPeriod accessPeriod,
      List<TimePeriod> accessPeriods,
      ParkingAreaCode parkingAreaCode,
      ReceptionMethod receptionMethod,
      PostalCode postalCode,
      EmailAddress emailAddress) {
    this.accessPeriod = accessPeriod;
    this.accessPeriods = accessPeriods;
    this.parkingAreaCode = parkingAreaCode;
    this.receptionMethod = receptionMethod;
    this.postalCode = postalCode;
    this.emailAddress = emailAddress;
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

  public PostalCode getPostalCode() {
    return postalCode;
  }

  public EmailAddress getEmailAddress() {
    return emailAddress;
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
