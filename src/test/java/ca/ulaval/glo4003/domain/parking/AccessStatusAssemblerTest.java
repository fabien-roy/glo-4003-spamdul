package ca.ulaval.glo4003.domain.parking;

import ca.ulaval.glo4003.api.parking.dto.AccessStatusDto;
import com.google.common.truth.Truth;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AccessStatusAssemblerTest {

  private AccessStatusAssembler accessStatusAssembler;

  @Before
  public void setUp() {
    accessStatusAssembler = new AccessStatusAssembler();
  }

  @Test
  public void whenAssembling_thenReturnAccessStatusDTO() {
    AccessStatusDto accessStatusDto = accessStatusAssembler.assemble("Access granted");

    Truth.assertThat(accessStatusDto.accessStatus).isEqualTo("Access granted");
  }
}
