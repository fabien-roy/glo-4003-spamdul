package ca.ulaval.glo4003.parkings.assemblers;

import static ca.ulaval.glo4003.gates.api.helpers.AccessStatusMother.createAccessStatus;

import ca.ulaval.glo4003.gates.api.dto.AccessStatusDto;
import ca.ulaval.glo4003.parkings.domain.AccessStatus;
import com.google.common.truth.Truth;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AccessStatusAssemblerTest {

  private static final AccessStatus accessStatus = createAccessStatus();

  private AccessStatusAssembler accessStatusAssembler;

  @Before
  public void setUp() {
    accessStatusAssembler = new AccessStatusAssembler();
  }

  @Test
  public void whenAssembling_thenReturnAccessStatusDto() {
    AccessStatusDto accessStatusDto = accessStatusAssembler.assemble(accessStatus);

    Truth.assertThat(accessStatusDto.accessStatus).isEqualTo(accessStatus.toString());
  }
}
