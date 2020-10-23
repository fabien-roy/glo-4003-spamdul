package ca.ulaval.glo4003.parkings.api;

import static ca.ulaval.glo4003.parkings.helpers.ParkingAreaCodeDtoBuilder.aParkingAreaCodeDto;
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

  private ParkingAreaCodeResource parkingAreaCodeResource;
  private ParkingAreaDto parkingAreaDto = aParkingAreaCodeDto().build();

  @Before
  public void setUp() {
    parkingAreaCodeResource = new ParkingAreaResourceImplementation(parkingAreaService);
  }

  @Test
  public void whenGettingBills_thenGetBills() {
    List<ParkingAreaDto> parkingAreaCodesDto = new ArrayList<>();
    parkingAreaCodesDto.add(parkingAreaDto);

    when(parkingAreaService.getParkingAreas()).thenReturn(parkingAreaCodesDto);

    List<ParkingAreaDto> parkingAreaCodesDtoFromService = parkingAreaService.getParkingAreas();
    Response response = parkingAreaCodeResource.getParkingAreas();
    Object entities = response.getEntity();

    assertThat(parkingAreaCodesDtoFromService).isEqualTo(entities);
  }

  @Test
  public void whenGettingBills_thenRespondWithOkStatus() {
    Response response = parkingAreaCodeResource.getParkingAreas();

    assertThat(response.getStatus()).isEqualTo(Response.Status.OK.getStatusCode());
  }
}
