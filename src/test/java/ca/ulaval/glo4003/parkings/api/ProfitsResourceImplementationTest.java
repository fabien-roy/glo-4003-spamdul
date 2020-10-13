package ca.ulaval.glo4003.parkings.api;

import static ca.ulaval.glo4003.parkings.helpers.AccessStatusDtoBuilder.anAccessStatusDto;
import static ca.ulaval.glo4003.parkings.helpers.ParkingStickerMother.createParkingStickerCode;
import static org.mockito.Mockito.when;

import ca.ulaval.glo4003.parkings.api.dto.AccessStatusDto;
import ca.ulaval.glo4003.parkings.api.dto.ParkingStickerCodeDto;
import ca.ulaval.glo4003.parkings.api.dto.ParkingStickerDto;
import ca.ulaval.glo4003.parkings.domain.AccessStatus;
import ca.ulaval.glo4003.parkings.services.ParkingStickerService;
import com.google.common.truth.Truth;
import javax.ws.rs.core.Response;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ProfitsResourceImplementationTest {
  @Mock private ParkingStickerService parkingStickerService;
  @Mock private ParkingStickerDto parkingStickerDto;
  @Mock private ParkingStickerCodeDto parkingStickerCodeDto;

  private ParkingResource parkingResource;

  private String parkingStickerCode = createParkingStickerCode().toString();
  private AccessStatusDto accessStatusDto;

  @Before
  public void setUp() {
    parkingResource = new ParkingResourceImplementation(parkingStickerService);

    accessStatusDto = anAccessStatusDto().build();

    when(parkingStickerService.addParkingSticker(parkingStickerDto))
        .thenReturn(parkingStickerCodeDto);
    when(parkingStickerService.validateParkingStickerCode(parkingStickerCode))
        .thenReturn(accessStatusDto);
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
    when(parkingStickerService.validateParkingStickerCode(parkingStickerCode))
        .thenReturn(accessStatusDto);

    Response response = parkingResource.validateParkingSticker(parkingStickerCode);

    Truth.assertThat(response.getStatus()).isEqualTo(Response.Status.ACCEPTED.getStatusCode());
  }

  @Test
  public void
      givenParkingStickerCodeInvalidAccessDay_whenValidateParkingStickerCode_thenRespondWithForbiddenStatus() {
    accessStatusDto =
        anAccessStatusDto().withAccessStatus(AccessStatus.ACCESS_REFUSED.toString()).build();
    when(parkingStickerService.validateParkingStickerCode(parkingStickerCode))
        .thenReturn(accessStatusDto);

    Response response = parkingResource.validateParkingSticker(parkingStickerCode);

    Truth.assertThat(response.getStatus()).isEqualTo(Response.Status.FORBIDDEN.getStatusCode());
  }
}
