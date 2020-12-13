package ca.ulaval.glo4003.accesspasses.services.converters;

import static ca.ulaval.glo4003.accesspasses.helpers.AccessPassDtoBuilder.anAccessPassDto;
import static ca.ulaval.glo4003.accesspasses.helpers.BicycleAccessPassDtoBuilder.aBicycleAccessPassDto;
import static ca.ulaval.glo4003.cars.helpers.LicensePlateMother.createLicensePlate;
import static ca.ulaval.glo4003.times.helpers.SemesterCodeMother.createSemesterCode;
import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.when;

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
import ca.ulaval.glo4003.communications.domain.exceptions.MissingEmailException;
import ca.ulaval.glo4003.communications.domain.exceptions.MissingPostalCodeException;
import ca.ulaval.glo4003.communications.services.converters.EmailAddressConverter;
import ca.ulaval.glo4003.communications.services.converters.PostalCodeConverter;
import ca.ulaval.glo4003.parkings.domain.ParkingAreaCode;
import ca.ulaval.glo4003.parkings.services.assemblers.ParkingAreaCodeAssembler;
import ca.ulaval.glo4003.times.domain.SemesterCode;
import ca.ulaval.glo4003.times.domain.exceptions.InvalidDayOfWeekException;
import ca.ulaval.glo4003.times.services.SemesterService;
import ca.ulaval.glo4003.times.services.converters.SemesterCodeConverter;
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
  @Mock private SemesterCodeConverter semesterCodeConverter;

  private AccessPassConverter accessPassConverter;

  private final LicensePlate licensePlate = createLicensePlate();
  private AccessPassDto carAccessPassDto =
      anAccessPassDto()
          .withLicensePlate(licensePlate.toString())
          .withSemesters(new String[] {"A20"})
          .build();
  private final BicycleAccessPassDto bicycleAccessPassDto =
      aBicycleAccessPassDto().withSemester("H20").build();
  private final PostalCode postalCode = new PostalCode("A1E 2E1");
  private final EmailAddress emailAddress = new EmailAddress("salut@mail.com");
  private final SemesterCode semesterCode = createSemesterCode();

  @Before
  public void setUp() {
    accessPassConverter =
        new AccessPassConverter(
            licensePlateConverter,
            parkingAreaCodeAssembler,
            semesterService,
            emailAddressConverter,
            postalCodeConverter,
            semesterCodeConverter);

    when(licensePlateConverter.convert(licensePlate.toString())).thenReturn(licensePlate);
    when(parkingAreaCodeAssembler.assemble(carAccessPassDto.parkingArea))
        .thenReturn(new ParkingAreaCode(carAccessPassDto.parkingArea));
    when(semesterCodeConverter.convert(bicycleAccessPassDto.semester)).thenReturn(semesterCode);
  }

  @Test
  public void
      givenOneDayPerWeekPerSemesterPeriod_whenConverting_thenReturnAccessPassWithAccessDay() {
    carAccessPassDto =
        anAccessPassDto()
            .withAccessPeriod(AccessPeriod.ONE_DAY_PER_WEEK_PER_SEMESTER.toString())
            .withSemesters(new String[] {"A20"})
            .build();

    AccessPass accessPass = accessPassConverter.convert(carAccessPassDto);

    assertThat(accessPass.getAccessDay().toString()).isEqualTo(carAccessPassDto.accessDay);
  }

  @Test(expected = InvalidDayOfWeekException.class)
  public void
      givenOneDayPerWeekPerSemesterPeriodAndInvalidAccessDay_whenConverting_thenThrowInvalidDayOfWeekException() {
    String invalidAccessDay = "invalidDayOfWeek";
    carAccessPassDto =
        anAccessPassDto()
            .withAccessPeriod(AccessPeriod.ONE_DAY_PER_WEEK_PER_SEMESTER.toString())
            .withAccessDay(invalidAccessDay)
            .build();

    accessPassConverter.convert(carAccessPassDto);
  }

  @Test(expected = InvalidDayOfWeekException.class)
  public void
      givenOneDayPerWeekPerSemesterAndNoAccessDay_whenConverting_thenThrowInvalidDayOfWeekException() {
    carAccessPassDto =
        anAccessPassDto()
            .withAccessPeriod(AccessPeriod.ONE_DAY_PER_WEEK_PER_SEMESTER.toString())
            .withAccessDay(null)
            .build();

    accessPassConverter.convert(carAccessPassDto);
  }

  @Test
  public void whenConverting_thenReturnAccessPassWithLicensePlate() {
    AccessPass accessPass = accessPassConverter.convert(carAccessPassDto);

    assertThat(accessPass.getLicensePlate()).isSameInstanceAs(licensePlate);
  }

  @Test
  public void whenConverting_thenReturnIsAdmittedOnCampusAtFalse() {
    AccessPass accessPass = accessPassConverter.convert(carAccessPassDto);

    assertThat(accessPass.isAdmittedOnCampus()).isSameInstanceAs(false);
  }

  @Test
  public void givenNoLicensePlate_whenConverting_thenReturnAccessPassWithoutLicensePlate() {
    carAccessPassDto =
        anAccessPassDto().withLicensePlate(null).withSemesters(new String[] {"A20"}).build();

    AccessPass accessPass = accessPassConverter.convert(carAccessPassDto);

    assertThat(accessPass.getLicensePlate()).isNull();
  }

  @Test(expected = UnsupportedAccessPeriodException.class)
  public void givenOneHourPeriod_whenConverting_thenThrowUnsupportedPeriodException() {
    carAccessPassDto = anAccessPassDto().withAccessPeriod(AccessPeriod.ONE_HOUR.toString()).build();

    accessPassConverter.convert(carAccessPassDto);
  }

  @Test(expected = UnsupportedAccessPeriodException.class)
  public void givenOneDayPeriod_whenConverting_thenThrowUnsupportedPeriodException() {
    carAccessPassDto = anAccessPassDto().withAccessPeriod(AccessPeriod.ONE_DAY.toString()).build();

    accessPassConverter.convert(carAccessPassDto);
  }

  @Test(expected = WrongAmountOfSemestersForPeriodException.class)
  public void givenWrongAmountOfSemesters_whenConverting_thenThrowWrongAmountException() {
    carAccessPassDto =
        anAccessPassDto()
            .withAccessPeriod(AccessPeriod.TWO_SEMESTERS.toString())
            .withSemesters(new String[] {"A20"})
            .build();

    accessPassConverter.convert(carAccessPassDto);
  }

  @Test
  public void givenNoParkingAreaCode_whenConverting_thenReturnAccessPassWithoutParkingAreaCode() {
    carAccessPassDto =
        anAccessPassDto()
            .withLicensePlate(licensePlate.toString())
            .withSemesters(new String[] {"A20"})
            .withParkingAea(null)
            .build();

    AccessPass accessPass = accessPassConverter.convert(carAccessPassDto);

    assertThat(accessPass.getParkingAreaCode()).isNull();
  }

  @Test
  public void givenParkingAreaCode_whenConverting_thenReturnAccessPassWithParkingAreaCode() {
    AccessPass accessPass = accessPassConverter.convert(carAccessPassDto);

    assertThat(accessPass.getParkingAreaCode().toString()).isEqualTo(carAccessPassDto.parkingArea);
  }

  @Test
  public void givenAccessPeriodOtherThanOneDayAWeekPerSemester_whenConverting_thenSetNoAccessDay() {
    AccessPass accessPass = accessPassConverter.convert(carAccessPassDto);

    assertThat(accessPass.getAccessDay()).isNull();
  }

  @Test
  public void
      givenBicycleAccessPassDto_whenConverting_thenReturnAccessPassWithThreeSemestersAccessPeriod() {
    AccessPass accessPass = accessPassConverter.convert(bicycleAccessPassDto);

    assertThat(accessPass.getAccessPeriod().toString())
        .isEqualTo(AccessPeriod.THREE_SEMESTERS.toString());
  }

  @Test
  public void givenBicycleAccessPassDto_whenConverting_thenReturnAccessPassWithNullAccessDay() {
    AccessPass accessPass = accessPassConverter.convert(bicycleAccessPassDto);

    assertThat(accessPass.getAccessDay()).isNull();
  }

  @Test
  public void givenBicycleAccessPassDto_whenConverting_thenReturnAccessPassWithNullLicensePlate() {
    AccessPass accessPass = accessPassConverter.convert(bicycleAccessPassDto);

    assertThat(accessPass.getLicensePlate()).isNull();
  }

  @Test
  public void
      givenBicycleAccessPassDto_whenConverting_thenReturnAccessPassWithZoneVeloParkingAreaCode() {
    AccessPass accessPass = accessPassConverter.convert(bicycleAccessPassDto);

    assertThat(accessPass.getParkingAreaCode().toString()).isEqualTo("ZoneVelo");
  }

  @Test(expected = MissingEmailException.class)
  public void
      givenEmailReceptionMethodWithoutEmailAddress_whenConverting_thenThrowMissingEmailException() {
    BicycleAccessPassDto bicycleAccessPassDto =
        aBicycleAccessPassDto()
            .withSemester("H20")
            .withReceptionMethod("email")
            .withEmailAddress(null)
            .build();

    accessPassConverter.convert(bicycleAccessPassDto);
  }

  @Test(expected = MissingPostalCodeException.class)
  public void
      givenPotalReceptionMethodWithoutPostalCoe_whenConverting_thenThrowMissingPostalCodeException() {
    BicycleAccessPassDto bicycleAccessPassDto =
        aBicycleAccessPassDto()
            .withSemester("H20")
            .withReceptionMethod("postal")
            .withPostalCode(null)
            .build();

    accessPassConverter.convert(bicycleAccessPassDto);
  }

  @Test
  public void
      givenEmailReceptionMethodWithEmailAddress_whenConverting_thenReturnAccessPassWithValidEmailAddress() {
    BicycleAccessPassDto bicycleAccessPassDto =
        aBicycleAccessPassDto()
            .withSemester("H20")
            .withReceptionMethod("email")
            .withEmailAddress(emailAddress.toString())
            .build();
    when(emailAddressConverter.convert(bicycleAccessPassDto.email)).thenReturn(emailAddress);

    AccessPass accessPass = accessPassConverter.convert(bicycleAccessPassDto);

    assertThat(accessPass.getEmailAddress()).isEqualTo(emailAddress);
  }

  @Test
  public void
      givenPostalReceptionMethodWithPostalCode_whenConverting_thenReturnAccessPassWithValidPostalCode() {
    BicycleAccessPassDto bicycleAccessPassDto =
        aBicycleAccessPassDto()
            .withSemester("H20")
            .withReceptionMethod("postal")
            .withPostalCode(postalCode.toString())
            .build();
    when(postalCodeConverter.convert(bicycleAccessPassDto.postalCode)).thenReturn(postalCode);

    AccessPass accessPass = accessPassConverter.convert(bicycleAccessPassDto);

    assertThat(accessPass.getPostalCode()).isEqualTo(postalCode);
  }
}
