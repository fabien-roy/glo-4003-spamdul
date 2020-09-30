package ca.ulaval.glo4003.offenses.services;

import static ca.ulaval.glo4003.offenses.helpers.OffenseValidationBuilder.anOffenseValidation;
import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

import ca.ulaval.glo4003.offenses.api.dto.OffenseValidationDto;
import ca.ulaval.glo4003.offenses.assemblers.OffenseAssembler;
import ca.ulaval.glo4003.offenses.assemblers.OffenseValidationAssembler;
import ca.ulaval.glo4003.offenses.domain.*;
import ca.ulaval.glo4003.parkings.domain.ParkingSticker;
import ca.ulaval.glo4003.parkings.domain.ParkingStickerRepository;
import ca.ulaval.glo4003.parkings.exceptions.NotFoundParkingStickerException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class OffenseServiceTest {
  @Mock private ParkingStickerRepository parkingStickerRepository;
  @Mock private OffenseValidationAssembler offenseValidationAssembler;
  @Mock private OffenseAssembler offenseAssembler;
  @Mock private OffenseValidationDto offenseValidationDto;
  @Mock private ParkingSticker parkingSticker;
  @Mock private OffenseRepository offenseRepository;

  private OffenseService offenseService;
  private OffenseValidation offenseValidation;

  private Offense zone01offense = new Offense("test", OffenseCodes.ZONE_01, 0);
  private Offense vig01offense = new Offense("test", OffenseCodes.VIG_01, 0);
  private Offense vig02offense = new Offense("test", OffenseCodes.VIG_02, 0);
  private Offense noneoffense = new Offense("vignette invalide", OffenseCodes.VIG_02, 45);

  @Before
  public void setUp() {
    offenseService =
        new OffenseService(
            parkingStickerRepository,
            offenseValidationAssembler,
            offenseAssembler,
            offenseRepository);

    offenseValidation = anOffenseValidation().build();

    when(offenseValidationAssembler.assemble(offenseValidationDto)).thenReturn(offenseValidation);
    when(parkingSticker.validateParkingStickerAreaCode(offenseValidation.getParkingAreaCode()))
        .thenReturn(false);
    when(parkingStickerRepository.findByCode(offenseValidation.getParkingStickerCode()))
        .thenReturn(parkingSticker);
    when(offenseRepository.findByCode(OffenseCodes.ZONE_01)).thenReturn(zone01offense);
    when(offenseRepository.findByCode(OffenseCodes.VIG_01)).thenReturn(vig01offense);
    when(offenseRepository.findByCode(OffenseCodes.VIG_02)).thenReturn(vig02offense);
  }

  @Test
  public void whenCheckingIfOffenseNeeded_thenParkingStickerRepositoryIsCalled() {
    offenseService.validateOffense(offenseValidationDto);

    Mockito.verify(parkingStickerRepository)
        .findByCode(eq(offenseValidation.getParkingStickerCode()));
  }

  @Test
  public void
      givenValidOffenseValidationDto_whenValidatingOffense_thenInvalidStickerOffenseIsReturned() {
    when(parkingStickerRepository.findByCode(offenseValidation.getParkingStickerCode()))
        .thenThrow(new NotFoundParkingStickerException());
    Offense offense = new Offense("vignette invalide", OffenseCodes.VIG_02, 45);

    offenseService.validateOffense(offenseValidationDto);

    Mockito.verify(offenseAssembler).assemble(vig02offense);
  }

  @Test
  public void givenValidOffenseValidationDto_whenValidatingOffense_thenNoOffenseIsReturned() {
    when(parkingSticker.validateParkingStickerAreaCode(offenseValidation.getParkingAreaCode()))
        .thenReturn(true);

    offenseService.validateOffense(offenseValidationDto);

    ArgumentCaptor<Offense> argumentCaptor = ArgumentCaptor.forClass(Offense.class);
    Mockito.verify(offenseAssembler).assemble(argumentCaptor.capture());
    Offense capturedArgument = argumentCaptor.getValue();
    assertThat(capturedArgument.getCode()).isEqualTo(OffenseCodes.NONE);
  }

  @Test
  public void
      givenValidOffenseValidationDto_whenValidatingOffense_thenWrongZoneOffenseIsReturned() {
    when(parkingSticker.validateParkingStickerAreaCode(offenseValidation.getParkingAreaCode()))
        .thenReturn(false);
    Offense offense = new Offense("mauvaise zone", OffenseCodes.ZONE_01, 55);

    offenseService.validateOffense(offenseValidationDto);

    Mockito.verify(offenseAssembler).assemble(zone01offense);
  }
}
