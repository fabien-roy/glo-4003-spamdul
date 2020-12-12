package ca.ulaval.glo4003.accesspasses.services.converters;

import static ca.ulaval.glo4003.accesspasses.helpers.AccessPassDtoBuilder.anAccessPassDto;
import static ca.ulaval.glo4003.cars.helpers.LicensePlateMother.createLicensePlate;
import static ca.ulaval.glo4003.times.helpers.TimePeriodBuilder.aTimePeriod;
import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.when;

import ca.ulaval.glo4003.accesspasses.domain.AccessPass;
import ca.ulaval.glo4003.accesspasses.domain.AccessPeriod;
import ca.ulaval.glo4003.accesspasses.exceptions.UnsupportedAccessPeriodException;
import ca.ulaval.glo4003.accesspasses.exceptions.WrongAmountOfSemestersForPeriodException;
import ca.ulaval.glo4003.accesspasses.services.dto.AccessPassDto;
import ca.ulaval.glo4003.cars.domain.LicensePlate;
import ca.ulaval.glo4003.cars.services.converters.LicensePlateConverter;
import ca.ulaval.glo4003.parkings.domain.ParkingAreaCode;
import ca.ulaval.glo4003.parkings.services.assemblers.ParkingAreaCodeAssembler;
import ca.ulaval.glo4003.times.domain.TimePeriod;
import ca.ulaval.glo4003.times.exceptions.InvalidDayOfWeekException;
import java.util.Collections;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AccessPassConverterTest {

  @Mock private LicensePlateConverter licensePlateConverter;
  @Mock private ParkingAreaCodeAssembler parkingAreaCodeAssembler;

  private AccessPassConverter accessPassConverter;

  private final LicensePlate licensePlate = createLicensePlate();
  private final List<TimePeriod> timePeriods = Collections.singletonList(aTimePeriod().build());
  private AccessPassDto accessPassDto =
      anAccessPassDto()
          .withLicensePlate(licensePlate.toString())
          .withSemesters(new String[] {"A20"})
          .build();

  @Before
  public void setUp() {
    accessPassConverter = new AccessPassConverter(licensePlateConverter, parkingAreaCodeAssembler);

    when(licensePlateConverter.convert(licensePlate.toString())).thenReturn(licensePlate);
    when(parkingAreaCodeAssembler.assemble(accessPassDto.parkingArea))
        .thenReturn(new ParkingAreaCode(accessPassDto.parkingArea));
  }

  @Test
  public void
      givenOneDayPerWeekPerSemesterPeriod_whenConverting_thenReturnAccessPassWithAccessDay() {
    accessPassDto =
        anAccessPassDto()
            .withAccessPeriod(AccessPeriod.ONE_DAY_PER_WEEK_PER_SEMESTER.toString())
            .withSemesters(new String[] {"A20"})
            .build();

    AccessPass accessPass = accessPassConverter.convert(accessPassDto, timePeriods);

    assertThat(accessPass.getAccessDay().toString()).isEqualTo(accessPassDto.accessDay);
  }

  @Test(expected = InvalidDayOfWeekException.class)
  public void
      givenOneDayPerWeekPerSemesterPeriodAndInvalidAccessDay_whenConverting_thenThrowInvalidDayOfWeekException() {
    String invalidAccessDay = "invalidDayOfWeek";
    accessPassDto =
        anAccessPassDto()
            .withAccessPeriod(AccessPeriod.ONE_DAY_PER_WEEK_PER_SEMESTER.toString())
            .withAccessDay(invalidAccessDay)
            .build();

    accessPassConverter.convert(accessPassDto, timePeriods);
  }

  @Test(expected = InvalidDayOfWeekException.class)
  public void
      givenOneDayPerWeekPerSemesterAndNoAccessDay_whenConverting_thenThrowInvalidDayOfWeekException() {
    accessPassDto =
        anAccessPassDto()
            .withAccessPeriod(AccessPeriod.ONE_DAY_PER_WEEK_PER_SEMESTER.toString())
            .withAccessDay(null)
            .build();

    accessPassConverter.convert(accessPassDto, timePeriods);
  }

  @Test
  public void whenConverting_thenReturnAccessPassWithLicensePlate() {
    AccessPass accessPass = accessPassConverter.convert(accessPassDto, timePeriods);

    assertThat(accessPass.getLicensePlate()).isSameInstanceAs(licensePlate);
  }

  @Test
  public void whenConverting_thenReturnIsAdmittedOnCampusAtFalse() {
    AccessPass accessPass = accessPassConverter.convert(accessPassDto, timePeriods);

    assertThat(accessPass.isAdmittedOnCampus()).isSameInstanceAs(false);
  }

  @Test
  public void givenNoLicensePlate_whenConverting_thenReturnAccessPassWithoutLicensePlate() {
    accessPassDto =
        anAccessPassDto().withLicensePlate(null).withSemesters(new String[] {"A20"}).build();

    AccessPass accessPass = accessPassConverter.convert(accessPassDto, timePeriods);

    assertThat(accessPass.getLicensePlate()).isNull();
  }

  @Test(expected = UnsupportedAccessPeriodException.class)
  public void givenOneHourPeriod_whenConverting_thenThrowUnsupportedPeriodException() {
    accessPassDto = anAccessPassDto().withAccessPeriod(AccessPeriod.ONE_HOUR.toString()).build();

    accessPassConverter.convert(accessPassDto, timePeriods);
  }

  @Test(expected = UnsupportedAccessPeriodException.class)
  public void givenOneDayPeriod_whenConverting_thenThrowUnsupportedPeriodException() {
    accessPassDto = anAccessPassDto().withAccessPeriod(AccessPeriod.ONE_DAY.toString()).build();

    accessPassConverter.convert(accessPassDto, timePeriods);
  }

  @Test(expected = WrongAmountOfSemestersForPeriodException.class)
  public void givenWrongAmountOfSemesters_whenConverting_thenThrowWrongAmountException() {
    accessPassDto =
        anAccessPassDto()
            .withAccessPeriod(AccessPeriod.TWO_SEMESTERS.toString())
            .withSemesters(new String[] {"A20"})
            .build();

    accessPassConverter.convert(accessPassDto, timePeriods);
  }

  @Test
  public void givenNoParkingAreaCode_whenConverting_thenReturnAccessPassWithoutParkingAreaCode() {
    accessPassDto =
        anAccessPassDto()
            .withLicensePlate(licensePlate.toString())
            .withSemesters(new String[] {"A20"})
            .withParkingAea(null)
            .build();

    AccessPass accessPass = accessPassConverter.convert(accessPassDto, timePeriods);

    assertThat(accessPass.getParkingAreaCode()).isNull();
  }

  @Test
  public void givenParkingAreaCode_whenConverting_thenReturnAccessPassWithParkingAreaCode() {
    AccessPass accessPass = accessPassConverter.convert(accessPassDto, timePeriods);

    assertThat(accessPass.getParkingAreaCode().toString()).isEqualTo(accessPassDto.parkingArea);
  }

  @Test
  public void givenAccessPeriodOtherThanOneDayAWeekPerSemester_whenConverting_thenSetNoAccessDay() {
    AccessPass accessPass = accessPassConverter.convert(accessPassDto, timePeriods);

    assertThat(accessPass.getAccessDay()).isNull();
  }
}
