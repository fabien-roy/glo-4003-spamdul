package ca.ulaval.glo4003.parkings.api;

import static ca.ulaval.glo4003.parkings.helpers.ParkingAreaDtoBuilder.aParkingAreaDto;
import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.when;

import ca.ulaval.glo4003.parkings.api.dto.ParkingAreaDto;
import ca.ulaval.glo4003.parkings.services.ParkingAreaService;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.Response;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ParkingAreaResourceImplementationTest {

  @Mock private ParkingAreaService parkingAreaService;

  private ParkingAreaResource parkingAreaResource;
  private ParkingAreaDto parkingAreaDto = aParkingAreaDto().build();

  @Before
  public void setUp() {
    parkingAreaResource = new ParkingAreaResourceImplementation(parkingAreaService);
  }

  @Test
  public void whenGettingParkingAreas_thenGetParkingAreas() {
    List<ParkingAreaDto> parkingAreasDto = new ArrayList<>();
    parkingAreasDto.add(parkingAreaDto);

    when(parkingAreaService.getParkingAreas()).thenReturn(parkingAreasDto);

    List<ParkingAreaDto> parkingAreasDtoFromService = parkingAreaService.getParkingAreas();
    Response response = parkingAreaResource.getParkingAreas();
    Object entities = response.getEntity();

    assertThat(parkingAreasDtoFromService).isEqualTo(entities);
  }

  @Test
  public void whenGettingParkingAreas_thenRespondWithOkStatus() {
    Response response = parkingAreaResource.getParkingAreas();

    assertThat(response.getStatus()).isEqualTo(Response.Status.OK.getStatusCode());
  }
}
