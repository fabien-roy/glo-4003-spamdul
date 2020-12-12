package ca.ulaval.glo4003.accesspasses.services.converters;

import ca.ulaval.glo4003.accesspasses.domain.AccessPass;
import ca.ulaval.glo4003.accesspasses.domain.AccessPeriod;
import ca.ulaval.glo4003.accesspasses.domain.exceptions.UnsupportedAccessPeriodException;
import ca.ulaval.glo4003.accesspasses.domain.exceptions.WrongAmountOfSemestersForPeriodException;
import ca.ulaval.glo4003.accesspasses.domain.exceptions.WrongReceptionMethodForBicycleAccessPassException;
import ca.ulaval.glo4003.accesspasses.services.dto.AccessPassDto;
import ca.ulaval.glo4003.accesspasses.services.dto.BicycleAccessPassDto;
import ca.ulaval.glo4003.cars.domain.LicensePlate;
import ca.ulaval.glo4003.cars.services.converters.LicensePlateConverter;
import ca.ulaval.glo4003.communications.domain.EmailAddress;
import ca.ulaval.glo4003.communications.services.converters.EmailAddressConverter;
import ca.ulaval.glo4003.locations.domain.PostalCode;
import ca.ulaval.glo4003.locations.services.converters.PostalCodeConverter;
import ca.ulaval.glo4003.parkings.domain.ParkingAreaCode;
import ca.ulaval.glo4003.parkings.domain.ReceptionMethod;
import ca.ulaval.glo4003.parkings.services.assemblers.ParkingAreaCodeAssembler;
import ca.ulaval.glo4003.times.domain.DayOfWeek;
import ca.ulaval.glo4003.times.domain.SemesterCode;
import ca.ulaval.glo4003.times.domain.TimePeriod;
import ca.ulaval.glo4003.times.services.SemesterService;
import ca.ulaval.glo4003.times.services.converters.SemesterCodeConverter;
import java.util.List;

public class AccessPassConverter {

  private final LicensePlateConverter licensePlateConverter;
  private final ParkingAreaCodeAssembler parkingAreaCodeAssembler;
  private final EmailAddressConverter emailAddressConverter;
  private final PostalCodeConverter postalCodeConverter;
  private final SemesterService semesterService; // TODO : Remove this somehow

  public AccessPassConverter(
      LicensePlateConverter licensePlateConverter,
      ParkingAreaCodeAssembler parkingAreaCodeAssembler,
      SemesterService semesterService,
      EmailAddressConverter emailAddressConverter,
      PostalCodeConverter postalCodeConverter) {
    this.licensePlateConverter = licensePlateConverter;
    this.parkingAreaCodeAssembler = parkingAreaCodeAssembler;
    this.semesterService = semesterService;
    this.emailAddressConverter = emailAddressConverter;
    this.postalCodeConverter = postalCodeConverter;
  }

  public AccessPass convert(AccessPassDto accessPassCodeDto) {
    if (accessPassCodeDto.licensePlate != null) {
      return convertForCar(accessPassCodeDto);
    } else {
      return convertForPedestrian(accessPassCodeDto);
    }
  }

  public AccessPass convert(BicycleAccessPassDto bicycleAccessPassDto) {
    EmailAddress emailAddress = null;
    PostalCode postalCode = null;

    String[] scholarYear =
        SemesterCode.findScholarYearFromSemesterCode(
            new SemesterCodeConverter().convert(bicycleAccessPassDto.semester));
    ParkingAreaCode parkingAreaCode = new ParkingAreaCode("ZoneVelo");
    AccessPeriod accessPeriod = AccessPeriod.THREE_SEMESTERS;

    validateReceptionMethodForBicycleAccessPass(bicycleAccessPassDto);
    ReceptionMethod receptionMethod = ReceptionMethod.get(bicycleAccessPassDto.receptionMethod);

    if (receptionMethod == ReceptionMethod.EMAIL) {
      emailAddress = emailAddressConverter.convert(bicycleAccessPassDto.emailAddress);
    } else if (receptionMethod == ReceptionMethod.POSTAL) {
      postalCode = postalCodeConverter.convert(bicycleAccessPassDto.postalCode);
    }

    return new AccessPass(
        accessPeriod,
        null,
        null,
        semesterService.getSemester(scholarYear),
        parkingAreaCode,
        receptionMethod,
        postalCode,
        emailAddress);
  }

  private AccessPass convertForCar(AccessPassDto accessPassDto) {
    AccessPeriod accessPeriod = AccessPeriod.get(accessPassDto.period);

    DayOfWeek dayOfWeek =
        accessPeriod == AccessPeriod.ONE_DAY_PER_WEEK_PER_SEMESTER
            ? DayOfWeek.get(accessPassDto.accessDay)
            : null;

    validateAccessPeriodIsSupported(accessPeriod);
    validateAmountOfSemesters(accessPassDto.semesters);
    validateCorrectLengthForSemesters(accessPassDto.semesters, accessPeriod);

    LicensePlate licensePlate = licensePlateConverter.convert(accessPassDto.licensePlate);
    ParkingAreaCode parkingAreaCode = parkingAreaCodeAssembler.assemble(accessPassDto.parkingArea);

    List<TimePeriod> timePeriods = semesterService.getSemester(accessPassDto.semesters);

    return new AccessPass(
        accessPeriod, dayOfWeek, licensePlate, timePeriods, parkingAreaCode, null, null, null);
  }

  private AccessPass convertForPedestrian(AccessPassDto accessPassDto) {
    AccessPeriod accessPeriod = AccessPeriod.get(accessPassDto.period);

    DayOfWeek dayOfWeek =
        accessPeriod == AccessPeriod.ONE_DAY_PER_WEEK_PER_SEMESTER
            ? DayOfWeek.get(accessPassDto.accessDay)
            : null;

    validateAccessPeriodIsSupported(accessPeriod);
    validateAmountOfSemesters(accessPassDto.semesters);
    validateCorrectLengthForSemesters(accessPassDto.semesters, accessPeriod);

    List<TimePeriod> timePeriods = semesterService.getSemester(accessPassDto.semesters);

    return new AccessPass(accessPeriod, dayOfWeek, null, timePeriods, null, null, null, null);
  }

  // Will be revised if story 3.1 is chosen
  private void validateAccessPeriodIsSupported(AccessPeriod period) {
    if (period == AccessPeriod.ONE_DAY || period == AccessPeriod.ONE_HOUR) {
      throw new UnsupportedAccessPeriodException();
    }
  }

  // Will be revised if story 3.1 is chosen
  private void validateAmountOfSemesters(String[] semesters) {
    if (semesters == null || semesters.length == 0) {
      throw new WrongAmountOfSemestersForPeriodException();
    }
  }

  private void validateCorrectLengthForSemesters(String[] semesters, AccessPeriod period) {
    if ((semesters.length != 1
            && (period == AccessPeriod.ONE_DAY_PER_WEEK_PER_SEMESTER
                || period == AccessPeriod.ONE_SEMESTER))
        || (semesters.length != 2 && period == AccessPeriod.TWO_SEMESTERS)
        || (semesters.length != 3 && period == AccessPeriod.THREE_SEMESTERS)) {
      throw new WrongAmountOfSemestersForPeriodException();
    }
  }

  private void validateReceptionMethodForBicycleAccessPass(
      BicycleAccessPassDto bicycleAccessPassDto) {
    if (bicycleAccessPassDto.receptionMethod == null) {
      throw new WrongReceptionMethodForBicycleAccessPassException();
    }
  }
}
