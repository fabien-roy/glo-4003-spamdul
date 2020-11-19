package ca.ulaval.glo4003.gates;

import ca.ulaval.glo4003.accesspasses.services.AccessPassService;
import ca.ulaval.glo4003.gates.api.GateResource;
import com.google.common.truth.Truth;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class GateInjectorTest {

  @Mock AccessPassService accessPassService;

  private GateInjector gateInjector;

  @Before
  public void setUp() {
    gateInjector = new GateInjector();
  }

  @Test
  public void whenCreatingGateResource_thenReturnIt() {
    GateResource gateResource = gateInjector.createGateResource(accessPassService);

    Truth.assertThat(gateResource).isNotNull();
  }
}
