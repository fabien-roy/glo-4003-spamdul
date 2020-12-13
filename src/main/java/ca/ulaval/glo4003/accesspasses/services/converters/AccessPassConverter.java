package ca.ulaval.glo4003.accesspasses.services.converters;

import ca.ulaval.glo4003.accesspasses.domain.AccessPass;
import ca.ulaval.glo4003.accesspasses.domain.AccessPeriod;
import ca.ulaval.glo4003.accesspasses.domain.exceptions.UnsupportedAccessPeriodException;
import ca.ulaval.glo4003.accesspasses.domain.exceptions.WrongAmountOfSemestersForPeriodException;
import ca.ulaval.glo4003.accesspasses.services.dto.AccessPassDto;
import ca.ulaval.glo4003.accesspasses.services.dto.BicycleAccessPassDto;
import ca.ulaval.glo4003.cars.domain.LicensePlate;
import ca.ulaval.glo4003.cars.services.converters.LicensePlateConverter;
import ca.ulaval.glo4003.communications.domain.EmailAddress;
import ca.ulaval.glo4003.communications.domain.PostalCode;
import ca.ulaval.glo4003.communications.domain.ReceptionMethod;
import ca.ulaval.glo4003.communications.domain.exceptions.MissingEmailException;
import ca.ulaval.glo4003.communications.domain.exceptions.MissingPostalCodeException;
import ca.ulaval.glo4003.communications.services.converters.EmailAddressConverter;
import ca.ulaval.glo4003.communications.services.converters.PostalCodeConverter;
import ca.ulaval.glo4003.parkings.domain.ParkingAreaCode;
import ca.ulaval.glo4003.parkings.domain.ParkingConfiguration;
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
  private final SemesterCodeConverter semesterCodeConverter;
  private final SemesterService semesterService;

  public AccessPassConverter(SemesterService semesterService) {
    this(
        new LicensePlateConverter(),
        new ParkingAreaCodeAssembler(),
        semesterService,
        new EmailAddressConverter(),
        new PostalCodeConverter(),
        new SemesterCodeConverter());
  }

  public AccessPassConverter(
      LicensePlateConverter licensePlateConverter,
      ParkingAreaCodeAssembler parkingAreaCodeAssembler,
      SemesterService semesterService,
      EmailAddressConverter emailAddressConverter,
      PostalCodeConverter postalCodeConverter,
      SemesterCodeConverter semesterCodeConverter) {
    this.licensePlateConverter = licensePlateConverter;
    this.parkingAreaCodeAssembler = parkingAreaCodeAssembler;
    this.semesterService = semesterService;
    this.emailAddressConverter = emailAddressConverter;
    this.postalCodeConverter = postalCodeConverter;
    this.semesterCodeConverter = semesterCodeConverter;
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
            semesterCodeConverter.convert(bicycleAccessPassDto.semester));
    ParkingAreaCode parkingAreaCode =
        ParkingConfiguration.getConfiguration().getBicycleParkingAreaCode();
    AccessPeriod accessPeriod = AccessPeriod.THREE_SEMESTERS;

    ReceptionMethod receptionMethod = ReceptionMethod.get(bicycleAccessPassDto.receptionMethod);

    if (receptionMethod.equals(ReceptionMethod.EMAIL)) {
      if (bicycleAccessPassDto.email == null) throw new MissingEmailException();
      emailAddress = emailAddressConverter.convert(bicycleAccessPassDto.email);
    } else if (receptionMethod.equals(ReceptionMethod.POSTAL)) {
      if (bicycleAccessPassDto.postalCode == null) throw new MissingPostalCodeException();
      postalCode = postalCodeConverter.convert(bicycleAccessPassDto.postalCode);
    }

    return new AccessPass(
        accessPeriod,
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

    return new AccessPass(accessPeriod, dayOfWeek, licensePlate, timePeriods, parkingAreaCode);
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

    return new AccessPass(accessPeriod, dayOfWeek, timePeriods);
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
}
