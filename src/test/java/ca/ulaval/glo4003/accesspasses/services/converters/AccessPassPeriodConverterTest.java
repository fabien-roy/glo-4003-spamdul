package ca.ulaval.glo4003.accesspasses.services.converters;

import static com.google.common.truth.Truth.assertThat;

import ca.ulaval.glo4003.accesspasses.domain.AccessPeriod;
import ca.ulaval.glo4003.accesspasses.domain.AccessPeriodInFrench;
import org.junit.Before;
import org.junit.Test;

public class AccessPassPeriodConverterTest {
  private AccessPassPeriodConverter accessPassPeriodConverter;

  @Before
  public void setUp() {
    accessPassPeriodConverter = new AccessPassPeriodConverter();
  }

  @Test
  public void
      givenUneJourneeParSemaineParSession_whenConverting_thenReturnOneDayPerWeekPerSession() {
    AccessPeriodInFrench accessPeriodInFrench =
        AccessPeriodInFrench.UNE_JOURNEE_PAR_SEMAINE_PAR_SESSION;

    AccessPeriod accessPeriod = accessPassPeriodConverter.convert(accessPeriodInFrench);

    assertThat(accessPeriod).isEqualTo(AccessPeriod.ONE_DAY_PER_WEEK_PER_SEMESTER);
  }

  @Test
  public void givenUneSession_whenConverting_thenReturnOneSemester() {
    AccessPeriodInFrench accessPeriodInFrench = AccessPeriodInFrench.UNE_SESSION;

    AccessPeriod accessPeriod = accessPassPeriodConverter.convert(accessPeriodInFrench);

    assertThat(accessPeriod).isEqualTo(AccessPeriod.ONE_SEMESTER);
  }

  @Test
  public void givenDeuxSessions_whenConverting_thenReturnTwoSemesters() {
    AccessPeriodInFrench accessPeriodInFrench = AccessPeriodInFrench.DEUX_SESSIONS;

    AccessPeriod accessPeriod = accessPassPeriodConverter.convert(accessPeriodInFrench);

    assertThat(accessPeriod).isEqualTo(AccessPeriod.TWO_SEMESTERS);
  }

  @Test
  public void givenTroisSessions_whenConverting_thenReturnThreeSemesters() {
    AccessPeriodInFrench accessPeriodInFrench = AccessPeriodInFrench.TROIS_SESSIONS;

    AccessPeriod accessPeriod = accessPassPeriodConverter.convert(accessPeriodInFrench);

    assertThat(accessPeriod).isEqualTo(AccessPeriod.THREE_SEMESTERS);
  }
}
