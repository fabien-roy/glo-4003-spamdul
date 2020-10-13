package ca.ulaval.glo4003.gateentries.services;

import static ca.ulaval.glo4003.access.helpers.AccessPassMother.createAccessPassCode;
import static ca.ulaval.glo4003.gateentries.api.helpers.AccessStatusDtoBuilder.anAccessStatusDto;
import static ca.ulaval.glo4003.gateentries.api.helpers.DayOfWeekDtoBuilder.aDayOfWeekDto;
import static ca.ulaval.glo4003.times.helpers.DayMother.createDay;
import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.when;

import ca.ulaval.glo4003.access.domain.AccessPass;
import ca.ulaval.glo4003.access.services.AccessPassService;
import ca.ulaval.glo4003.gateentries.api.dto.AccessStatusDto;
import ca.ulaval.glo4003.gateentries.api.dto.DayOfWeekDto;
import ca.ulaval.glo4003.gateentries.assemblers.DayOfWeekAssembler;
import ca.ulaval.glo4003.parkings.assemblers.AccessStatusAssembler;
import ca.ulaval.glo4003.parkings.domain.AccessStatus;
import ca.ulaval.glo4003.times.domain.Days;
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

  private GateEntryService gateEntryService;

  private final DayOfWeekDto dayOfWeekDto = aDayOfWeekDto().build();
  private final Days dayOfWeek = createDay();
  private final String accessPassCode = createAccessPassCode().toString();
  private final AccessStatusDto grantedAccessStatusDto = anAccessStatusDto().build();
  private final AccessStatusDto refusedAccessStatusDto = anAccessStatusDto().build();

  @Before
  public void setUp() {
    gateEntryService =
        new GateEntryService(accessPassService, dayOfWeekAssembler, accessStatusAssembler);

    when(dayOfWeekAssembler.assemble(dayOfWeekDto)).thenReturn(dayOfWeek);
    when(accessPassService.getAccessPass(accessPassCode)).thenReturn(accessPass);
    when(accessStatusAssembler.assemble(AccessStatus.ACCESS_GRANTED))
        .thenReturn(grantedAccessStatusDto);
    when(accessStatusAssembler.assemble(AccessStatus.ACCESS_REFUSED))
        .thenReturn(refusedAccessStatusDto);
  }

  @Test
  public void givenValidAccessDay_whenValidatingAccessPass_thenReturnAccessGranted() {
    when(accessPass.validateAccessDay(dayOfWeek)).thenReturn(true);

    AccessStatusDto accessStatusDto =
        gateEntryService.validateAccessPass(dayOfWeekDto, accessPassCode);

    assertThat(accessStatusDto).isSameInstanceAs(grantedAccessStatusDto);
  }

  @Test
  public void givenInvalidAccessDay_whenValidatingAccessPass_thenReturnAccessRefused() {
    when(accessPass.validateAccessDay(dayOfWeek)).thenReturn(false);

    AccessStatusDto accessStatusDto =
        gateEntryService.validateAccessPass(dayOfWeekDto, accessPassCode);

    assertThat(accessStatusDto).isSameInstanceAs(refusedAccessStatusDto);
  }
}
