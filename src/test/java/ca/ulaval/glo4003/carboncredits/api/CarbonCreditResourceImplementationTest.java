package ca.ulaval.glo4003.carboncredits.api;

import static org.mockito.Mockito.when;

import ca.ulaval.glo4003.carboncredits.domain.CarbonCredit;
import ca.ulaval.glo4003.carboncredits.services.CarbonCreditsService;
import com.google.common.truth.Truth;
import javax.ws.rs.core.Response;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CarbonCreditResourceImplementationTest {
  private final Double CARBON_CREDITS = new CarbonCredit(20).toDouble();
  @Mock private CarbonCreditsService carbonCreditsService;

  private CarbonCreditResource carbonCreditResource;

  @Before
  public void setUp() {
    carbonCreditResource = new CarbonCreditResourceImplementation(carbonCreditsService);

    when(carbonCreditsService.getCarbonCredits()).thenReturn(CARBON_CREDITS);
  }

  @Test
  public void whenGettingCarbonCredits_thenRespondOkStatus() {
    Response response = carbonCreditResource.getCarbonCredits();

    Truth.assertThat(response.getStatus()).isEqualTo(Response.Status.OK.getStatusCode());
  }

  @Test
  public void whenGettingCarbonCredits_thenRespondCarbonCredits() {
    Response response = carbonCreditResource.getCarbonCredits();

    Truth.assertThat(response.getEntity()).isEqualTo(CARBON_CREDITS);
  }
}
