package ca.ulaval.glo4003.profits.api;

import static ca.ulaval.glo4003.times.helpers.CustomDateMother.createFutureDate;
import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.verify;

import ca.ulaval.glo4003.profits.services.ProfitsService;
import javax.ws.rs.core.Response;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ProfitsResourceImplementationTest {

  private int A_YEAR = createFutureDate().toLocalDate().getYear();

  private ProfitsResourceImplementation profitsResourceImplementation;

  @Mock private ProfitsService profitsService;

  @Before
  public void setup() {
    profitsResourceImplementation = new ProfitsResourceImplementation(profitsService);
  }

  @Test
  public void whenGettingProfitsFromParkingSticker_shouldCallProfitsService() {
    profitsResourceImplementation.getParkingStickerProfits(A_YEAR);

    verify(profitsService).getParkingStickerProfits(A_YEAR);
  }

  @Test
  public void whenGettingProfitsFromParkingSticker_shouldResponseWithOkStatus() {
    Response response = profitsResourceImplementation.getParkingStickerProfits(A_YEAR);

    assertThat(response.getStatus()).isEqualTo(Response.Status.OK.getStatusCode());
  }

  @Test
  public void whenGettingProfitsFromAccessPass_ifAskedByConsumptionType_shouldCallProfitsService() {
    boolean isByConsumptionType = true;
    profitsResourceImplementation.getAccessPassProfits(A_YEAR, isByConsumptionType);

    verify(profitsService).getAccessPassProfitsByConsumptionType(A_YEAR);
  }

  @Test
  public void
      whenGettingProfitsFromAccessPass_ifNotAskedByConsumptionType_shouldCallProfitsService() {
    boolean isByConsumptionType = false;
    profitsResourceImplementation.getAccessPassProfits(A_YEAR, isByConsumptionType);

    verify(profitsService).getAccessPassProfits(A_YEAR);
  }

  @Test
  public void whenGettingProfitsFromAccessPass_shouldResponseWithOkStatus() {
    boolean isByConsumptionType = false;
    Response response =
        profitsResourceImplementation.getAccessPassProfits(A_YEAR, isByConsumptionType);

    assertThat(response.getStatus()).isEqualTo(Response.Status.OK.getStatusCode());
  }

  @Test
  public void whenGettingProfitsFromOffenses_shoudlCallProfitsService() {
    profitsResourceImplementation.getOffenseProfits(A_YEAR);

    verify(profitsService).getOffenseProfits(A_YEAR);
  }

  @Test
  public void whenGettingProfitsFromOffenses_shouldResponseWithOkStatus() {
    Response response = profitsResourceImplementation.getOffenseProfits(A_YEAR);

    assertThat(response.getStatus()).isEqualTo(Response.Status.OK.getStatusCode());
  }
}
