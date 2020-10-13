package ca.ulaval.glo4003.parkings.api;

import static org.mockito.Mockito.when;

import ca.ulaval.glo4003.parkings.api.dto.ParkingStickerCodeDto;
import ca.ulaval.glo4003.parkings.api.dto.ParkingStickerDto;
import ca.ulaval.glo4003.parkings.services.ParkingStickerService;
import com.google.common.truth.Truth;
import javax.ws.rs.core.Response;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ParkingResourceImplementationTest {
  @Mock private ParkingStickerService parkingStickerService;
  @Mock private ParkingStickerDto parkingStickerDto;
  @Mock private ParkingStickerCodeDto parkingStickerCodeDto;

  private ParkingResource parkingResource;

  @Before
  public void setUp() {
    parkingResource = new ParkingResourceImplementation(parkingStickerService);

    when(parkingStickerService.addParkingSticker(parkingStickerDto))
        .thenReturn(parkingStickerCodeDto);
  }

  @Test
  public void whenAddParkingSticker_thenAddParkingStickerToService() {
    Response response = parkingResource.addParkingSticker(parkingStickerDto);
    ParkingStickerCodeDto parkingStickerCodeDto = (ParkingStickerCodeDto) response.getEntity();

    Truth.assertThat(parkingStickerCodeDto.parkingStickerCode)
        .isEqualTo(parkingStickerCodeDto.parkingStickerCode);
  }

  @Test
  public void whenAddParkingSticker_thenRespondWithCreatedStatus() {
    Response response = parkingResource.addParkingSticker(parkingStickerDto);

    Truth.assertThat(response.getStatus()).isEqualTo(Response.Status.CREATED.getStatusCode());
  }
}
