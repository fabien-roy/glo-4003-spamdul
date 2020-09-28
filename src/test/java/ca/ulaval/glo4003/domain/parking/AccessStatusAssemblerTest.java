package ca.ulaval.glo4003.domain.parking;

import static ca.ulaval.glo4003.domain.parking.AccessStatus.ACCESS_GRANTED;

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
    AccessStatusDto accessStatusDto = accessStatusAssembler.assemble(ACCESS_GRANTED.toString());

    Truth.assertThat(accessStatusDto.accessStatus).isEqualTo(ACCESS_GRANTED.toString());
  }
}
