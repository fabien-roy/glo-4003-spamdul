package ca.ulaval.glo4003.parkings.services;

import static ca.ulaval.glo4003.parkings.helpers.ParkingAreaBuilder.aParkingArea;
import static ca.ulaval.glo4003.parkings.helpers.ParkingAreaDtoBuilder.aParkingAreaDto;
import static org.mockito.Mockito.when;

import ca.ulaval.glo4003.parkings.api.dto.ParkingAreaDto;
import ca.ulaval.glo4003.parkings.assemblers.ParkingAreaAssembler;
import ca.ulaval.glo4003.parkings.domain.ParkingArea;
import ca.ulaval.glo4003.parkings.domain.ParkingAreaRepository;
import com.google.common.truth.Truth;
import java.util.ArrayList;
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
  }

  @Test
  public void whenGettingAllZone_thenReturnZones() {
    List<ParkingArea> parkingAreas = new ArrayList<>();
    parkingAreas.add(parkingArea);
    when(parkingAreaRepository.getAllArea()).thenReturn(parkingAreas);

    when(parkingAreaAssembler.assemble(parkingArea)).thenReturn(parkingAreaDto);
    List<ParkingAreaDto> parkingAreaDtoList = parkingAreaService.getParkingAreas();

    Truth.assertThat(parkingAreaDtoList).contains(parkingAreaDto);
  }
}
