package ca.ulaval.glo4003.parkings.assemblers;

import static com.google.common.truth.Truth.assertThat;

import ca.ulaval.glo4003.parkings.domain.ParkingPeriod;
import ca.ulaval.glo4003.parkings.domain.ParkingPeriodInFrench;
import org.junit.Before;
import org.junit.Test;

public class ParkingPeriodAssemblerTest {
  private ParkingPeriodAssembler parkingPeriodAssembler;

  @Before
  public void setUp() {
    parkingPeriodAssembler = new ParkingPeriodAssembler();
  }

  @Test
  public void
      givenUneJourneeParSemaineParSesssion_whenAssembling_thenReturnOneDayPerWeekPerSession() {
    ParkingPeriodInFrench parkingPeriodInFrench =
        ParkingPeriodInFrench.UNE_JOURNEE_PAR_SEMAINE_PAR_SESSION;

    ParkingPeriod parkingPeriod = parkingPeriodAssembler.assemble(parkingPeriodInFrench);

    assertThat(parkingPeriod).isEqualTo(ParkingPeriod.ONE_DAY_PER_WEEK_PER_SEMESTER);
  }

  @Test
  public void givenMensuel_whenAssembling_thenReturnMonthly() {
    ParkingPeriodInFrench parkingPeriodInFrench = ParkingPeriodInFrench.MENSUEL;

    ParkingPeriod parkingPeriod = parkingPeriodAssembler.assemble(parkingPeriodInFrench);

    assertThat(parkingPeriod).isEqualTo(ParkingPeriod.MONTHLY);
  }

  @Test
  public void givenUneSession_whenAssembling_thenReturnOneSemester() {
    ParkingPeriodInFrench parkingPeriodInFrench = ParkingPeriodInFrench.UNE_SESSION;

    ParkingPeriod parkingPeriod = parkingPeriodAssembler.assemble(parkingPeriodInFrench);

    assertThat(parkingPeriod).isEqualTo(ParkingPeriod.ONE_SEMESTER);
  }

  @Test
  public void givenDeuxSessions_whenAssembling_thenReturnTwoSemesters() {
    ParkingPeriodInFrench parkingPeriodInFrench = ParkingPeriodInFrench.DEUX_SESSIONS;

    ParkingPeriod parkingPeriod = parkingPeriodAssembler.assemble(parkingPeriodInFrench);

    assertThat(parkingPeriod).isEqualTo(ParkingPeriod.TWO_SEMESTERS);
  }

  @Test
  public void givenTroisSessions_whenAssembling_thenReturnThreeSemesters() {
    ParkingPeriodInFrench parkingPeriodInFrench = ParkingPeriodInFrench.TROIS_SESSIONS;

    ParkingPeriod parkingPeriod = parkingPeriodAssembler.assemble(parkingPeriodInFrench);

    assertThat(parkingPeriod).isEqualTo(ParkingPeriod.THREE_SEMESTERS);
  }
}
