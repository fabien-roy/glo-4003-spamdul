package ca.ulaval.glo4003.carboncredits.api;

import static org.mockito.Mockito.when;

import ca.ulaval.glo4003.carboncredits.api.dto.CarbonCreditDto;
import ca.ulaval.glo4003.carboncredits.api.dto.MonthlyPaymentStatusDto;
import ca.ulaval.glo4003.carboncredits.services.CarbonCreditService;
import com.google.common.truth.Truth;
import javax.ws.rs.core.Response;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CarbonCreditResourceImplementationTest {
  @Mock private CarbonCreditService carbonCreditService;
  private final CarbonCreditDto carbonCreditDto = new CarbonCreditDto();
  private final MonthlyPaymentStatusDto monthlyPaymentStatusDto = new MonthlyPaymentStatusDto();

  private CarbonCreditResource carbonCreditResource;

  @Before
  public void setUp() {
    carbonCreditResource = new CarbonCreditResourceImplementation(carbonCreditService);

    when(carbonCreditService.getCarbonCredits()).thenReturn(carbonCreditDto);
  }

  @Test
  public void
      givenCarbonCreditMonthlyPaymentStatusDto_whenModifyingCarbonCreditMonthlyPaymentStatus_thenMonthlyPaymentStatusIsChanged() {
    Response response =
        carbonCreditResource.modifyCarbonCreditMonthlyPaymentStatus(monthlyPaymentStatusDto);

    Truth.assertThat(response.getStatus()).isEqualTo(Response.Status.OK.getStatusCode());
  }

  @Test
  public void whenGettingCarbonCredits_thenRespondOkStatus() {
    Response response = carbonCreditResource.getCarbonCredits();

    Truth.assertThat(response.getStatus()).isEqualTo(Response.Status.OK.getStatusCode());
  }

  @Test
  public void whenGettingCarbonCredits_thenRespondCarbonCredits() {
    Response response = carbonCreditResource.getCarbonCredits();

    Truth.assertThat(response.getEntity()).isEqualTo(carbonCreditDto);
  }
}
