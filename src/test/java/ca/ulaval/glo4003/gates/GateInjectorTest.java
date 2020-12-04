package ca.ulaval.glo4003.gates;

import static com.google.common.truth.Truth.assertThat;

import ca.ulaval.glo4003.accesspasses.services.AccessPassService;
import ca.ulaval.glo4003.gates.api.GateResource;
import ca.ulaval.glo4003.reports.services.ReportEventService;
import ca.ulaval.glo4003.times.services.converters.CustomDateTimeConverter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class GateInjectorTest {

  @Mock private AccessPassService accessPassService;
  @Mock private CustomDateTimeConverter customDateTimeConverter;
  @Mock private ReportEventService reportEventService;

  private GateInjector gateInjector;

  @Before
  public void setUp() {
    gateInjector = new GateInjector();
  }

  @Test
  public void whenCreatingGateResource_thenReturnIt() {
    GateResource gateResource =
        gateInjector.createGateResource(
            accessPassService, customDateTimeConverter, reportEventService);

    assertThat(gateResource).isNotNull();
  }
}
