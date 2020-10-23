package ca.ulaval.glo4003.parkings.api;

import static ca.ulaval.glo4003.parkings.helpers.ParkingAreaCodeDtoBuilder.aParkingAreaCodeDto;
import static ca.ulaval.glo4003.parkings.helpers.ParkingAreaMother.createParkingAreaCode;
import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.when;

import ca.ulaval.glo4003.parkings.api.dto.ParkingAreaCodeDto;
import ca.ulaval.glo4003.parkings.domain.ParkingAreaCode;
import ca.ulaval.glo4003.parkings.services.ParkingAreaCodeService;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.Response;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ParkingAreaCodeResourceImplementationTest {
  ParkingAreaCode parkingAreaCode = createParkingAreaCode();

  @Mock private ParkingAreaCodeService parkingAreaCodeService;

  private ParkingAreaCodeResource parkingAreaCodeResource;
  private ParkingAreaCodeDto parkingAreaCodeDto = aParkingAreaCodeDto().build();

  @Before
  public void setUp() {
    parkingAreaCodeResource = new ParkingAreaCodeResourceImplementation(parkingAreaCodeService);
  }

  @Test
  public void whenGettingBills_thenGetBills() {
    List<ParkingAreaCodeDto> parkingAreaCodesDto = new ArrayList<>();
    parkingAreaCodesDto.add(parkingAreaCodeDto);

    when(parkingAreaCodeService.getParkingAreas()).thenReturn(parkingAreaCodesDto);

    List<ParkingAreaCodeDto> parkingAreaCodesDtoFromService =
        parkingAreaCodeService.getParkingAreas();
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
