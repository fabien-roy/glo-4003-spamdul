package ca.ulaval.glo4003.accesspasses.services.converters;

import static ca.ulaval.glo4003.accesspasses.helpers.AccessPassDtoBuilder.anAccessPassDto;
import static ca.ulaval.glo4003.cars.helpers.LicensePlateMother.createLicensePlate;
import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.when;

import ca.ulaval.glo4003.accesspasses.domain.AccessPass;
import ca.ulaval.glo4003.accesspasses.domain.AccessPeriod;
import ca.ulaval.glo4003.accesspasses.domain.exceptions.UnsupportedAccessPeriodException;
import ca.ulaval.glo4003.accesspasses.domain.exceptions.WrongAmountOfSemestersForPeriodException;
import ca.ulaval.glo4003.accesspasses.services.dto.AccessPassDto;
import ca.ulaval.glo4003.cars.domain.LicensePlate;
import ca.ulaval.glo4003.cars.services.converters.LicensePlateConverter;
import ca.ulaval.glo4003.communications.services.converters.EmailAddressConverter;
import ca.ulaval.glo4003.locations.services.converters.PostalCodeConverter;
import ca.ulaval.glo4003.parkings.domain.ParkingAreaCode;
import ca.ulaval.glo4003.parkings.services.assemblers.ParkingAreaCodeAssembler;
import ca.ulaval.glo4003.times.domain.exceptions.InvalidDayOfWeekException;
import ca.ulaval.glo4003.times.services.SemesterService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AccessPassConverterTest {

  @Mock private LicensePlateConverter licensePlateConverter;
  @Mock private ParkingAreaCodeAssembler parkingAreaCodeAssembler;
  @Mock private SemesterService semesterService;
  @Mock private EmailAddressConverter emailAddressConverter;
  @Mock private PostalCodeConverter postalCodeConverter;

  private AccessPassConverter accessPassConverter;

  private final LicensePlate licensePlate = createLicensePlate();
  private AccessPassDto accessPassDto =
      anAccessPassDto()
          .withLicensePlate(licensePlate.toString())
          .withSemesters(new String[] {"A20"})
          .build();

  @Before
  public void setUp() {
    accessPassConverter =
        new AccessPassConverter(
            licensePlateConverter,
            parkingAreaCodeAssembler,
            semesterService,
            emailAddressConverter,
            postalCodeConverter);

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

    AccessPass accessPass = accessPassConverter.convert(accessPassDto);

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

    accessPassConverter.convert(accessPassDto);
  }

  @Test(expected = InvalidDayOfWeekException.class)
  public void
      givenOneDayPerWeekPerSemesterAndNoAccessDay_whenConverting_thenThrowInvalidDayOfWeekException() {
    accessPassDto =
        anAccessPassDto()
            .withAccessPeriod(AccessPeriod.ONE_DAY_PER_WEEK_PER_SEMESTER.toString())
            .withAccessDay(null)
            .build();

    accessPassConverter.convert(accessPassDto);
  }

  @Test
  public void whenConverting_thenReturnAccessPassWithLicensePlate() {
    AccessPass accessPass = accessPassConverter.convert(accessPassDto);

    assertThat(accessPass.getLicensePlate()).isSameInstanceAs(licensePlate);
  }

  @Test
  public void whenConverting_thenReturnIsAdmittedOnCampusAtFalse() {
    AccessPass accessPass = accessPassConverter.convert(accessPassDto);

    assertThat(accessPass.isAdmittedOnCampus()).isSameInstanceAs(false);
  }

  @Test
  public void givenNoLicensePlate_whenConverting_thenReturnAccessPassWithoutLicensePlate() {
    accessPassDto =
        anAccessPassDto().withLicensePlate(null).withSemesters(new String[] {"A20"}).build();

    AccessPass accessPass = accessPassConverter.convert(accessPassDto);

    assertThat(accessPass.getLicensePlate()).isNull();
  }

  @Test(expected = UnsupportedAccessPeriodException.class)
  public void givenOneHourPeriod_whenConverting_thenThrowUnsupportedPeriodException() {
    accessPassDto = anAccessPassDto().withAccessPeriod(AccessPeriod.ONE_HOUR.toString()).build();

    accessPassConverter.convert(accessPassDto);
  }

  @Test(expected = UnsupportedAccessPeriodException.class)
  public void givenOneDayPeriod_whenConverting_thenThrowUnsupportedPeriodException() {
    accessPassDto = anAccessPassDto().withAccessPeriod(AccessPeriod.ONE_DAY.toString()).build();

    accessPassConverter.convert(accessPassDto);
  }

  @Test(expected = WrongAmountOfSemestersForPeriodException.class)
  public void givenWrongAmountOfSemesters_whenConverting_thenThrowWrongAmountException() {
    accessPassDto =
        anAccessPassDto()
            .withAccessPeriod(AccessPeriod.TWO_SEMESTERS.toString())
            .withSemesters(new String[] {"A20"})
            .build();

    accessPassConverter.convert(accessPassDto);
  }

  @Test
  public void givenNoParkingAreaCode_whenConverting_thenReturnAccessPassWithoutParkingAreaCode() {
    accessPassDto =
        anAccessPassDto()
            .withLicensePlate(licensePlate.toString())
            .withSemesters(new String[] {"A20"})
            .withParkingAea(null)
            .build();

    AccessPass accessPass = accessPassConverter.convert(accessPassDto);

    assertThat(accessPass.getParkingAreaCode()).isNull();
  }

  @Test
  public void givenParkingAreaCode_whenConverting_thenReturnAccessPassWithParkingAreaCode() {
    AccessPass accessPass = accessPassConverter.convert(accessPassDto);

    assertThat(accessPass.getParkingAreaCode().toString()).isEqualTo(accessPassDto.parkingArea);
  }

  @Test
  public void givenAccessPeriodOtherThanOneDayAWeekPerSemester_whenConverting_thenSetNoAccessDay() {
    AccessPass accessPass = accessPassConverter.convert(accessPassDto);

    assertThat(accessPass.getAccessDay()).isNull();
  }
}
