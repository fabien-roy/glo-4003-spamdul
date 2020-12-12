package ca.ulaval.glo4003.carboncredits;

import static com.google.common.truth.Truth.assertThat;

import ca.ulaval.glo4003.carboncredits.api.CarbonCreditResource;
import ca.ulaval.glo4003.initiatives.domain.InitiativeRepository;
import ca.ulaval.glo4003.initiatives.services.InitiativeService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CarbonCreditInjectorTest {
  @Mock private InitiativeService initiativeService;
  @Mock private InitiativeRepository initiativeRepository;

  private CarbonCreditInjector carbonCreditInjector;

  @Before
  public void setUp() {
    carbonCreditInjector = new CarbonCreditInjector();
  }

  @Test
  public void whenCreatingCarbonCreditResource_thenReturnIt() {
    CarbonCreditResource carbonCreditResource =
        carbonCreditInjector.createCarbonCreditResource(initiativeService, initiativeRepository);

    assertThat(carbonCreditResource).isNotNull();
  }
}
