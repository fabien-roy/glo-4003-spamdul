package ca.ulaval.glo4003.accesspasses.services.converters;

import ca.ulaval.glo4003.accesspasses.domain.AccessPass;
import ca.ulaval.glo4003.accesspasses.domain.AccessPeriod;
import ca.ulaval.glo4003.accesspasses.exceptions.UnsupportedAccessPeriodException;
import ca.ulaval.glo4003.accesspasses.exceptions.WrongAmountOfSemestersForPeriodException;
import ca.ulaval.glo4003.accesspasses.services.dto.AccessPassDto;
import ca.ulaval.glo4003.cars.domain.LicensePlate;
import ca.ulaval.glo4003.cars.services.converters.LicensePlateConverter;
import ca.ulaval.glo4003.parkings.domain.ParkingAreaCode;
import ca.ulaval.glo4003.parkings.services.assemblers.ParkingAreaCodeAssembler;
import ca.ulaval.glo4003.times.domain.DayOfWeek;
import ca.ulaval.glo4003.times.domain.TimePeriod;
import java.util.List;

public class AccessPassConverter {

  private final LicensePlateConverter licensePlateConverter;
  private final ParkingAreaCodeAssembler parkingAreaCodeAssembler;

  public AccessPassConverter(
      LicensePlateConverter licensePlateConverter,
      ParkingAreaCodeAssembler parkingAreaCodeAssembler) {
    this.licensePlateConverter = licensePlateConverter;
    this.parkingAreaCodeAssembler = parkingAreaCodeAssembler;
  }

  public AccessPass convert(AccessPassDto accessPassCodeDto, List<TimePeriod> timePeriods) {
    AccessPeriod period = AccessPeriod.get(accessPassCodeDto.period);

    DayOfWeek dayOfWeek =
        period == AccessPeriod.ONE_DAY_PER_WEEK_PER_SEMESTER
            ? DayOfWeek.get(accessPassCodeDto.accessDay)
            : null;

    validateAccessPeriodIsSupported(period);
    validateAmountOfSemesters(accessPassCodeDto.semesters);
    validateCorrectLengthForSemesters(accessPassCodeDto.semesters, period);

    LicensePlate licensePlate;
    ParkingAreaCode parkingAreaCode;
    if (accessPassCodeDto.licensePlate != null) {
      licensePlate = licensePlateConverter.convert(accessPassCodeDto.licensePlate);
      parkingAreaCode = parkingAreaCodeAssembler.assemble(accessPassCodeDto.parkingArea);
    } else {
      licensePlate = null;
      parkingAreaCode = null;
    }

    return new AccessPass(dayOfWeek, licensePlate, timePeriods, parkingAreaCode);
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
