package ca.ulaval.glo4003.carboncredits;

import ca.ulaval.glo4003.carboncredits.api.CarbonCreditResource;
import ca.ulaval.glo4003.funds.domain.SustainableMobilityProgramBankRepository;
import ca.ulaval.glo4003.initiatives.services.InitiativeService;
import com.google.common.truth.Truth;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CarbonCreditInjectorTest {
  private CarbonCreditInjector carbonCreditInjector;
  @Mock private InitiativeService initiativeService;
  @Mock private SustainableMobilityProgramBankRepository sustainableMobilityProgramBankRepository;

  @Before
  public void setUp() {
    carbonCreditInjector = new CarbonCreditInjector();
  }

  @Test
  public void whenCreatingCarbonCreditResource_thenReturnIt() {
    CarbonCreditResource carbonCreditResource =
        carbonCreditInjector.createCarbonCreditResource(
            initiativeService, sustainableMobilityProgramBankRepository);

    Truth.assertThat(carbonCreditResource).isNotNull();
  }
}
