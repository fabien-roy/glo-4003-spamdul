package ca.ulaval.glo4003.accesspasses.domain;

import static ca.ulaval.glo4003.accesspasses.helpers.AccessPassBuilder.anAccessPass;
import static com.google.common.truth.Truth.assertThat;

import ca.ulaval.glo4003.accesspasses.exceptions.InvalidAccessPassEntryException;
import ca.ulaval.glo4003.accesspasses.exceptions.InvalidAccessPassExitException;
import ca.ulaval.glo4003.times.domain.CustomDateTime;
import ca.ulaval.glo4003.times.domain.DayOfWeek;
import org.junit.Test;

public class AccessPassTest {

  private static final DayOfWeek VALID_DAY = DayOfWeek.MONDAY;
  private static final DayOfWeek INVALID_DAY = DayOfWeek.FRIDAY;

  private final AccessPass accessPassWithNoCampusAccess =
      anAccessPass().withValidDay(VALID_DAY).build();
  private final AccessPass accessPassWithCampusAccess =
      anAccessPass().withValidDay(VALID_DAY).withAccessToCampus().build();

  @Test
  public void givenAccessPassNotAdmittedOnCampus_whenEnteringCampus_shouldBeAdmittedOnCampus() {
    AccessPass accessPassWithNoCampusAccess = anAccessPass().withValidDay(VALID_DAY).build();

    accessPassWithNoCampusAccess.enterCampus();

    assertThat(accessPassWithNoCampusAccess.isAdmittedOnCampus()).isTrue();
  }

  @Test(expected = InvalidAccessPassEntryException.class)
  public void givenAccessPassAlreadyOnCampus_whenEnteringCampus_thenThrowException() {
    accessPassWithCampusAccess.enterCampus();
    accessPassWithCampusAccess.enterCampus();
  }

  @Test
  public void givenAccessPassAdmittedOnCampus_whenExitingCampus_shouldNoLongerBeAdmittedOnCampus() {
    AccessPass accessPassWithCampusAccess =
        anAccessPass().withValidDay(VALID_DAY).withAccessToCampus().build();

    accessPassWithCampusAccess.enterCampus();
    accessPassWithCampusAccess.exitCampus();

    assertThat(accessPassWithCampusAccess.isAdmittedOnCampus()).isFalse();
  }

  @Test(expected = InvalidAccessPassExitException.class)
  public void givenAccessPassNotAdmittedOnCampus_whenExitingCampus_thenThrowException() {
    accessPassWithNoCampusAccess.exitCampus();
  }

  @Test
  public void givenAccessPassWithPeriodThatContainsToday_whenValidateAccess_thenAccessGranted() {
    AccessPass testPass = anAccessPass().addAccessPeriodCurrentlyValid().build();

    boolean granted = testPass.validateAccess(CustomDateTime.now());

    assertThat(granted).isTrue();
  }

  @Test
  public void givenAccessPassWithoutAPeriodThatContainsToday_whenValidateAccess_thenAccessDenied() {
    AccessPass testPass = anAccessPass().addAccessPeriodCurrentlyInvalid().build();

    boolean granted = testPass.validateAccess(CustomDateTime.now());

    assertThat(granted).isFalse();
  }

  @Test
  public void
      givenAccessPassWithMultiplePeriodsIncludingOneValidToday_whenValidateAccess_thenAccessGranted() {
    AccessPass testPass =
        anAccessPass()
            .addAccessPeriodCurrentlyInvalid()
            .addAccessPeriodCurrentlyInvalid()
            .addAccessPeriodCurrentlyValid()
            .build();

    boolean granted = testPass.validateAccess(CustomDateTime.now());

    assertThat(granted).isTrue();
  }

  @Test
  public void givenAccessPassValidThisDayOfTheWeek_whenValidateAccess_thenAccessGranted() {
    CustomDateTime now = CustomDateTime.now();
    DayOfWeek today = now.getDayOfWeek();
    AccessPass testPass =
        anAccessPass().withValidDay(today).addAccessPeriodCurrentlyValid().build();

    boolean granted = testPass.validateAccess(now);

    assertThat(granted).isTrue();
  }

  @Test
  public void givenAccessPassNotValidThisDayOfTheWeek_whenValidateAccess_thenAccessDenied() {
    CustomDateTime now = CustomDateTime.now();
    CustomDateTime inOneDay = CustomDateTime.now().plusDays(1);
    DayOfWeek tomorrow = inOneDay.getDayOfWeek();
    AccessPass testPass =
        anAccessPass().withValidDay(tomorrow).addAccessPeriodCurrentlyValid().build();

    boolean granted = testPass.validateAccess(now);

    assertThat(granted).isFalse();
  }
}
