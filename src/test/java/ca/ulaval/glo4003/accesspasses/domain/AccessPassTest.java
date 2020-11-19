package ca.ulaval.glo4003.accesspasses.domain;

import static ca.ulaval.glo4003.accesspasses.helpers.AccessPassBuilder.anAccessPass;

import ca.ulaval.glo4003.times.domain.DayOfWeek;

public class AccessPassTest {

  // TODO Redo all of this
  private static final DayOfWeek VALID_DAY = DayOfWeek.MONDAY;
  private static final DayOfWeek INVALID_DAY = DayOfWeek.FRIDAY;

  private final AccessPass accessPassWithNoCampusAccess =
      anAccessPass().withValidDay(VALID_DAY).build();
  private final AccessPass accessPassWithCampusAccess =
      anAccessPass().withValidDay(VALID_DAY).withAccessToCampus().build();

  /*@Test
    public void givenAccessDayOnValidDay_whenValidatingAccessDay_thenReturnTrue() {
      boolean isAccessDayValid = accessPassWithNoCampusAccess.validateAccessDay(VALID_DAY);

      assertThat(isAccessDayValid).isTrue();
    }

    @Test
    public void givenAccessDayNotOnValidDay_whenValidatingAccessDay_thenReturnFalse() {
      boolean isAccessDayValid = accessPassWithNoCampusAccess.validateAccessDay(INVALID_DAY);

      assertThat(isAccessDayValid).isFalse();
  <<<<<<< HEAD
    }

    @Test
    public void givenAccessPassNotAdmittedOnCampus_whenEnteringCampus_shouldBeAdmittedOnCampus() {
      AccessPass accessPassWithNoCampusAccess = anAccessPass().withValidDay(VALID_DAY).build();

      accessPassWithNoCampusAccess.enterCampus();

      assertThat(accessPassWithNoCampusAccess.isAdmittedOnCampus()).isTrue();
    }

    @Test(expected = InvalidAccessPassEntryException.class)
    public void givenAccessPassAlreadyOnCampus_whenEnteringCampus_thenThrowException() {
      accessPassWithCampusAccess.enterCampus();
    }

    @Test
    public void givenAccessPassAdmittedOnCampus_whenExitingCampus_shouldNoLongerBeAdmittedOnCampus() {
      AccessPass accessPassWithCampusAccess =
          anAccessPass().withValidDay(VALID_DAY).withAccessToCampus().build();

      accessPassWithCampusAccess.exitCampus();

      assertThat(accessPassWithCampusAccess.isAdmittedOnCampus()).isFalse();
    }

    @Test(expected = InvalidAccessPassExitException.class)
    public void givenAccessPassNotAdmittedOnCampus_whenExitingCampus_thenThrowException() {
      accessPassWithNoCampusAccess.exitCampus();
    }
  =======
    }*/
}
