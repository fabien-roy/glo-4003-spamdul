package ca.ulaval.glo4003.gateentries;

import ca.ulaval.glo4003.access.services.AccessPassService;
import ca.ulaval.glo4003.gateentries.api.GateEntryResource;
import com.google.common.truth.Truth;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class GateEntryInjectorTest {

  @Mock AccessPassService accessPassService;

  private GateEntryInjector gateEntryInjector;

  @Before
  public void setUp() {
    gateEntryInjector = new GateEntryInjector();
  }

  @Test
  public void whenCreatingGateEntryResource_thenReturnIt() {
    GateEntryResource gateEntryResource =
        gateEntryInjector.createGateEntryResource(accessPassService);

    Truth.assertThat(gateEntryResource).isNotNull();
  }
}
