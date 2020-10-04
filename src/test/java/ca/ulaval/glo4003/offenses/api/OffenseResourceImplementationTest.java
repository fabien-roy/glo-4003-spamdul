package ca.ulaval.glo4003.offenses.api;

import static ca.ulaval.glo4003.offenses.helpers.OffenseTypeDtoBuilder.anOffenseTypeDto;
import static ca.ulaval.glo4003.offenses.helpers.OffenseValidationDtoBuilder.anOffenseValidationDto;
import static org.mockito.Mockito.when;

import ca.ulaval.glo4003.offenses.api.dto.OffenseTypeDto;
import ca.ulaval.glo4003.offenses.api.dto.OffenseValidationDto;
import ca.ulaval.glo4003.offenses.services.OffenseTypeService;
import com.google.common.truth.Truth;
import javax.ws.rs.core.Response;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class OffenseResourceImplementationTest {
  @Mock OffenseTypeService offenseTypeService;

  private OffenseResource offenseResource;

  private final OffenseValidationDto offenseValidationDto = anOffenseValidationDto().build();
  private final OffenseTypeDto offenseTypeDto = anOffenseTypeDto().build();

  @Before
  public void setUp() {
    offenseResource = new OffenseResourceImplementation(offenseTypeService);

    when(offenseTypeService.validateOffense(offenseValidationDto)).thenReturn(offenseTypeDto);
  }

  @Test
  public void whenValidatingOffense_thenRespondOkStatus() {
    Response response = offenseResource.validateOffense(offenseValidationDto);

    Truth.assertThat(response.getStatus()).isEqualTo(Response.Status.OK.getStatusCode());
  }

  @Test
  public void whenValidatingOffense_thenRespondOffenseTypeDto() {
    Response response = offenseResource.validateOffense(offenseValidationDto);
    OffenseTypeDto receivedOffenseTypeDto = (OffenseTypeDto) response.getEntity();

    Truth.assertThat(receivedOffenseTypeDto).isSameInstanceAs(offenseTypeDto);
  }
}
