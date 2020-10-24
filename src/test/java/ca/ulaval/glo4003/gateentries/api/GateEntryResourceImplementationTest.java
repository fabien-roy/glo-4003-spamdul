package ca.ulaval.glo4003.gateentries.api;

import static ca.ulaval.glo4003.accesspasses.helpers.AccessPassMother.createAccessPassCode;
import static ca.ulaval.glo4003.gateentries.api.helpers.AccessStatusDtoBuilder.anAccessStatusDto;
import static ca.ulaval.glo4003.gateentries.api.helpers.DayOfWeekDtoBuilder.aDayOfWeekDto;
import static org.mockito.Mockito.when;

import ca.ulaval.glo4003.gateentries.api.dto.AccessStatusDto;
import ca.ulaval.glo4003.gateentries.api.dto.DayOfWeekDto;
import ca.ulaval.glo4003.gateentries.services.GateEntryService;
import ca.ulaval.glo4003.parkings.domain.AccessStatus;
import com.google.common.truth.Truth;
import javax.ws.rs.core.Response;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class GateEntryResourceImplementationTest {
  @Mock GateEntryService gateEntryService;

  private GateEntryResource gateEntryResource;

  private DayOfWeekDto dayOfWeekDto = aDayOfWeekDto().build();
  private String accessPassCode = createAccessPassCode().toString();
  private AccessStatusDto accessStatusDto = anAccessStatusDto().build();

  @Before
  public void setUp() {
    gateEntryResource = new GateEntryResourceImplementation(gateEntryService);

    when(gateEntryService.validateAccessPass(dayOfWeekDto, accessPassCode))
        .thenReturn(accessStatusDto);
  }

  @Test
  public void whenValidatingAccessPass_thenValidateAccessPassWithService() {
    Response response = gateEntryResource.validateAccessPass(dayOfWeekDto, accessPassCode);
    AccessStatusDto receivedAccessStatusDto = (AccessStatusDto) response.getEntity();

    Truth.assertThat(receivedAccessStatusDto.accessStatus).isEqualTo(accessStatusDto.accessStatus);
  }

  @Test
  public void givenValidAccessPass_whenValidatingAccessPass_thenRespondWithAcceptedStatus() {
    accessStatusDto =
        anAccessStatusDto().withAccessStatus(AccessStatus.ACCESS_GRANTED.toString()).build();
    when(gateEntryService.validateAccessPass(dayOfWeekDto, accessPassCode))
        .thenReturn(accessStatusDto);

    Response response = gateEntryResource.validateAccessPass(dayOfWeekDto, accessPassCode);

    Truth.assertThat(response.getStatus()).isEqualTo(Response.Status.ACCEPTED.getStatusCode());
  }

  @Test
  public void givenInvalidAccessPass_whenValidatingAccessPass_thenRespondWithForbiddenStatus() {
    accessStatusDto =
        anAccessStatusDto().withAccessStatus(AccessStatus.ACCESS_REFUSED.toString()).build();
    when(gateEntryService.validateAccessPass(dayOfWeekDto, accessPassCode))
        .thenReturn(accessStatusDto);

    Response response = gateEntryResource.validateAccessPass(dayOfWeekDto, accessPassCode);

    Truth.assertThat(response.getStatus()).isEqualTo(Response.Status.FORBIDDEN.getStatusCode());
  }
}
