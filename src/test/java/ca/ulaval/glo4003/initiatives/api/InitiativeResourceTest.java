package ca.ulaval.glo4003.initiatives.api;

import static ca.ulaval.glo4003.initiatives.helpers.AddInitiativeDtoBuilder.anAddInitiativeDto;
import static ca.ulaval.glo4003.initiatives.helpers.InitiativeAddAllocatedAmountDtoBuilder.aInitiativeAddAllocatedAmountDTO;
import static ca.ulaval.glo4003.initiatives.helpers.InitiativeCodeDtoBuilder.aInitiativeCodeDto;
import static ca.ulaval.glo4003.initiatives.helpers.InitiativeDtoBuilder.anInitiativeDto;
import static ca.ulaval.glo4003.initiatives.helpers.InitiativeMother.createInitiativeCode;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import ca.ulaval.glo4003.initiatives.api.dto.AddInitiativeDto;
import ca.ulaval.glo4003.initiatives.api.dto.InitiativeAddAllocatedAmountDto;
import ca.ulaval.glo4003.initiatives.api.dto.InitiativeCodeDto;
import ca.ulaval.glo4003.initiatives.api.dto.InitiativeDto;
import ca.ulaval.glo4003.initiatives.domain.InitiativeCode;
import ca.ulaval.glo4003.initiatives.services.InitiativeService;
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
public class InitiativeResourceImplementationTest {
  @Mock private InitiativeService initiativeService;

  private InitiativeResource initiativeResource;

  private final AddInitiativeDto addInitiativeDto = anAddInitiativeDto().build();
  private final InitiativeCodeDto initiativeCodeDto = aInitiativeCodeDto().build();
  private final InitiativeDto initiativeDto = anInitiativeDto().build();
  private final InitiativeCode initiativeCode = createInitiativeCode();
  private final InitiativeAddAllocatedAmountDto initiativeAddAllocatedAmountDto =
      aInitiativeAddAllocatedAmountDTO().build();
  private final List<InitiativeDto> initiativeDtoList = Collections.singletonList(initiativeDto);

  @Before
  public void setUp() {
    initiativeResource = new InitiativeResourceImplementation(initiativeService);
  }

  @Test
  public void whenAddingInitiative_thenAddInitiativeWithService() {
    when(initiativeService.addInitiative(addInitiativeDto)).thenReturn(initiativeCodeDto);

    Response response = initiativeResource.addInitiative(addInitiativeDto);
    InitiativeCodeDto respondedInitiativeCodeDto = (InitiativeCodeDto) response.getEntity();

    Truth.assertThat(respondedInitiativeCodeDto.initiativeCode)
        .isEqualTo(initiativeCodeDto.initiativeCode);
  }

  @Test
  public void whenAddingInitiative_thenResponseCreatedStatus() {
    when(initiativeService.addInitiative(addInitiativeDto)).thenReturn(initiativeCodeDto);

    Response response = initiativeResource.addInitiative(addInitiativeDto);

    Truth.assertThat(response.getStatus()).isEqualTo(Response.Status.CREATED.getStatusCode());
  }

  @Test
  public void whenGettingInitiative_thenGetInitiativeWithService() {
    when(initiativeService.getInitiative(initiativeCode.toString())).thenReturn(initiativeDto);

    Response response = initiativeResource.getInitiative(initiativeCode.toString());
    InitiativeDto respondedInitiativeDto = (InitiativeDto) response.getEntity();

    Truth.assertThat(respondedInitiativeDto.code).isEqualTo(initiativeDto.code);
    Truth.assertThat(respondedInitiativeDto.name).isEqualTo(initiativeDto.name);
    Truth.assertThat(respondedInitiativeDto.allocatedAmount)
        .isEqualTo(initiativeDto.allocatedAmount);
  }

  @Test
  public void whenGettingInitiative_thenResponseOkStatus() {
    when(initiativeService.getInitiative(initiativeCode.toString())).thenReturn(initiativeDto);

    Response response = initiativeResource.getInitiative(initiativeCode.toString());

    Truth.assertThat(response.getStatus()).isEqualTo(Response.Status.OK.getStatusCode());
  }

  @Test
  public void whenGettingAllInitiative_thenGetAllInitiativeWithService() {
    when(initiativeService.getAllInitiatives()).thenReturn(initiativeDtoList);

    initiativeResource.getAllInitiatives();

    verify(initiativeService).getAllInitiatives();
  }

  @Test
  public void whenGettingAllInitiative_thenResponseOkStatus() {
    when(initiativeService.getAllInitiatives()).thenReturn(initiativeDtoList);

    Response response = initiativeResource.getAllInitiatives();

    Truth.assertThat(response.getStatus()).isEqualTo(Response.Status.OK.getStatusCode());
  }

  @Test
  public void whenAddingAmountToInitiative_thenAddAmountToInitiativeWithService() {
    initiativeResource.allocateAmountToInitiative(
        initiativeCode.toString(), initiativeAddAllocatedAmountDto);

    verify(initiativeService)
        .addAllocatedAmountToInitiative(initiativeCode.toString(), initiativeAddAllocatedAmountDto);
  }

  @Test
  public void whenAddingAmountToInitiative_thenResponseOkStatus() {
    Response response =
        initiativeResource.allocateAmountToInitiative(
            initiativeCode.toString(), initiativeAddAllocatedAmountDto);

    Truth.assertThat(response.getStatus()).isEqualTo(Response.Status.OK.getStatusCode());
  }
}
