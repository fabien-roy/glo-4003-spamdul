package ca.ulaval.glo4003.accesspasses.assembler;

import ca.ulaval.glo4003.accesspasses.api.dto.AccessPassDto;
import ca.ulaval.glo4003.accesspasses.domain.AccessPass;
import ca.ulaval.glo4003.accesspasses.domain.AccessPeriod;
import ca.ulaval.glo4003.accesspasses.exceptions.InvalidAccessPeriodException;
import ca.ulaval.glo4003.accounts.assemblers.AccountIdAssembler;
import ca.ulaval.glo4003.accounts.domain.AccountId;
import ca.ulaval.glo4003.cars.assemblers.LicensePlateAssembler;
import ca.ulaval.glo4003.cars.domain.LicensePlate;
import ca.ulaval.glo4003.times.assemblers.SemesterCodeAssembler;
import ca.ulaval.glo4003.times.domain.DayOfWeek;
import ca.ulaval.glo4003.times.domain.SemesterCode;
import ca.ulaval.glo4003.times.domain.TimePeriod;
import ca.ulaval.glo4003.times.services.SemesterService;
import java.util.ArrayList;
import java.util.List;

public class AccessPassAssembler {

  private final AccountIdAssembler accountIdAssembler;
  private final LicensePlateAssembler licensePlateAssembler;
  private final SemesterService semesterService;
  private final SemesterCodeAssembler semesterCodeAssembler;

  public AccessPassAssembler(
      AccountIdAssembler accountIdAssembler,
      LicensePlateAssembler licensePlateAssembler,
      SemesterService semesterService,
      SemesterCodeAssembler semesterCodeAssembler) {
    this.accountIdAssembler = accountIdAssembler;
    this.licensePlateAssembler = licensePlateAssembler;
    this.semesterService = semesterService;
    this.semesterCodeAssembler = semesterCodeAssembler;
  }

  public AccessPass assemble(AccessPassDto accessPassCodeDto, String accountId) {
    AccountId id = accountIdAssembler.assemble(accountId);
    DayOfWeek dayOfWeek;
    if (accessPassCodeDto.accessDay != null) {
      dayOfWeek = DayOfWeek.get(accessPassCodeDto.accessDay);
    } else {
      dayOfWeek = null;
    }
    AccessPeriod period = AccessPeriod.get(accessPassCodeDto.period);
    List<TimePeriod> accessPeriods = new ArrayList<>();
    if (accessPassCodeDto.semesters != null && accessPassCodeDto.semesters.length != 0) {
      // This checks to see if there are 1, 2, or 3 semesters in the array and if the chosen period
      // matches that
      if ((accessPassCodeDto.semesters.length != 1
              && (period == AccessPeriod.ONE_DAY_BY_WEEK_FOR_SEMESTER
                  || period == AccessPeriod.ONE_SEMESTER))
          || (accessPassCodeDto.semesters.length != 2 && period == AccessPeriod.TWO_SEMESTERS)
          || (accessPassCodeDto.semesters.length != 3 && period == AccessPeriod.THREE_SEMESTERS)) {
        throw new InvalidAccessPeriodException();
      }
      for (String semester : accessPassCodeDto.semesters) {
        SemesterCode code = semesterCodeAssembler.assemble(semester);
        TimePeriod semesterPeriod = semesterService.getSemester(code);
        accessPeriods.add(semesterPeriod);
      }
    }
    LicensePlate licensePlate;
    if (accessPassCodeDto.licensePlate != null) {
      licensePlate = licensePlateAssembler.assemble(accessPassCodeDto.licensePlate);
    } else {
      licensePlate = null;
    }
    return new AccessPass(id, dayOfWeek, licensePlate, accessPeriods);
  }
}
