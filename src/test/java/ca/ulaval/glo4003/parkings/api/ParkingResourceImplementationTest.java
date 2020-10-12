package ca.ulaval.glo4003.parkings.api;

import static ca.ulaval.glo4003.parkings.helpers.AccessStatusDtoBuilder.anAccessStatusDto;
import static ca.ulaval.glo4003.parkings.helpers.ParkingStickerMother.createParkingStickerCode;
import static org.mockito.Mockito.when;

import ca.ulaval.glo4003.parkings.api.dto.AccessStatusDto;
import ca.ulaval.glo4003.parkings.api.dto.ParkingStickerCodeDto;
import ca.ulaval.glo4003.parkings.api.dto.ParkingStickerDto;
import ca.ulaval.glo4003.parkings.domain.AccessStatus;
import ca.ulaval.glo4003.parkings.services.ParkingService;
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
