package ca.ulaval.glo4003.gates.api;

import static ca.ulaval.glo4003.accesspasses.helpers.AccessPassMother.createAccessPassCode;
import static ca.ulaval.glo4003.cars.helpers.LicensePlateMother.createLicensePlate;
import static ca.ulaval.glo4003.gates.helpers.AccessStatusDtoBuilder.anAccessStatusDto;
import static ca.ulaval.glo4003.gates.helpers.DateTimeDtoBuilder.aDateTimeDto;
import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.when;

import ca.ulaval.glo4003.gates.api.dto.AccessStatusDto;
import ca.ulaval.glo4003.gates.api.dto.DateTimeDto;
import ca.ulaval.glo4003.gates.services.GateService;
import ca.ulaval.glo4003.parkings.domain.AccessStatus;
import javax.ws.rs.core.Response;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class GateResourceImplementationTest {
  @Mock GateService gateService;

  private GateResource gateResource;

  private final DateTimeDto dateTimeDto = aDateTimeDto().build();
  private final String accessPassCode = createAccessPassCode().toString();
  private final String accessPassLicensePlate = createLicensePlate().toString();
  private AccessStatusDto accessStatusDto = anAccessStatusDto().build();

  @Before
  public void setUp() {
    gateResource = new GateResourceImplementation(gateService);

    when(gateService.validateAccessPassEntryWithCode(dateTimeDto, accessPassCode))
        .thenReturn(accessStatusDto);

    when(gateService.validateAccessPassEntryWithLicensePlate(dateTimeDto, accessPassLicensePlate))
        .thenReturn(accessStatusDto);
  }

  @Test
  public void whenValidatingAccessPassEntryWithCode_thenValidateAccessPassWithService() {
    Response response = gateResource.validateAccessPassEntryWithCode(dateTimeDto, accessPassCode);
    AccessStatusDto receivedAccessStatusDto = (AccessStatusDto) response.getEntity();

    assertThat(receivedAccessStatusDto.accessStatus).isEqualTo(accessStatusDto.accessStatus);
  }

  @Test
  public void
      givenValidAccessPass_whenValidatingAccessPassEntryWithCode_thenRespondWithAcceptedStatus() {
    accessStatusDto =
        anAccessStatusDto().withAccessStatus(AccessStatus.ACCESS_GRANTED.toString()).build();
    when(gateService.validateAccessPassEntryWithCode(dateTimeDto, accessPassCode))
        .thenReturn(accessStatusDto);

    Response response = gateResource.validateAccessPassEntryWithCode(dateTimeDto, accessPassCode);

    assertThat(response.getStatus()).isEqualTo(Response.Status.ACCEPTED.getStatusCode());
  }

  @Test
  public void
      givenInvalidAccessPass_whenValidatingAccessEntryPassWithCode_thenRespondWithForbiddenStatus() {
    accessStatusDto =
        anAccessStatusDto().withAccessStatus(AccessStatus.ACCESS_REFUSED.toString()).build();
    when(gateService.validateAccessPassEntryWithCode(dateTimeDto, accessPassCode))
        .thenReturn(accessStatusDto);

    Response response = gateResource.validateAccessPassEntryWithCode(dateTimeDto, accessPassCode);

    assertThat(response.getStatus()).isEqualTo(Response.Status.FORBIDDEN.getStatusCode());
  }

  @Test
  public void whenValidatingAccessPassEntryWithLicensePlate_thenValidateAccessPassWithService() {
    Response response =
        gateResource.validateAccessPassEntryWithLicensePlate(dateTimeDto, accessPassLicensePlate);
    AccessStatusDto receivedAccessStatusDto = (AccessStatusDto) response.getEntity();

    assertThat(receivedAccessStatusDto.accessStatus).isEqualTo(accessStatusDto.accessStatus);
  }

  @Test
  public void
      givenValidAccessPass_whenValidatingAccessPassEntryWithLicensePlate_thenRespondWithAcceptedStatus() {
    accessStatusDto =
        anAccessStatusDto().withAccessStatus(AccessStatus.ACCESS_GRANTED.toString()).build();
    when(gateService.validateAccessPassEntryWithLicensePlate(dateTimeDto, accessPassLicensePlate))
        .thenReturn(accessStatusDto);

    Response response =
        gateResource.validateAccessPassEntryWithLicensePlate(dateTimeDto, accessPassLicensePlate);

    assertThat(response.getStatus()).isEqualTo(Response.Status.ACCEPTED.getStatusCode());
  }

  @Test
  public void
      givenInvalidAccessPass_whenValidatingAccessPassEntryWithLicensePlate_thenRespondWithForbiddenStatus() {
    accessStatusDto =
        anAccessStatusDto().withAccessStatus(AccessStatus.ACCESS_REFUSED.toString()).build();
    when(gateService.validateAccessPassEntryWithLicensePlate(dateTimeDto, accessPassLicensePlate))
        .thenReturn(accessStatusDto);

    Response response =
        gateResource.validateAccessPassEntryWithLicensePlate(dateTimeDto, accessPassLicensePlate);

    assertThat(response.getStatus()).isEqualTo(Response.Status.FORBIDDEN.getStatusCode());
  }

  @Test
  public void validateAccessPassExitWithCode_thenRespondWithOkStatus() {
    Response response = gateResource.validateAccessPassExitWithCode(accessPassCode);

    assertThat(response.getStatus()).isEqualTo(Response.Status.OK.getStatusCode());
  }

  @Test
  public void validateAccessPassExitWithLicensePlate_thenRespondWithOkStatus() {
    Response response = gateResource.validateAccessPassExitWithLicensePlate(accessPassLicensePlate);

    assertThat(response.getStatus()).isEqualTo(Response.Status.OK.getStatusCode());
  }
}
