package ca.ulaval.glo4003.accesspasses.services.converters;

import ca.ulaval.glo4003.accesspasses.domain.AccessPass;
import ca.ulaval.glo4003.accesspasses.domain.AccessPeriod;
import ca.ulaval.glo4003.accesspasses.domain.exceptions.UnsupportedAccessPeriodException;
import ca.ulaval.glo4003.accesspasses.domain.exceptions.WrongAmountOfSemestersForPeriodException;
import ca.ulaval.glo4003.accesspasses.domain.exceptions.WrongReceptionMethodForBicycleAccessPassException;
import ca.ulaval.glo4003.accesspasses.services.dto.AccessPassDto;
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
import ca.ulaval.glo4003.times.domain.TimePeriod;
import ca.ulaval.glo4003.times.services.SemesterService;
import java.util.List;

public class AccessPassConverter {

  private static final char AUTUMN = 'A';
  private static final char WINTER = 'H';
  private static final char SUMMER = 'E';
  private final LicensePlateConverter licensePlateConverter;
  private final ParkingAreaCodeAssembler parkingAreaCodeAssembler;
  private final ParkingAreaCode bicycleParkingArea = new ParkingAreaCode("ZoneVelo");
  private final EmailAddressConverter emailAddressConverter;
  private final PostalCodeConverter postalCodeConverter;
  private SemesterService semesterService;

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

  public AccessPass convert(AccessPassDto accessPassCodeDto, List<TimePeriod> timePeriods) {

    if (accessPassCodeDto.licensePlate != null) {
      return convertForCarAccessPass(accessPassCodeDto, timePeriods);
    } else if (accessPassCodeDto.parkingArea.equals(bicycleParkingArea.toString())) {
      return convertForBicycleAccessPass(accessPassCodeDto);
    } else {
      return convertForPedestrianAccessPass(accessPassCodeDto, timePeriods);
    }
  }

  private AccessPass convertForCarAccessPass(
      AccessPassDto accessPassCodeDto, List<TimePeriod> timePeriods) {
    AccessPeriod accessPeriod = AccessPeriod.get(accessPassCodeDto.period);

    DayOfWeek dayOfWeek =
        accessPeriod == AccessPeriod.ONE_DAY_PER_WEEK_PER_SEMESTER
            ? DayOfWeek.get(accessPassCodeDto.accessDay)
            : null;

    validateAccessPeriodIsSupported(accessPeriod);
    validateAmountOfSemesters(accessPassCodeDto.semesters);
    validateCorrectLengthForSemesters(accessPassCodeDto.semesters, accessPeriod);

    LicensePlate licensePlate = licensePlateConverter.convert(accessPassCodeDto.licensePlate);
    ParkingAreaCode parkingAreaCode =
        parkingAreaCodeAssembler.assemble(accessPassCodeDto.parkingArea);

    return new AccessPass(
        accessPeriod, dayOfWeek, licensePlate, timePeriods, parkingAreaCode, null, null, null);
  }

  private AccessPass convertForBicycleAccessPass(AccessPassDto accessPassDto) {
    EmailAddress emailAddress = null;
    PostalCode postalCode = null;
    String[] scholarYear = FindScholarYearSemester(accessPassDto.semesters);
    ParkingAreaCode parkingAreaCode = parkingAreaCodeAssembler.assemble(accessPassDto.parkingArea);
    // TODO permet d'éviter une erreur lors de la création du bill puisqu'il n'y a pas de period
    // (BAD SMELL?)
    AccessPeriod accessPeriod = AccessPeriod.THREE_SEMESTERS;

    validateReceptionMethodForBicycleAccessPass(accessPassDto);
    ReceptionMethod receptionMethod = ReceptionMethod.get(accessPassDto.receptionMethod);

    if (receptionMethod == ReceptionMethod.EMAIL) {
      emailAddress = emailAddressConverter.convert(accessPassDto.emailAddress);
    } else if (receptionMethod == ReceptionMethod.POSTAL) {
      postalCode = postalCodeConverter.convert(accessPassDto.postalCode);
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

  // TODO maybe move this in an other class?
  private String[] FindScholarYearSemester(String[] semesters) {
    String[] scholarYear = new String[3];

    String currentSemesters = semesters[0];
    int currentYear = Integer.parseInt(currentSemesters.substring(1, 3));

    switch (currentSemesters.charAt(0)) {
      case AUTUMN:
        scholarYear[0] = currentSemesters;
        scholarYear[1] = WINTER + String.valueOf(currentYear + 1);
        scholarYear[2] = SUMMER + String.valueOf(currentYear + 1);
        break;
      case WINTER:
        scholarYear[0] = AUTUMN + String.valueOf(currentYear - 1);
        scholarYear[1] = currentSemesters;
        scholarYear[2] = SUMMER + String.valueOf(currentYear);
        break;
      case SUMMER:
        scholarYear[0] = AUTUMN + String.valueOf(currentYear - 1);
        scholarYear[1] = WINTER + String.valueOf(currentYear);
        scholarYear[2] = currentSemesters;
        break;
    }
    return scholarYear;
  }

  private AccessPass convertForPedestrianAccessPass(
      AccessPassDto accessPassCodeDto, List<TimePeriod> timePeriods) {
    AccessPeriod accessPeriod = AccessPeriod.get(accessPassCodeDto.period);

    DayOfWeek dayOfWeek =
        accessPeriod == AccessPeriod.ONE_DAY_PER_WEEK_PER_SEMESTER
            ? DayOfWeek.get(accessPassCodeDto.accessDay)
            : null;

    validateAccessPeriodIsSupported(accessPeriod);
    validateAmountOfSemesters(accessPassCodeDto.semesters);
    validateCorrectLengthForSemesters(accessPassCodeDto.semesters, accessPeriod);

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

  private void validateReceptionMethodForBicycleAccessPass(AccessPassDto accessPassDto) {
    if (accessPassDto.receptionMethod == null
        || !accessPassDto.receptionMethod.equals("postal")
        || !accessPassDto.receptionMethod.equals("email")
        || !accessPassDto.receptionMethod.equals("ssp")) {
      throw new WrongReceptionMethodForBicycleAccessPassException();
    }
  }
}
