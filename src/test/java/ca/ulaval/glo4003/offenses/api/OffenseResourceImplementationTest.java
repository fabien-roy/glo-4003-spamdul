package ca.ulaval.glo4003.offenses.api;

import static org.mockito.Mockito.when;

import ca.ulaval.glo4003.offenses.api.OffenseResource;
import ca.ulaval.glo4003.offenses.api.OffenseResourceImplementation;
import ca.ulaval.glo4003.offenses.api.dto.OffenseDto;
import ca.ulaval.glo4003.offenses.api.dto.OffenseValidationDto;
import ca.ulaval.glo4003.offenses.services.OffenseService;
import com.google.common.truth.Truth;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.Response;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class OffenseResourceImplementationTest {
  @Mock OffenseDto offenseDto;
  @Mock OffenseService offenseService;
  @Mock OffenseValidationDto offenseValidationDto;
  private List<OffenseDto> offenseDtoList;

  private OffenseResource offenseResource;

  @Before
  public void setUp() {
    offenseResource = new OffenseResourceImplementation(offenseService);
    offenseDtoList = new ArrayList<>();
    offenseDtoList.add(offenseDto);
  }

  @Test
  public void whenGettingAllOffenses_thenResponseOkStatus() {
    when(offenseService.getAllOffenses()).thenReturn(offenseDtoList);

    Response response = offenseResource.getAllOffenses();

    Truth.assertThat(response.getStatus()).isEqualTo(Response.Status.OK.getStatusCode());
  }

  @Test
  public void whenValidatingOffense_thenResponseOkStatus() {
    when(offenseService.validateOffense(offenseValidationDto)).thenReturn(offenseDto);

    Response response = offenseResource.validateOffense(offenseValidationDto);

    Truth.assertThat(response.getStatus()).isEqualTo(Response.Status.OK.getStatusCode());
  }
}
