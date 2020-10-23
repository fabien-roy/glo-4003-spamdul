package ca.ulaval.glo4003.parkings.assemblers;

import static ca.ulaval.glo4003.parkings.helpers.ParkingAreaBuilder.aParkingArea;
import static com.google.common.truth.Truth.assertThat;

import ca.ulaval.glo4003.parkings.api.dto.ParkingAreaDto;
import ca.ulaval.glo4003.parkings.domain.ParkingArea;
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

    assertThat(parkingAreaDto.parkingArea).isEqualTo(parkingAreaDto.parkingArea);
    assertThat(parkingAreaDto.parkingPeriodPrice).isNotEmpty();
  }
}
