package ca.ulaval.glo4003.api.parking;

import static ca.ulaval.glo4003.api.parking.helpers.AccessStatusDtoBuilder.anAccessStatusDto;
import static ca.ulaval.glo4003.domain.parking.helpers.ParkingStickerMother.createParkingStickerCode;
import static org.mockito.Mockito.when;

import ca.ulaval.glo4003.api.parking.dto.AccessStatusDto;
import ca.ulaval.glo4003.api.parking.dto.ParkingStickerCodeDto;
import ca.ulaval.glo4003.api.parking.dto.ParkingStickerDto;
import ca.ulaval.glo4003.domain.parking.AccessStatus;
import ca.ulaval.glo4003.domain.parking.ParkingService;
import com.google.common.truth.Truth;
import javax.ws.rs.core.Response;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ParkingResourceImplementationTest {
  @Mock private ParkingService parkingService;
  @Mock private ParkingStickerDto parkingStickerDto;
  @Mock private ParkingStickerCodeDto parkingStickerCodeDto;

  private ParkingResource parkingResource;

  private String parkingStickerCode = createParkingStickerCode().toString();
  private AccessStatusDto accessStatusDto;

  @Before
  public void setUp() {
    parkingResource = new ParkingResourceImplementation(parkingService);

    accessStatusDto = anAccessStatusDto().build();

    when(parkingService.addParkingSticker(parkingStickerDto)).thenReturn(parkingStickerCodeDto);
    when(parkingService.validateParkingStickerCode(parkingStickerCode)).thenReturn(accessStatusDto);
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

  @Test
  public void whenValidateParkingStickerCode_thenValidateParkingStickerCodeToService() {
    Response response = parkingResource.validateParkingSticker(parkingStickerCode);
    AccessStatusDto receivedAccessStatusDto = (AccessStatusDto) response.getEntity();

    Truth.assertThat(receivedAccessStatusDto.accessStatus).isEqualTo(accessStatusDto.accessStatus);
  }

  @Test
  public void
      givenParkingStickerCodeValidAccessDay_whenValidateParkingStickerCode_thenRespondWithAcceptedStatus() {
    accessStatusDto =
        anAccessStatusDto().withAccessStatus(AccessStatus.ACCESS_GRANTED.toString()).build();
    when(parkingService.validateParkingStickerCode(parkingStickerCode)).thenReturn(accessStatusDto);

    Response response = parkingResource.validateParkingSticker(parkingStickerCode);

    Truth.assertThat(response.getStatus()).isEqualTo(Response.Status.ACCEPTED.getStatusCode());
  }

  @Test
  public void
      givenParkingStickerCodeInvalidAccessDay_whenValidateParkingStickerCode_thenRespondWithForbiddenStatus() {
    accessStatusDto =
        anAccessStatusDto().withAccessStatus(AccessStatus.ACCESS_REFUSED.toString()).build();
    when(parkingService.validateParkingStickerCode(parkingStickerCode)).thenReturn(accessStatusDto);

    Response response = parkingResource.validateParkingSticker(parkingStickerCode);

    Truth.assertThat(response.getStatus()).isEqualTo(Response.Status.FORBIDDEN.getStatusCode());
  }
}
