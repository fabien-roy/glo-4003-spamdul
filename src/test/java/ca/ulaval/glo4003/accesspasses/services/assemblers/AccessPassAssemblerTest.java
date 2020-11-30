package ca.ulaval.glo4003.accesspasses.services.assemblers;

import static ca.ulaval.glo4003.accesspasses.helpers.AccessPassDtoBuilder.anAccessPassDto;
import static ca.ulaval.glo4003.accounts.helpers.AccountMother.createAccountId;
import static ca.ulaval.glo4003.cars.helpers.LicensePlateMother.createLicensePlate;
import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.when;

import ca.ulaval.glo4003.accesspasses.domain.AccessPass;
import ca.ulaval.glo4003.accesspasses.domain.AccessPeriod;
import ca.ulaval.glo4003.accesspasses.exceptions.UnsupportedAccessPeriodException;
import ca.ulaval.glo4003.accesspasses.exceptions.WrongAmountOfSemestersForPeriodException;
import ca.ulaval.glo4003.accesspasses.services.dto.AccessPassDto;
import ca.ulaval.glo4003.accounts.domain.AccountId;
import ca.ulaval.glo4003.cars.assemblers.LicensePlateAssembler;
import ca.ulaval.glo4003.cars.domain.LicensePlate;
import ca.ulaval.glo4003.parkings.assemblers.ParkingAreaCodeAssembler;
import ca.ulaval.glo4003.parkings.domain.ParkingAreaCode;
import ca.ulaval.glo4003.times.assemblers.SemesterCodeAssembler;
import ca.ulaval.glo4003.times.exceptions.InvalidDayOfWeekException;
import ca.ulaval.glo4003.times.services.SemesterService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AccessPassAssemblerTest {

  @Mock private LicensePlateAssembler licensePlateAssembler;
  @Mock private SemesterService semesterService;
  @Mock private SemesterCodeAssembler semesterCodeAssembler;
  @Mock private ParkingAreaCodeAssembler parkingAreaCodeAssembler;

  private AccessPassAssembler accessPassAssembler;

  private final AccountId accountId = createAccountId();
  private final LicensePlate licensePlate = createLicensePlate();
  private AccessPassDto accessPassDto =
      anAccessPassDto()
          .withLicensePlate(licensePlate.toString())
          .withSemesters(new String[] {"A20"})
          .build();

  @Before
  public void setUp() {
    accessPassAssembler =
        new AccessPassAssembler(
            licensePlateAssembler,
            semesterService,
            semesterCodeAssembler,
            parkingAreaCodeAssembler);

    when(licensePlateAssembler.assemble(licensePlate.toString())).thenReturn(licensePlate);
    when(parkingAreaCodeAssembler.assemble(accessPassDto.parkingArea))
        .thenReturn(new ParkingAreaCode(accessPassDto.parkingArea));
  }

  @Test
  public void
      givenOneDayPerWeekPerSemesterPeriod_whenAssembling_thenReturnAccessPassWithAccessDay() {
    accessPassDto =
        anAccessPassDto()
            .withAccessPeriod(AccessPeriod.ONE_DAY_PER_WEEK_PER_SEMESTER.toString())
            .withSemesters(new String[] {"A20"})
            .build();

    AccessPass accessPass = accessPassAssembler.assemble(accessPassDto, accountId.toString());

    assertThat(accessPass.getAccessDay().toString()).isEqualTo(accessPassDto.accessDay);
  }

  @Test(expected = InvalidDayOfWeekException.class)
  public void
      givenOneDayPerWeekPerSemesterPeriodAndInvalidAccessDay_whenAssembling_thenThrowInvalidDayOfWeekException() {
    String invalidAccessDay = "invalidDayOfWeek";
    accessPassDto =
        anAccessPassDto()
            .withAccessPeriod(AccessPeriod.ONE_DAY_PER_WEEK_PER_SEMESTER.toString())
            .withAccessDay(invalidAccessDay)
            .build();

    accessPassAssembler.assemble(accessPassDto, accountId.toString());
  }

  @Test(expected = InvalidDayOfWeekException.class)
  public void
      givenOneDayPerWeekPerSemesterAndNoAccessDay_whenAssembling_thenThrowInvalidDayOfWeekException() {
    accessPassDto =
        anAccessPassDto()
            .withAccessPeriod(AccessPeriod.ONE_DAY_PER_WEEK_PER_SEMESTER.toString())
            .withAccessDay(null)
            .build();

    accessPassAssembler.assemble(accessPassDto, accountId.toString());
  }

  @Test
  public void whenAssembling_thenReturnAccessPassWithLicensePlate() {
    AccessPass accessPass = accessPassAssembler.assemble(accessPassDto, accountId.toString());

    assertThat(accessPass.getLicensePlate()).isSameInstanceAs(licensePlate);
  }

  @Test
  public void whenAssembling_thenReturnIsAdmittedOnCampusAtFalse() {
    AccessPass accessPass = accessPassAssembler.assemble(accessPassDto, accountId.toString());

    assertThat(accessPass.isAdmittedOnCampus()).isSameInstanceAs(false);
  }

  @Test
  public void givenNoLicensePlate_whenAssembling_thenReturnAccessPassWithoutLicensePlate() {
    accessPassDto =
        anAccessPassDto().withLicensePlate(null).withSemesters(new String[] {"A20"}).build();

    AccessPass accessPass = accessPassAssembler.assemble(accessPassDto, accountId.toString());

    assertThat(accessPass.getLicensePlate()).isNull();
  }

  @Test(expected = UnsupportedAccessPeriodException.class)
  public void givenOneHourPeriod_whenAssembling_thenThrowUnsupportedPeriodException() {
    accessPassDto = anAccessPassDto().withAccessPeriod(AccessPeriod.ONE_HOUR.toString()).build();

    accessPassAssembler.assemble(accessPassDto, accountId.toString());
  }

  @Test(expected = UnsupportedAccessPeriodException.class)
  public void givenOneDayPeriod_whenAssembling_thenThrowUnsupportedPeriodException() {
    accessPassDto = anAccessPassDto().withAccessPeriod(AccessPeriod.ONE_DAY.toString()).build();

    accessPassAssembler.assemble(accessPassDto, accountId.toString());
  }

  @Test(expected = WrongAmountOfSemestersForPeriodException.class)
  public void givenWrongAmountOfSemesters_whenAssembling_thenThrowWrongAmountException() {
    accessPassDto =
        anAccessPassDto()
            .withAccessPeriod(AccessPeriod.TWO_SEMESTERS.toString())
            .withSemesters(new String[] {"A20"})
            .build();

    accessPassAssembler.assemble(accessPassDto, accountId.toString());
  }

  @Test
  public void givenNoParkingAreaCode_whenAssembling_thenReturnAccessPassWithoutParkingAreaCode() {
    accessPassDto =
        anAccessPassDto()
            .withLicensePlate(licensePlate.toString())
            .withSemesters(new String[] {"A20"})
            .withParkingAea(null)
            .build();

    AccessPass accessPass = accessPassAssembler.assemble(accessPassDto, accountId.toString());

    assertThat(accessPass.getParkingAreaCode()).isNull();
  }

  @Test
  public void givenParkingAreaCode_whenAssembling_thenReturnAccessPassWithParkingAreaCode() {
    AccessPass accessPass = accessPassAssembler.assemble(accessPassDto, accountId.toString());

    assertThat(accessPass.getParkingAreaCode().toString()).isEqualTo(accessPassDto.parkingArea);
  }

  @Test
  public void givenAccessPeriodOtherThanOneDayAWeekPerSemester_whenAssembling_thenSetNoAccessDay() {
    AccessPass accessPass = accessPassAssembler.assemble(accessPassDto, accountId.toString());

    assertThat(accessPass.getAccessDay()).isNull();
  }
}
