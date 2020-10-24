package ca.ulaval.glo4003.carboncredits;

import ca.ulaval.glo4003.carboncredits.api.CarbonCreditResource;
import com.google.common.truth.Truth;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CarbonCreditInjectorTest {
  private CarbonCreditInjector carbonCreditInjector;

  @Before
  public void setUp() {
    carbonCreditInjector = new CarbonCreditInjector();
  }

  @Test
  public void whenCreatingCarbonCreditResource_thenReturnIt() {
    CarbonCreditResource carbonCreditResource = carbonCreditInjector.createCarbonCreditResource();

    Truth.assertThat(carbonCreditResource).isNotNull();
  }
}
