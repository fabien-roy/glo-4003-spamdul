package ca.ulaval.glo4003.domain.offense;

import static ca.ulaval.glo4003.domain.offense.helpers.OffenseValidationBuilder.anOffenseValidation;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

import ca.ulaval.glo4003.api.offense.dto.OffenseValidationDto;
import ca.ulaval.glo4003.domain.parking.ParkingSticker;
import ca.ulaval.glo4003.domain.parking.ParkingStickerRepository;
import ca.ulaval.glo4003.domain.parking.exception.NotFoundParkingStickerException;
import java.util.ArrayList;
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

    offenseService.isOffenseNeeded(offenseValidationDto);

    Mockito.verify(offenseAssembler).assemble(eq(offense));
  }

  @Test
  public void givenValidOffenseValidationDto_whenCheckingIfOffenseNeeded_thenNoOffenseIsReturned() {
    when(parkingSticker.validateParkingStickerAreaCode(offenseValidation.getParkingAreaCode()))
        .thenReturn(true);
    Offense offense = new Offense("Aucune infraction signalée", "000", 0);

    offenseService.isOffenseNeeded(offenseValidationDto);

    Mockito.verify(offenseAssembler).assemble(eq(offense));
  }

  @Test
  public void
      givenValidOffenseValidationDto_whenCheckingIfOffenseNeeded_thenWrongZoneOffenseIsReturned() {
    when(parkingSticker.validateParkingStickerAreaCode(offenseValidation.getParkingAreaCode()))
        .thenReturn(false);
    Offense offense = new Offense("mauvaise zone", "ZONE_01", 55);

    offenseService.isOffenseNeeded(offenseValidationDto);

    Mockito.verify(offenseAssembler).assemble(eq(offense));
  }

  @Test
  public void whenGettingAllOffenses_thenAllOffensesAreReturned() {
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

    offenseService.getAllOffenses();

    Mockito.verify(offenseAssembler).assembleMany(offenses);
  }
}
