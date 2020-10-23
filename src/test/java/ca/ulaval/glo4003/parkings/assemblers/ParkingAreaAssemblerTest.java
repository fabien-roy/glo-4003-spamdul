package ca.ulaval.glo4003.parkings.assemblers;

import static ca.ulaval.glo4003.parkings.helpers.ParkingAreaBuilder.aParkingArea;
import static com.google.common.truth.Truth.assertThat;

import ca.ulaval.glo4003.parkings.api.dto.ParkingAreaDto;
import ca.ulaval.glo4003.parkings.domain.ParkingArea;
import java.util.ArrayList;
import java.util.List;
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
    List<ParkingArea> parkingAreas = new ArrayList<>();
    parkingAreas.add(parkingArea);

    List<ParkingAreaDto> parkingAreaDto = parkingAreaAssembler.assembleMany(parkingAreas);

    assertThat(parkingAreaDto.get(0).parkingArea).isEqualTo(parkingArea.getCode().toString());
    assertThat(parkingAreaDto.get(0).parkingPeriodPrice).isNotEmpty();
  }
}
