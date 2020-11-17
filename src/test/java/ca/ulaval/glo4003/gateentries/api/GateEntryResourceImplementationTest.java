package ca.ulaval.glo4003.gateentries.api;

import static ca.ulaval.glo4003.accesspasses.helpers.AccessPassMother.createAccessPassCode;
import static ca.ulaval.glo4003.cars.helpers.LicensePlateMother.createLicensePlate;
import static ca.ulaval.glo4003.gateentries.api.helpers.AccessStatusDtoBuilder.anAccessStatusDto;
import static ca.ulaval.glo4003.gateentries.api.helpers.DayOfWeekDtoBuilder.aDayOfWeekDto;
import static org.mockito.Mockito.when;

import ca.ulaval.glo4003.gateentries.api.dto.AccessStatusDto;
import ca.ulaval.glo4003.gateentries.api.dto.DayOfWeekDto;
import ca.ulaval.glo4003.gateentries.services.GateEntryService;
import ca.ulaval.glo4003.parkings.domain.AccessStatus;
import com.google.common.truth.Truth;
import javax.ws.rs.core.Response;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class GateEntryResourceImplementationTest {
  @Mock GateEntryService gateEntryService;

  private GateEntryResource gateEntryResource;

  private DayOfWeekDto dayOfWeekDto = aDayOfWeekDto().build();
  private String accessPassCode = createAccessPassCode().toString();
  private AccessStatusDto accessStatusDto = anAccessStatusDto().build();
  private String accessPassLicensePlate = createLicensePlate().toString();

  @Before
  public void setUp() {
    gateEntryResource = new GateEntryResourceImplementation(gateEntryService);

    when(gateEntryService.validateAccessPassEntryWithCode(dayOfWeekDto, accessPassCode))
        .thenReturn(accessStatusDto);

    when(gateEntryService.validateAccessPassEntryWithLicensePlate(
            dayOfWeekDto, accessPassLicensePlate))
        .thenReturn(accessStatusDto);
  }

  @Test
  public void whenValidatingAccessPassEntryWithCode_thenValidateAccessPassWithService() {
    Response response =
        gateEntryResource.validateAccessPassEntryWithCode(dayOfWeekDto, accessPassCode);
    AccessStatusDto receivedAccessStatusDto = (AccessStatusDto) response.getEntity();

    Truth.assertThat(receivedAccessStatusDto.accessStatus).isEqualTo(accessStatusDto.accessStatus);
  }

  @Test
  public void
      givenValidAccessPass_whenValidatingAccessPassEntryWithCode_thenRespondWithAcceptedStatus() {
    accessStatusDto =
        anAccessStatusDto().withAccessStatus(AccessStatus.ACCESS_GRANTED.toString()).build();
    when(gateEntryService.validateAccessPassEntryWithCode(dayOfWeekDto, accessPassCode))
        .thenReturn(accessStatusDto);

    Response response =
        gateEntryResource.validateAccessPassEntryWithCode(dayOfWeekDto, accessPassCode);

    Truth.assertThat(response.getStatus()).isEqualTo(Response.Status.ACCEPTED.getStatusCode());
  }

  @Test
  public void
      givenInvalidAccessPass_whenValidatingAccessEntryPassWithCode_thenRespondWithForbiddenStatus() {
    accessStatusDto =
        anAccessStatusDto().withAccessStatus(AccessStatus.ACCESS_REFUSED.toString()).build();
    when(gateEntryService.validateAccessPassEntryWithCode(dayOfWeekDto, accessPassCode))
        .thenReturn(accessStatusDto);

    Response response =
        gateEntryResource.validateAccessPassEntryWithCode(dayOfWeekDto, accessPassCode);

    Truth.assertThat(response.getStatus()).isEqualTo(Response.Status.FORBIDDEN.getStatusCode());
  }

  @Test
  public void whenValidatingAccessPassEntryWithLicensePlate_thenValidateAccessPassWithService() {
    Response response =
        gateEntryResource.validateAccessPassEntryWithLicensePlate(
            dayOfWeekDto, accessPassLicensePlate);
    AccessStatusDto receivedAccessStatusDto = (AccessStatusDto) response.getEntity();

    Truth.assertThat(receivedAccessStatusDto.accessStatus).isEqualTo(accessStatusDto.accessStatus);
  }

  @Test
  public void
      givenValidAccessPass_whenValidatingAccessPassEntryWithLicensePlate_thenRespondWithAcceptedStatus() {
    accessStatusDto =
        anAccessStatusDto().withAccessStatus(AccessStatus.ACCESS_GRANTED.toString()).build();
    when(gateEntryService.validateAccessPassEntryWithLicensePlate(
            dayOfWeekDto, accessPassLicensePlate))
        .thenReturn(accessStatusDto);

    Response response =
        gateEntryResource.validateAccessPassEntryWithLicensePlate(
            dayOfWeekDto, accessPassLicensePlate);

    Truth.assertThat(response.getStatus()).isEqualTo(Response.Status.ACCEPTED.getStatusCode());
  }

  @Test
  public void
      givenInvalidAccessPass_whenValidatingAccessPassEntryWithLicensePlate_thenRespondWithForbiddenStatus() {
    accessStatusDto =
        anAccessStatusDto().withAccessStatus(AccessStatus.ACCESS_REFUSED.toString()).build();
    when(gateEntryService.validateAccessPassEntryWithLicensePlate(
            dayOfWeekDto, accessPassLicensePlate))
        .thenReturn(accessStatusDto);

    Response response =
        gateEntryResource.validateAccessPassEntryWithLicensePlate(
            dayOfWeekDto, accessPassLicensePlate);

    Truth.assertThat(response.getStatus()).isEqualTo(Response.Status.FORBIDDEN.getStatusCode());
  }

  @Test
  public void validateAccessPassExitWithCode_thenRespondWithOkStatus() {
    Response response = gateEntryResource.validateAccessPassExitWithCode(accessPassCode);

    Truth.assertThat(response.getStatus()).isEqualTo(Response.Status.OK.getStatusCode());
  }

  @Test
  public void validateAccessPassExitWithLicensePlate_thenRespondWithOkStatus() {
    Response response =
        gateEntryResource.validateAccessPassExitWithLicensePlate(accessPassLicensePlate);

    Truth.assertThat(response.getStatus()).isEqualTo(Response.Status.OK.getStatusCode());
  }
}
