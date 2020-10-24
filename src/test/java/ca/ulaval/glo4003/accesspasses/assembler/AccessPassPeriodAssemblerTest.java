package ca.ulaval.glo4003.accesspasses.assembler;

import static com.google.common.truth.Truth.assertThat;

import ca.ulaval.glo4003.accesspasses.domain.AccessFrenchPeriod;
import ca.ulaval.glo4003.accesspasses.domain.AccessPeriod;
import org.junit.Before;
import org.junit.Test;

public class AccessPassPeriodAssemblerTest {
  private AccessPassPeriodAssembler accessPassPeriodAssembler;

  @Before
  public void setUp() {
    accessPassPeriodAssembler = new AccessPassPeriodAssembler();
  }

  @Test
  public void givenUneHeure_whenAssembling_thenReturnOneHour() {
    AccessFrenchPeriod accessFrenchPeriod = AccessFrenchPeriod.UNE_HEURE;

    AccessPeriod accessPeriod = accessPassPeriodAssembler.assemble(accessFrenchPeriod);

    assertThat(accessPeriod).isEqualTo(AccessPeriod.ONE_HOUR);
  }

  @Test
  public void givenUneJournee_whenAssembling_thenReturnOneDay() {
    AccessFrenchPeriod accessFrenchPeriod = AccessFrenchPeriod.UNE_JOURNEE;

    AccessPeriod accessPeriod = accessPassPeriodAssembler.assemble(accessFrenchPeriod);

    assertThat(accessPeriod).isEqualTo(AccessPeriod.ONE_DAY);
  }

  @Test
  public void
      givenUneJourneeParSemainePourSession_whenAssembling_thenReturnOneDayByWeekForSession() {
    AccessFrenchPeriod accessFrenchPeriod = AccessFrenchPeriod.UNE_JOURNEE_PAR_SEMAINE_POUR_SESSION;

    AccessPeriod accessPeriod = accessPassPeriodAssembler.assemble(accessFrenchPeriod);

    assertThat(accessPeriod).isEqualTo(AccessPeriod.ONE_DAY_BY_WEEK_FOR_SESSION);
  }

  @Test
  public void givenUneSession_whenAssembling_thenReturnOneSession() {
    AccessFrenchPeriod accessFrenchPeriod = AccessFrenchPeriod.UNE_SESSION;

    AccessPeriod accessPeriod = accessPassPeriodAssembler.assemble(accessFrenchPeriod);

    assertThat(accessPeriod).isEqualTo(AccessPeriod.ONE_SESSION);
  }

  @Test
  public void givenDeuxSessions_whenAssembling_thenReturnTwoSessions() {
    AccessFrenchPeriod accessFrenchPeriod = AccessFrenchPeriod.DEUX_SESSIONS;

    AccessPeriod accessPeriod = accessPassPeriodAssembler.assemble(accessFrenchPeriod);

    assertThat(accessPeriod).isEqualTo(AccessPeriod.TWO_SESSIONS);
  }

  @Test
  public void givenTroisSessions_whenAssembling_thenReturnThreeSessions() {
    AccessFrenchPeriod accessFrenchPeriod = AccessFrenchPeriod.TROIS_SESSIONS;

    AccessPeriod accessPeriod = accessPassPeriodAssembler.assemble(accessFrenchPeriod);

    assertThat(accessPeriod).isEqualTo(AccessPeriod.THREE_SESSIONS);
  }
}
