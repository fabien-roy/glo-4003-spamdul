package ca.ulaval.glo4003.offenses.services;

import static ca.ulaval.glo4003.offenses.helpers.OffenseTypeBuilder.anOffenseType;
import static ca.ulaval.glo4003.offenses.helpers.OffenseTypeDtoBuilder.anOffenseTypeDto;
import static ca.ulaval.glo4003.offenses.helpers.OffenseValidationBuilder.anOffenseValidation;
import static ca.ulaval.glo4003.offenses.helpers.OffenseValidationDtoBuilder.anOffenseValidationDto;
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
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

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
  private final OffenseType wrongZoneOffenseType =
      anOffenseType().withCode(OffenseCodes.ZONE_01).build();
  private final OffenseType invalidStickerOffenseType =
      anOffenseType().withCode(OffenseCodes.VIG_02).build();
  private final OffenseTypeDto wrongZoneOffenseTypeDto = anOffenseTypeDto().build();
  private final OffenseTypeDto invalidStickerOffenseTypeDto = anOffenseTypeDto().build();

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
    when(offenseTypeRepository.findByCode(OffenseCodes.ZONE_01)).thenReturn(wrongZoneOffenseType);
    when(offenseTypeRepository.findByCode(OffenseCodes.VIG_02))
        .thenReturn(invalidStickerOffenseType);
    when(offenseTypeAssembler.assemble(wrongZoneOffenseType)).thenReturn(wrongZoneOffenseTypeDto);
    when(offenseTypeAssembler.assemble(invalidStickerOffenseType))
        .thenReturn(invalidStickerOffenseTypeDto);
  }

  @Test
  public void whenGettingAllOffenseTypes_thenReturnAllOffenseTypes() {
    List<OffenseTypeDto> receivedOffenseTypeDtos = offenseTypeService.getAllOffenseTypes();

    Truth.assertThat(receivedOffenseTypeDtos).isSameInstanceAs(offenseTypeDtos);
  }

  @Test
  public void givenValidOffenseValidationDto_whenValidatingOffense_thenNoOffenseIsReturned() {
    when(parkingSticker.validateParkingStickerAreaCode(offenseValidation.getParkingAreaCode()))
        .thenReturn(true);

    List<OffenseTypeDto> offenseTypeDtos = offenseTypeService.validateOffense(offenseValidationDto);

    Truth.assertThat(offenseTypeDtos).isEmpty();
  }

  @Test
  public void
      givenValidOffenseValidationDto_whenValidatingOffense_thenInvalidStickerOffenseIsReturned() {
    when(parkingStickerRepository.findByCode(offenseValidation.getParkingStickerCode()))
        .thenThrow(new NotFoundParkingStickerException());

    List<OffenseTypeDto> offenseTypeDtos = offenseTypeService.validateOffense(offenseValidationDto);

    Truth.assertThat(offenseTypeDtos).contains(invalidStickerOffenseTypeDto);
  }

  @Test
  public void
      givenValidOffenseValidationDto_whenValidatingOffense_thenWrongZoneOffenseIsReturned() {
    when(parkingSticker.validateParkingStickerAreaCode(offenseValidation.getParkingAreaCode()))
        .thenReturn(false);

    List<OffenseTypeDto> offenseTypeDtos = offenseTypeService.validateOffense(offenseValidationDto);

    Truth.assertThat(offenseTypeDtos).contains(wrongZoneOffenseTypeDto);
  }
}
