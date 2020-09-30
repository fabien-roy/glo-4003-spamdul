package ca.ulaval.glo4003.domain.offense;

import static ca.ulaval.glo4003.domain.offense.helpers.OffenseValidationBuilder.anOffenseValidation;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

import ca.ulaval.glo4003.api.offense.dto.OffenseDto;
import ca.ulaval.glo4003.api.offense.dto.OffenseValidationDto;
import ca.ulaval.glo4003.domain.parking.ParkingSticker;
import ca.ulaval.glo4003.domain.parking.ParkingStickerRepository;
import ca.ulaval.glo4003.domain.parking.exception.NotFoundParkingStickerException;
import com.google.common.truth.Truth;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
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

  private OffenseService offenseService;
  private OffenseValidation offenseValidation;
  private OffenseRepository offenseRepository;

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
  }

  @Test
  public void whenCheckingIfOffenseNeeded_thenParkingStickerRepositoryIsCalled() {
    offenseService.isOffenseNeeded(offenseValidationDto);

    Mockito.verify(parkingStickerRepository)
        .findByCode(eq(offenseValidation.getParkingStickerCode()));
  }

  @Test
  public void
      givenValidOffenseValidationDto_whenCheckingIfOffenseNeeded_thenInvalidStickerOffenseIsReturned() {
    when(parkingStickerRepository.findByCode(offenseValidation.getParkingStickerCode()))
        .thenThrow(new NotFoundParkingStickerException());
    Offense offense = new Offense("vignette invalide", "VIG_02", 45);

    OffenseDto offenseDto = offenseService.isOffenseNeeded(offenseValidationDto);

    Truth.assertThat(offenseDto.reasonCode).isEqualTo(offense.getReasonCode());
  }

  @Test
  public void givenValidOffenseValidationDto_whenCheckingIfOffenseNeeded_thenNoOffenseIsReturned() {
    when(parkingSticker.validateParkingStickerAreaCode(offenseValidation.getParkingAreaCode()))
        .thenReturn(true);
    Offense offense = new Offense("Aucune infraction signalée", "000", 0);

    OffenseDto offenseDto = offenseService.isOffenseNeeded(offenseValidationDto);

    Truth.assertThat(offenseDto.reasonCode).isEqualTo(offense.getReasonCode());
  }

  @Test
  public void
      givenValidOffenseValidationDto_whenCheckingIfOffenseNeeded_thenWrongZoneOffenseIsReturned() {
    when(parkingSticker.validateParkingStickerAreaCode(offenseValidation.getParkingAreaCode()))
        .thenReturn(false);
    Offense offense = new Offense("mauvaise zone", "ZONE_01", 55);

    OffenseDto offenseDto = offenseService.isOffenseNeeded(offenseValidationDto);

    Truth.assertThat(offenseDto.reasonCode).isEqualTo(offense.getReasonCode());
  }

  @Test
  public void whenGettingAllOffenses_thenAllPossibleOffensesAreReturned() {
    ArrayList<Offense> offenses = new ArrayList<>();
    offenses.add(new Offense("mauvaise zone", "ZONE_01", 55));
    offenses.add(new Offense("vignette pas admissible pour la journée", "VIG_01", 22));
    offenses.add(new Offense("vignette invalide", "VIG_02", 45));
    offenses.add(new Offense("temps dépassé", "TEMPS_01", 47));
    offenses.add(new Offense("stationnement réservé pour voiture électrique", "ZONE_02", 55));
    offenses.add(new Offense("pas de vignette", "VIG_03", 55));
    offenses.add(new Offense("la vignette et la plaque ne sont pas associées", "VIG_04", 42));
    offenses.add(
        new Offense("stationnement réservé pour voiture électrique branchée", "ZONE_03", 55));

    List<OffenseDto> offenseDtoList = offenseService.getAllOffenses();

    for (int i = 0; i < offenses.size(); i++) {
      Truth.assertThat(offenseDtoList.get(i).reasonCode).isEqualTo(offenses.get(i).getReasonCode());
    }
  }
}
