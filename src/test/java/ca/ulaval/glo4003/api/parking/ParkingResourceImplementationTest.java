package ca.ulaval.glo4003.api.parking;

import static org.mockito.Mockito.when;

import ca.ulaval.glo4003.api.parking.dto.AccessStatusDto;
import ca.ulaval.glo4003.api.parking.dto.ParkingStickerCodeDto;
import ca.ulaval.glo4003.api.parking.dto.ParkingStickerDto;
import ca.ulaval.glo4003.domain.parking.AccessStatus;
import ca.ulaval.glo4003.domain.parking.AccessStatusAssembler;
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
  @Mock private AccessStatusDto accessStatusDto;

  private ParkingResource parkingResource;
  private AccessStatusAssembler accessStatusAssembler;

  @Before
  public void setUp() {
    parkingResource = new ParkingResourceImplementation(parkingService);
    accessStatusAssembler = new AccessStatusAssembler();
  }

  @Test
  public void whenAddParkingSticker_thenAddParkingStickerToService() {
    when(parkingService.addParkingSticker(parkingStickerDto)).thenReturn(parkingStickerCodeDto);

    Response response = parkingResource.addParkingSticker(parkingStickerDto);
    ParkingStickerCodeDto parkingStickerCodeDto = (ParkingStickerCodeDto) response.getEntity();

    Truth.assertThat(parkingStickerCodeDto.parkingStickerCode)
        .isEqualTo(parkingStickerCodeDto.parkingStickerCode);
  }

  @Test
  public void whenAddParkingSticker_thenRespondWithCreatedStatus() {
    when(parkingService.addParkingSticker(parkingStickerDto)).thenReturn(parkingStickerCodeDto);

    Response response = parkingResource.addParkingSticker(parkingStickerDto);

    Truth.assertThat(response.getStatus()).isEqualTo(Response.Status.CREATED.getStatusCode());
  }

  @Test
  public void whenValidateParkingStickerCode_thenValidateParkingStickerCodeToService() {
    when(parkingService.validateParkingStickerCode(parkingStickerCodeDto))
        .thenReturn(accessStatusAssembler.assemble(AccessStatus.ACCESS_GRANTED.toString()));

    parkingResource.validateParkingStickerCode(parkingStickerCodeDto);

    Mockito.verify(parkingService).validateParkingStickerCode(parkingStickerCodeDto);
    Truth.assertThat(accessStatusDto.accessStatus).isSameInstanceAs(accessStatusDto.accessStatus);
  }

  @Test
  public void
      givenParkingStickerCodeValidAccessDay_whenValidateParkingStickerCode_thenRespondWithAcceptedStatus() {
    when(parkingService.validateParkingStickerCode(parkingStickerCodeDto))
        .thenReturn(accessStatusAssembler.assemble(AccessStatus.ACCESS_GRANTED.toString()));

    Response response = parkingResource.validateParkingStickerCode(parkingStickerCodeDto);

    Truth.assertThat(response.getStatus()).isEqualTo(Response.Status.ACCEPTED.getStatusCode());
  }

  @Test
  public void
      givenParkingStickerCodeInvalidAccessDay_whenValidateParkingStickerCode_thenRespondWithForbiddenStatus() {
    when(parkingService.validateParkingStickerCode(parkingStickerCodeDto))
        .thenReturn(accessStatusAssembler.assemble(AccessStatus.ACCESS_REFUSED.toString()));

    Response response = parkingResource.validateParkingStickerCode(parkingStickerCodeDto);

    Truth.assertThat(response.getStatus()).isEqualTo(Response.Status.FORBIDDEN.getStatusCode());
  }
}
