package ca.ulaval.glo4003.parkings.services;

import static ca.ulaval.glo4003.parkings.helpers.ParkingAreaCodeDtoBuilder.aParkingAreaCodeDto;
import static ca.ulaval.glo4003.parkings.helpers.ParkingAreaMother.createParkingAreaCode;
import static org.mockito.Mockito.when;

import ca.ulaval.glo4003.parkings.api.dto.ParkingAreaCodeDto;
import ca.ulaval.glo4003.parkings.assemblers.ParkingAreaCodeAssembler;
import ca.ulaval.glo4003.parkings.domain.ParkingAreaCode;
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
public class ParkingAreaCodeServiceTest {

  @Mock private ParkingAreaRepository parkingAreaRepository;
  @Mock private ParkingAreaCodeAssembler parkingAreaCodeAssembler;

  private ParkingAreaCodeService parkingAreaCodeService;
  private final ParkingAreaCode parkingAreaCode = createParkingAreaCode();
  private final ParkingAreaCodeDto parkingAreaCodeDto = aParkingAreaCodeDto().build();

  @Before
  public void setUp() {
    parkingAreaCodeService =
        new ParkingAreaCodeService(parkingAreaRepository, parkingAreaCodeAssembler);
  }

  @Test
  public void whenGettingAllZone_thenReturnZones() {
    List<ParkingAreaCode> parkingAreaCodes = new ArrayList<>();
    parkingAreaCodes.add(parkingAreaCode);
    when(parkingAreaRepository.getAllAreaCode()).thenReturn(parkingAreaCodes);

    when(parkingAreaCodeAssembler.assemble(parkingAreaCode)).thenReturn(parkingAreaCodeDto);
    List<ParkingAreaCodeDto> parkingAreaCodeDtoList = parkingAreaCodeService.getParkingAreas();

    Truth.assertThat(parkingAreaCodeDtoList).contains(parkingAreaCodeDto);
  }
}
