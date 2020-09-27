package ca.ulaval.glo4003.api.parking;

import static org.mockito.Mockito.when;

import ca.ulaval.glo4003.api.parking.dto.ParkingStickerCodeDto;
import ca.ulaval.glo4003.api.parking.dto.ParkingStickerDto;
import ca.ulaval.glo4003.domain.parking.ParkingService;
import com.google.common.truth.Truth;
import javax.ws.rs.core.Response;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ParkingResourceImplementationTest {
  @Mock private ParkingService parkingService;
  @Mock private ParkingStickerDto parkingStickerDto;
  @Mock private ParkingStickerCodeDto parkingStickerCodeDto;

  private ParkingResource parkingResource;

  @Before
  public void setUp() {
    parkingResource = new ParkingResourceImplementation(parkingService);
  }

  @Test
  public void whenAddParkingSticker_thenAddParkingStickerToService() {
    when(parkingService.addParkingSticker(parkingStickerDto)).thenReturn(parkingStickerCodeDto);

    Response response = parkingResource.addParkingSticker(parkingStickerDto);
    ParkingStickerCodeDto parkingStickerCodeDto = (ParkingStickerCodeDto) response.getEntity();

    Truth.assertThat(parkingStickerCodeDto.code).isSameInstanceAs(parkingStickerCodeDto.code);
  }

  @Test
  public void whenAddParkingSticker_thenRespondWithCreatedStatus() {
    when(parkingService.addParkingSticker(parkingStickerDto)).thenReturn(parkingStickerCodeDto);

    Response response = parkingResource.addParkingSticker(parkingStickerDto);

    Truth.assertThat(response.getStatus()).isEqualTo(Response.Status.CREATED.getStatusCode());
  }

  @Test
  public void whenValidateParkingStickerCode_thenValidateParkingStickerCodeToService() {
    parkingResource.addParkingSticker(parkingStickerDto);

    Mockito.verify(parkingService).addParkingSticker(parkingStickerDto);
  }
}
