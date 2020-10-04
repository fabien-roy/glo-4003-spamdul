package ca.ulaval.glo4003.offenses.api;

import static ca.ulaval.glo4003.offenses.helpers.OffenseTypeDtoBuilder.anOffenseTypeDto;
import static org.mockito.Mockito.when;

import ca.ulaval.glo4003.offenses.api.dto.OffenseTypeDto;
import ca.ulaval.glo4003.offenses.services.OffenseTypeService;
import com.google.common.truth.Truth;
import java.util.Collections;
import java.util.List;
import javax.ws.rs.core.Response;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class OffenseTypeResourceImplementationTest {
  @Mock OffenseTypeService offenseTypeService;

  private OffenseTypeResource offenseResource;

  private final OffenseTypeDto offenseTypeDto = anOffenseTypeDto().build();
  private final List<OffenseTypeDto> offenseTypeDtos = Collections.singletonList(offenseTypeDto);

  @Before
  public void setUp() {
    offenseResource = new OffenseTypeResourceImplementation(offenseTypeService);

    when(offenseTypeService.getAllOffenseTypes()).thenReturn(offenseTypeDtos);
  }

  @Test
  public void whenGettingAllOffenses_thenRespondOkStatus() {
    Response response = offenseResource.getAllOffenses();

    Truth.assertThat(response.getStatus()).isEqualTo(Response.Status.OK.getStatusCode());
  }

  @Test
  public void whenGettingAllOffenses_thenRespondOffenseTypeDtos() {
    Response response = offenseResource.getAllOffenses();
    List<OffenseTypeDto> receivedOffenseTypeDtos = (List<OffenseTypeDto>) response.getEntity();

    Truth.assertThat(receivedOffenseTypeDtos).isSameInstanceAs(offenseTypeDtos);
  }
}
