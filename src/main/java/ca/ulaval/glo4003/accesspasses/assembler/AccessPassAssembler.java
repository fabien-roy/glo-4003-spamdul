package ca.ulaval.glo4003.accesspasses.assembler;

import ca.ulaval.glo4003.accesspasses.api.dto.AccessPassDto;
import ca.ulaval.glo4003.accesspasses.domain.AccessPass;
import ca.ulaval.glo4003.accesspasses.domain.AccessPeriod;
import ca.ulaval.glo4003.accesspasses.exceptions.UnsupportedAccessPeriodException;
import ca.ulaval.glo4003.accesspasses.exceptions.WrongAmountOfSemestersForPeriodException;
import ca.ulaval.glo4003.accounts.assemblers.AccountIdAssembler;
import ca.ulaval.glo4003.accounts.domain.AccountId;
import ca.ulaval.glo4003.cars.assemblers.LicensePlateAssembler;
import ca.ulaval.glo4003.cars.domain.LicensePlate;
import ca.ulaval.glo4003.parkings.assemblers.ParkingAreaCodeAssembler;
import ca.ulaval.glo4003.parkings.domain.ParkingAreaCode;
import ca.ulaval.glo4003.times.assemblers.SemesterCodeAssembler;
import ca.ulaval.glo4003.times.domain.DayOfWeek;
import ca.ulaval.glo4003.times.domain.TimePeriod;
import ca.ulaval.glo4003.times.services.SemesterService;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class AccessPassAssembler {

  private final AccountIdAssembler accountIdAssembler;
  private final LicensePlateAssembler licensePlateAssembler;
  private final SemesterService semesterService;
  private final SemesterCodeAssembler semesterCodeAssembler;
  private final ParkingAreaCodeAssembler parkingAreaCodeAssembler;

  public AccessPassAssembler(
      AccountIdAssembler accountIdAssembler,
      LicensePlateAssembler licensePlateAssembler,
      SemesterService semesterService,
      SemesterCodeAssembler semesterCodeAssembler,
      ParkingAreaCodeAssembler parkingAreaCodeAssembler) {
    this.accountIdAssembler = accountIdAssembler;
    this.licensePlateAssembler = licensePlateAssembler;
    this.semesterService = semesterService;
    this.semesterCodeAssembler = semesterCodeAssembler;
    this.parkingAreaCodeAssembler = parkingAreaCodeAssembler;
  }

  public AccessPass assemble(AccessPassDto accessPassCodeDto, String accountId) {
    AccountId id = accountIdAssembler.assemble(accountId);

    // TODO : Do not support dayOfWeek for anything else than 1d/week/semester
    DayOfWeek dayOfWeek;
    if (accessPassCodeDto.accessDay != null) {
      dayOfWeek = DayOfWeek.get(accessPassCodeDto.accessDay);
    } else {
      dayOfWeek = null;
    }

    AccessPeriod period = AccessPeriod.get(accessPassCodeDto.period);

    validateAccessPeriodIsSupported(period);
    validateAmountOfSemesters(accessPassCodeDto.semesters);
    validateCorrectLengthForSemesters(accessPassCodeDto.semesters, period);

    List<TimePeriod> accessPeriods = buildAccessPeriods(accessPassCodeDto.semesters);

    LicensePlate licensePlate;
    ParkingAreaCode parkingAreaCode;
    if (accessPassCodeDto.licensePlate != null) {
      licensePlate = licensePlateAssembler.assemble(accessPassCodeDto.licensePlate);
      parkingAreaCode = parkingAreaCodeAssembler.assemble(accessPassCodeDto.parkingArea);
    } else {
      licensePlate = null;
      parkingAreaCode = null;
    }

    return new AccessPass(id, dayOfWeek, licensePlate, accessPeriods, parkingAreaCode);
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
            && (period == AccessPeriod.ONE_DAY_BY_WEEK_FOR_SEMESTER
                || period == AccessPeriod.ONE_SEMESTER))
        || (semesters.length != 2 && period == AccessPeriod.TWO_SEMESTERS)
        || (semesters.length != 3 && period == AccessPeriod.THREE_SEMESTERS)) {
      throw new WrongAmountOfSemestersForPeriodException();
    }
  }

  // TODO : Instead of setting semester periods here, use a factory from the service (an assembler
  //        must not use a service)
  private List<TimePeriod> buildAccessPeriods(String[] semesters) {
    return Arrays.stream(semesters)
        .map(semesterCodeAssembler::assemble)
        .map(semesterService::getSemester)
        .collect(Collectors.toList());
  }
}
