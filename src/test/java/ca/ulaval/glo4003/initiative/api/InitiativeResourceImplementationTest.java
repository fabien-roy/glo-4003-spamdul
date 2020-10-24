package ca.ulaval.glo4003.initiative.api;

import static ca.ulaval.glo4003.initiative.helpers.InitiativeMother.createCode;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import ca.ulaval.glo4003.initiative.api.dto.AddInitiativeDto;
import ca.ulaval.glo4003.initiative.api.dto.InitiativeAddAllocatedAmountDto;
import ca.ulaval.glo4003.initiative.api.dto.InitiativeCodeDto;
import ca.ulaval.glo4003.initiative.api.dto.InitiativeDto;
import ca.ulaval.glo4003.initiative.domain.InitiativeCode;
import ca.ulaval.glo4003.initiative.helpers.AddInitiativeDtoBuilder;
import ca.ulaval.glo4003.initiative.helpers.InitiativeAddAllocatedAmountDtoBuilder;
import ca.ulaval.glo4003.initiative.helpers.InitiativeCodeDtoBuilder;
import ca.ulaval.glo4003.initiative.helpers.InitiativeDtoBuilder;
import ca.ulaval.glo4003.initiative.services.InitiativeService;
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
public class InitiativeResourceImplementationTest {
  @Mock private InitiativeService initiativeService;
  private InitiativeResource initiativeResource;

  private AddInitiativeDto addInitiativeDto = AddInitiativeDtoBuilder.aAddInitiativeDto().build();
  private InitiativeCodeDto initiativeCodeDto =
      InitiativeCodeDtoBuilder.aInitiativeCodeDto().build();
  private InitiativeDto initiativeDto = InitiativeDtoBuilder.aInitiativeDto().build();
  private InitiativeCode initiativeCode = createCode();
  private InitiativeAddAllocatedAmountDto initiativeAddAllocatedAmountDto =
      InitiativeAddAllocatedAmountDtoBuilder.aInitiativeAddAllocatedAmountDTO().build();
  private List<InitiativeDto> InitiativeDtoList = new ArrayList();

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
  public void whenGettingInitiative_thenResponseFoundStatus() {
    when(initiativeService.getInitiative(initiativeCode.toString())).thenReturn(initiativeDto);

    Response response = initiativeResource.getInitiative(initiativeCode.toString());

    Truth.assertThat(response.getStatus()).isEqualTo(Response.Status.FOUND.getStatusCode());
  }

  @Test
  public void whenGettingAllInitiative_thenGetAllInitiativeWithService() {
    when(initiativeService.getAllInitiatives()).thenReturn(InitiativeDtoList);

    initiativeResource.getAllInitiatives();

    verify(initiativeService).getAllInitiatives();
  }

  @Test
  public void whenGettingAllInitiative_thenResponseOkStatus() {
    when(initiativeService.getAllInitiatives()).thenReturn(InitiativeDtoList);

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
