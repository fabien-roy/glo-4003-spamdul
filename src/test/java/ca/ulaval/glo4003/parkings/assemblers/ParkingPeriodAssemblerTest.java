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
  public void givenUneJournee_whenAssembling_thenReturnOneDay() {
    ParkingPeriodInFrench parkingPeriodInFrench = ParkingPeriodInFrench.UNE_JOURNEE;

    ParkingPeriod parkingPeriod = parkingPeriodAssembler.assemble(parkingPeriodInFrench);

    assertThat(parkingPeriod).isEqualTo(ParkingPeriod.ONE_DAY);
  }

  @Test
  public void givenMensuel_whenAssembling_thenReturnMonthly() {
    ParkingPeriodInFrench parkingPeriodInFrench = ParkingPeriodInFrench.MENSUEL;

    ParkingPeriod parkingPeriod = parkingPeriodAssembler.assemble(parkingPeriodInFrench);

    assertThat(parkingPeriod).isEqualTo(ParkingPeriod.MONTHLY);
  }

  @Test
  public void givenUneSession_whenAssembling_thenReturnOneSession() {
    ParkingPeriodInFrench parkingPeriodInFrench = ParkingPeriodInFrench.UNE_SESSION;

    ParkingPeriod parkingPeriod = parkingPeriodAssembler.assemble(parkingPeriodInFrench);

    assertThat(parkingPeriod).isEqualTo(ParkingPeriod.ONE_SESSION);
  }

  @Test
  public void givenDeuxSession_whenAssembling_thenReturnTwoSession() {
    ParkingPeriodInFrench parkingPeriodInFrench = ParkingPeriodInFrench.DEUX_SESSIONS;

    ParkingPeriod parkingPeriod = parkingPeriodAssembler.assemble(parkingPeriodInFrench);

    assertThat(parkingPeriod).isEqualTo(ParkingPeriod.TWO_SESSIONS);
  }

  @Test
  public void givenTroisSession_whenAssembling_thenReturnThreeSession() {
    ParkingPeriodInFrench parkingPeriodInFrench = ParkingPeriodInFrench.TROIS_SESSIONS;

    ParkingPeriod parkingPeriod = parkingPeriodAssembler.assemble(parkingPeriodInFrench);

    assertThat(parkingPeriod).isEqualTo(ParkingPeriod.THREE_SESSIONS);
  }
}
