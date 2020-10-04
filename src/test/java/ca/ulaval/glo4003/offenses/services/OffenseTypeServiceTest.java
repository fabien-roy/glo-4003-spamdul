package ca.ulaval.glo4003.offenses.services;

import static ca.ulaval.glo4003.offenses.helpers.OffenseTypeBuilder.anOffenseType;
import static ca.ulaval.glo4003.offenses.helpers.OffenseTypeDtoBuilder.anOffenseTypeDto;
import static ca.ulaval.glo4003.offenses.helpers.OffenseValidationBuilder.anOffenseValidation;
import static ca.ulaval.glo4003.offenses.helpers.OffenseValidationDtoBuilder.anOffenseValidationDto;
import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

import ca.ulaval.glo4003.offenses.api.dto.OffenseTypeDto;
import ca.ulaval.glo4003.offenses.api.dto.OffenseValidationDto;
import ca.ulaval.glo4003.offenses.assemblers.OffenseTypeAssembler;
import ca.ulaval.glo4003.offenses.assemblers.OffenseValidationAssembler;
import ca.ulaval.glo4003.offenses.domain.*;
import ca.ulaval.glo4003.parkings.domain.ParkingSticker;
import ca.ulaval.glo4003.parkings.domain.ParkingStickerRepository;
import ca.ulaval.glo4003.parkings.exceptions.NotFoundParkingStickerException;
import com.google.common.truth.Truth;
import java.util.Collections;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

// TODO : Some tests here smell bad
@RunWith(MockitoJUnitRunner.class)
public class OffenseTypeServiceTest {
  @Mock private ParkingStickerRepository parkingStickerRepository;
  @Mock private OffenseValidationAssembler offenseValidationAssembler;
  @Mock private OffenseTypeAssembler offenseTypeAssembler;
  @Mock private OffenseTypeRepository offenseTypeRepository;
  @Mock ParkingSticker parkingSticker;

  private OffenseTypeService offenseTypeService;

  private final OffenseType offenseType = anOffenseType().build();
  private final List<OffenseType> offenseTypes = Collections.singletonList(offenseType);
  private final OffenseTypeDto offenseTypeDto = anOffenseTypeDto().build();
  private final List<OffenseTypeDto> offenseTypeDtos = Collections.singletonList(offenseTypeDto);
  private final OffenseValidation offenseValidation = anOffenseValidation().build();
  private final OffenseValidationDto offenseValidationDto = anOffenseValidationDto().build();
  private final OffenseType zone01OffenseType =
      anOffenseType().withCode(OffenseCodes.ZONE_01).build();
  private final OffenseType vig01OffenseType =
      anOffenseType().withCode(OffenseCodes.VIG_01).build();
  private final OffenseType vig02OffenseType =
      anOffenseType().withCode(OffenseCodes.VIG_02).build();

  @Before
  public void setUp() {
    offenseTypeService =
        new OffenseTypeService(
            parkingStickerRepository,
            offenseValidationAssembler,
            offenseTypeAssembler,
            offenseTypeRepository);

    when(offenseTypeRepository.getAll()).thenReturn(offenseTypes);
    when(offenseTypeAssembler.assembleMany(offenseTypes)).thenReturn(offenseTypeDtos);

    when(offenseValidationAssembler.assemble(offenseValidationDto)).thenReturn(offenseValidation);
    when(parkingSticker.validateParkingStickerAreaCode(offenseValidation.getParkingAreaCode()))
        .thenReturn(false);
    when(parkingStickerRepository.findByCode(offenseValidation.getParkingStickerCode()))
        .thenReturn(parkingSticker);
    when(offenseTypeRepository.findByCode(OffenseCodes.ZONE_01)).thenReturn(zone01OffenseType);
    when(offenseTypeRepository.findByCode(OffenseCodes.VIG_01)).thenReturn(vig01OffenseType);
    when(offenseTypeRepository.findByCode(OffenseCodes.VIG_02)).thenReturn(vig02OffenseType);
  }

  @Test
  public void whenGettingAllOffenseTypes_thenReturnAllOffenseTypes() {
    List<OffenseTypeDto> receivedOffenseTypeDtos = offenseTypeService.getAllOffenseTypes();

    Truth.assertThat(receivedOffenseTypeDtos).isSameInstanceAs(offenseTypeDtos);
  }

  @Test
  public void whenCheckingIfOffenseNeeded_thenParkingStickerRepositoryIsCalled() {
    offenseTypeService.validateOffense(offenseValidationDto);

    Mockito.verify(parkingStickerRepository)
        .findByCode(eq(offenseValidation.getParkingStickerCode()));
  }

  @Test
  public void
      givenValidOffenseValidationDto_whenValidatingOffense_thenInvalidStickerOffenseIsReturned() {
    when(parkingStickerRepository.findByCode(offenseValidation.getParkingStickerCode()))
        .thenThrow(new NotFoundParkingStickerException());

    offenseTypeService.validateOffense(offenseValidationDto);

    Mockito.verify(offenseTypeAssembler).assemble(vig02OffenseType);
  }

  @Test
  public void givenValidOffenseValidationDto_whenValidatingOffense_thenNoOffenseIsReturned() {
    when(parkingSticker.validateParkingStickerAreaCode(offenseValidation.getParkingAreaCode()))
        .thenReturn(true);

    offenseTypeService.validateOffense(offenseValidationDto);

    ArgumentCaptor<OffenseType> argumentCaptor = ArgumentCaptor.forClass(OffenseType.class);
    Mockito.verify(offenseTypeAssembler).assemble(argumentCaptor.capture());
    OffenseType capturedArgument = argumentCaptor.getValue();
    assertThat(capturedArgument.getCode()).isEqualTo(OffenseCodes.NONE);
  }

  @Test
  public void
      givenValidOffenseValidationDto_whenValidatingOffense_thenWrongZoneOffenseIsReturned() {
    when(parkingSticker.validateParkingStickerAreaCode(offenseValidation.getParkingAreaCode()))
        .thenReturn(false);

    offenseTypeService.validateOffense(offenseValidationDto);

    Mockito.verify(offenseTypeAssembler).assemble(zone01OffenseType);
  }
}
