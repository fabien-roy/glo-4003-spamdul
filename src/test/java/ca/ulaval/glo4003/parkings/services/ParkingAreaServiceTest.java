package ca.ulaval.glo4003.parkings.services;

import static ca.ulaval.glo4003.parkings.helpers.ParkingAreaBuilder.aParkingArea;
import static ca.ulaval.glo4003.parkings.helpers.ParkingAreaDtoBuilder.aParkingAreaDto;
import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.when;

import ca.ulaval.glo4003.parkings.api.dto.ParkingAreaDto;
import ca.ulaval.glo4003.parkings.assemblers.ParkingAreaAssembler;
import ca.ulaval.glo4003.parkings.domain.ParkingArea;
import ca.ulaval.glo4003.parkings.domain.ParkingAreaCode;
import ca.ulaval.glo4003.parkings.domain.ParkingAreaRepository;
import java.util.Collections;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ParkingAreaServiceTest {

  @Mock private ParkingAreaRepository parkingAreaRepository;
  @Mock private ParkingAreaAssembler parkingAreaAssembler;

  private ParkingAreaService parkingAreaService;
  private final ParkingArea parkingArea = aParkingArea().build();
  private final ParkingAreaDto parkingAreaDto = aParkingAreaDto().build();

  @Before
  public void setUp() {
    parkingAreaService = new ParkingAreaService(parkingAreaRepository, parkingAreaAssembler);

    List<ParkingArea> parkingAreas = Collections.singletonList(parkingArea);
    List<ParkingAreaDto> parkingAreaDtos = Collections.singletonList(parkingAreaDto);

    when(parkingAreaRepository.get(parkingArea.getCode())).thenReturn(parkingArea);
    when(parkingAreaRepository.getAll()).thenReturn(parkingAreas);
    when(parkingAreaAssembler.assembleMany(parkingAreas)).thenReturn(parkingAreaDtos);
  }

  @Test
  public void whenGettingParkingArea_thenReturnParkingArea() {
    ParkingArea receivedParkingArea = parkingAreaService.get(parkingArea.getCode());

    assertThat(receivedParkingArea).isSameInstanceAs(parkingArea);
  }

  @Test
  public void whenGettingAllParkingAreas_thenReturnAllParkingAreaDtos() {
    List<ParkingAreaDto> parkingAreaDtos = parkingAreaService.getParkingAreas();

    assertThat(parkingAreaDtos).hasSize(1);
    assertThat(parkingAreaDtos.get(0)).isSameInstanceAs(parkingAreaDto);
  }

  @Test
  public void whenGettingAllParkingAreaCodes_thenReturnAllParkingAreaCodes() {
    List<ParkingAreaCode> parkingAreaCodes = parkingAreaService.getParkingAreaCodes();

    assertThat(parkingAreaCodes).hasSize(1);
    assertThat(parkingAreaCodes.get(0)).isSameInstanceAs(parkingArea.getCode());
  }
}
