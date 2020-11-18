package ca.ulaval.glo4003.gate.services;

import static ca.ulaval.glo4003.accesspasses.helpers.AccessPassMother.createAccessPassCode;
import static ca.ulaval.glo4003.cars.helpers.LicensePlateMother.createLicensePlate;
import static ca.ulaval.glo4003.gate.api.helpers.AccessStatusDtoBuilder.anAccessStatusDto;
import static ca.ulaval.glo4003.gate.api.helpers.DayOfWeekDtoBuilder.aDayOfWeekDto;
import static ca.ulaval.glo4003.times.helpers.DayOfWeekMother.createDayOfWeek;
import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import ca.ulaval.glo4003.accesspasses.domain.AccessPass;
import ca.ulaval.glo4003.accesspasses.exceptions.InvalidAccessPassExitException;
import ca.ulaval.glo4003.accesspasses.services.AccessPassService;
import ca.ulaval.glo4003.cars.assemblers.LicensePlateAssembler;
import ca.ulaval.glo4003.cars.domain.LicensePlate;
import ca.ulaval.glo4003.gate.api.dto.AccessStatusDto;
import ca.ulaval.glo4003.gate.api.dto.DayOfWeekDto;
import ca.ulaval.glo4003.gate.assemblers.DayOfWeekAssembler;
import ca.ulaval.glo4003.parkings.assemblers.AccessStatusAssembler;
import ca.ulaval.glo4003.parkings.domain.AccessStatus;
import ca.ulaval.glo4003.times.domain.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class GateEntryServiceTest {
  @Mock private AccessPassService accessPassService;
  @Mock private DayOfWeekAssembler dayOfWeekAssembler;
  @Mock private AccessStatusAssembler accessStatusAssembler;
  @Mock private AccessPass accessPass;
  @Mock private LicensePlateAssembler licensePlateAssembler;

  private GateEntryService gateEntryService;

  private final DayOfWeekDto dayOfWeekDto = aDayOfWeekDto().build();
  private final DayOfWeek dayOfWeek = createDayOfWeek();
  private final String accessPassCode = createAccessPassCode().toString();
  private final AccessStatusDto grantedAccessStatusDto = anAccessStatusDto().build();
  private final AccessStatusDto refusedAccessStatusDto = anAccessStatusDto().build();
  private final String accessPassLicensePlate = createLicensePlate().toString();
  private final LicensePlate LICENSE_PLATE = new LicensePlate(accessPassLicensePlate);
  private final List<AccessPass> accessPasses = new ArrayList<>();

  @Before
  public void setUp() {
    gateEntryService =
        new GateEntryService(
            accessPassService, dayOfWeekAssembler, accessStatusAssembler, licensePlateAssembler);

    accessPasses.add(accessPass);

    when(dayOfWeekAssembler.assemble(dayOfWeekDto)).thenReturn(dayOfWeek);
    when(accessPassService.getAccessPass(accessPassCode)).thenReturn(accessPass);
    when(accessStatusAssembler.assemble(AccessStatus.ACCESS_GRANTED))
        .thenReturn(grantedAccessStatusDto);
    when(accessStatusAssembler.assemble(AccessStatus.ACCESS_REFUSED))
        .thenReturn(refusedAccessStatusDto);
    when(licensePlateAssembler.assemble(accessPassLicensePlate)).thenReturn(LICENSE_PLATE);
    when(accessPassService.getAccessPassesByLicensePlate(LICENSE_PLATE)).thenReturn(accessPasses);
  }

  @Test
  public void givenValidAccessDay_whenValidatingAccessPassEntryWithCode_thenReturnAccessGranted() {
    when(accessPass.validateAccessDay(dayOfWeek)).thenReturn(true);

    AccessStatusDto accessStatusDto =
        gateEntryService.validateAccessPassEntryWithCode(dayOfWeekDto, accessPassCode);

    assertThat(accessStatusDto).isSameInstanceAs(grantedAccessStatusDto);
  }

  @Test
  public void givenValidAccessDay_whenValidatingAccessPassEntryWithCode_theShouldAdmitOnCampus() {
    when(accessPass.validateAccessDay(dayOfWeek)).thenReturn(true);

    gateEntryService.validateAccessPassEntryWithCode(dayOfWeekDto, accessPassCode);

    verify(accessPass).enterCampus();
  }

  @Test
  public void
      givenInvalidAccessDay_whenValidatingAccessPassEntryWithCode_thenReturnAccessRefused() {
    when(accessPass.validateAccessDay(dayOfWeek)).thenReturn(false);

    AccessStatusDto accessStatusDto =
        gateEntryService.validateAccessPassEntryWithCode(dayOfWeekDto, accessPassCode);

    assertThat(accessStatusDto).isSameInstanceAs(refusedAccessStatusDto);
  }

  @Test
  public void
      givenValidAccessDay_whenValidatingAccessPassEntryWithLicensePlate_thenReturnAccessGranted() {
    when(accessPass.validateAccessDay(dayOfWeek)).thenReturn(true);

    AccessStatusDto accessStatusDto =
        gateEntryService.validateAccessPassEntryWithLicensePlate(
            dayOfWeekDto, accessPassLicensePlate);

    assertThat(accessStatusDto).isSameInstanceAs(grantedAccessStatusDto);
  }

  @Test
  public void
      givenValidAccessDay_whenValidatingAccessPassEntryWithLicensePlate_theShouldAdmitOnCampus() {
    when(accessPass.validateAccessDay(dayOfWeek)).thenReturn(true);

    gateEntryService.validateAccessPassEntryWithLicensePlate(dayOfWeekDto, accessPassLicensePlate);

    verify(accessPass).enterCampus();
  }

  @Test
  public void
      givenInvalidAccessDay_whenValidatingAccessPassEntryWithLicensePlate_thenReturnAccessRefused() {
    when(accessPass.validateAccessDay(dayOfWeek)).thenReturn(false);

    AccessStatusDto accessStatusDto =
        gateEntryService.validateAccessPassEntryWithLicensePlate(
            dayOfWeekDto, accessPassLicensePlate);

    assertThat(accessStatusDto).isSameInstanceAs(refusedAccessStatusDto);
  }

  @Test
  public void whenValidateAccessPassExitWithCode_thenExitCampusHasBeenCalled() {
    gateEntryService.validateAccessPassExitWithCode(accessPassCode);

    verify(accessPass).exitCampus();
  }

  @Test
  public void
      givenAccessPassAdmittedOnCampus_whenValidateAccessPassExitWithLicensePlate_thenExitCampusHasBeenCalled() {
    when(accessPass.isAdmittedOnCampus()).thenReturn(true);

    gateEntryService.validateAccessPassExitWithLicensePlate(accessPassLicensePlate);

    verify(accessPass).exitCampus();
  }

  @Test(expected = InvalidAccessPassExitException.class)
  public void
      givenAccessPassNotAdmittedOnCampus_whenValidateAccessPassExitWithLicensePlate_thenThrowInvalidAccessPassExitException() {
    when(accessPass.isAdmittedOnCampus()).thenReturn(false);

    gateEntryService.validateAccessPassExitWithLicensePlate(accessPassLicensePlate);
  }
}
