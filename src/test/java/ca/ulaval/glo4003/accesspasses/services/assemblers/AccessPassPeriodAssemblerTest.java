package ca.ulaval.glo4003.accesspasses.services.assemblers;

import static com.google.common.truth.Truth.assertThat;

import ca.ulaval.glo4003.accesspasses.domain.AccessPeriod;
import ca.ulaval.glo4003.accesspasses.domain.AccessPeriodInFrench;
import org.junit.Before;
import org.junit.Test;

public class AccessPassPeriodAssemblerTest {
  private AccessPassPeriodAssembler accessPassPeriodAssembler;

  @Before
  public void setUp() {
    accessPassPeriodAssembler = new AccessPassPeriodAssembler();
  }

  @Test
  public void
      givenUneJourneeParSemaineParSession_whenAssembling_thenReturnOneDayPerWeekPerSession() {
    AccessPeriodInFrench accessPeriodInFrench =
        AccessPeriodInFrench.UNE_JOURNEE_PAR_SEMAINE_PAR_SESSION;

    AccessPeriod accessPeriod = accessPassPeriodAssembler.assemble(accessPeriodInFrench);

    assertThat(accessPeriod).isEqualTo(AccessPeriod.ONE_DAY_PER_WEEK_PER_SEMESTER);
  }

  @Test
  public void givenUneSession_whenAssembling_thenReturnOneSemester() {
    AccessPeriodInFrench accessPeriodInFrench = AccessPeriodInFrench.UNE_SESSION;

    AccessPeriod accessPeriod = accessPassPeriodAssembler.assemble(accessPeriodInFrench);

    assertThat(accessPeriod).isEqualTo(AccessPeriod.ONE_SEMESTER);
  }

  @Test
  public void givenDeuxSessions_whenAssembling_thenReturnTwoSemesters() {
    AccessPeriodInFrench accessPeriodInFrench = AccessPeriodInFrench.DEUX_SESSIONS;

    AccessPeriod accessPeriod = accessPassPeriodAssembler.assemble(accessPeriodInFrench);

    assertThat(accessPeriod).isEqualTo(AccessPeriod.TWO_SEMESTERS);
  }

  @Test
  public void givenTroisSessions_whenAssembling_thenReturnThreeSemesters() {
    AccessPeriodInFrench accessPeriodInFrench = AccessPeriodInFrench.TROIS_SESSIONS;

    AccessPeriod accessPeriod = accessPassPeriodAssembler.assemble(accessPeriodInFrench);

    assertThat(accessPeriod).isEqualTo(AccessPeriod.THREE_SEMESTERS);
  }
}
