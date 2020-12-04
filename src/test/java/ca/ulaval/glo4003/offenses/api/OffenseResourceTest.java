package ca.ulaval.glo4003.offenses.api;

import static ca.ulaval.glo4003.offenses.helpers.OffenseTypeDtoBuilder.anOffenseTypeDto;
import static ca.ulaval.glo4003.offenses.helpers.OffenseValidationDtoBuilder.anOffenseValidationDto;
import static org.mockito.Mockito.when;

import ca.ulaval.glo4003.offenses.services.OffenseTypeService;
import ca.ulaval.glo4003.offenses.services.dto.OffenseTypeDto;
import ca.ulaval.glo4003.offenses.services.dto.OffenseValidationDto;
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
public class OffenseResourceTest {
  @Mock OffenseTypeService offenseTypeService;

  private OffenseResource offenseResource;

  private final OffenseValidationDto offenseValidationDto = anOffenseValidationDto().build();
  private final OffenseTypeDto offenseTypeDto = anOffenseTypeDto().build();
  private final List<OffenseTypeDto> offenseTypeDtos = Collections.singletonList(offenseTypeDto);

  @Before
  public void setUp() {
    offenseResource = new OffenseResource(offenseTypeService);

    when(offenseTypeService.getAllOffenseTypes()).thenReturn(offenseTypeDtos);
    when(offenseTypeService.validateOffense(offenseValidationDto)).thenReturn(offenseTypeDtos);
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

  @Test
  public void whenValidatingOffense_thenRespondOkStatus() {
    Response response = offenseResource.validateOffense(offenseValidationDto);

    Truth.assertThat(response.getStatus()).isEqualTo(Response.Status.OK.getStatusCode());
  }

  @Test
  public void whenValidatingOffense_thenRespondOffenseTypeDto() {
    Response response = offenseResource.validateOffense(offenseValidationDto);
    List<OffenseTypeDto> receivedOffenseTypeDtos = (List<OffenseTypeDto>) response.getEntity();

    Truth.assertThat(receivedOffenseTypeDtos).isSameInstanceAs(offenseTypeDtos);
  }
}
