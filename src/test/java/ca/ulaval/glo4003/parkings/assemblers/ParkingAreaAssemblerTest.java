package ca.ulaval.glo4003.parkings.assemblers;

import static ca.ulaval.glo4003.parkings.helpers.ParkingAreaBuilder.aParkingArea;

import ca.ulaval.glo4003.parkings.api.dto.ParkingAreaDto;
import ca.ulaval.glo4003.parkings.domain.ParkingArea;
import com.google.common.truth.Truth;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ParkingAreaAssemblerTest {

  private ParkingAreaAssembler parkingAreaAssembler;
  private ParkingArea parkingArea = aParkingArea().build();

  @Before
  public void setUp() {
    parkingAreaAssembler = new ParkingAreaAssembler();
  }

  @Test
  public void whenAssembling_thenReturnParkingAreaDto() {
    ParkingAreaDto parkingAreaDto = parkingAreaAssembler.assemble(parkingArea);

    Truth.assertThat(parkingAreaDto.parkingArea).isEqualTo(parkingAreaDto.parkingArea);
    Truth.assertThat(parkingAreaDto.parkingPeriodPrice).isNotEmpty();
  }
}
