package ca.ulaval.glo4003.offenses.services.converters;

import static ca.ulaval.glo4003.offenses.helpers.OffenseValidationDtoBuilder.anOffenseValidationDto;
import static ca.ulaval.glo4003.parkings.helpers.ParkingAreaMother.createParkingAreaCode;
import static ca.ulaval.glo4003.parkings.helpers.ParkingStickerMother.createParkingStickerCode;
import static ca.ulaval.glo4003.times.helpers.TimeOfDayMother.createTimeOfDay;
import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.when;

import ca.ulaval.glo4003.offenses.domain.OffenseValidation;
import ca.ulaval.glo4003.offenses.services.dto.OffenseValidationDto;
import ca.ulaval.glo4003.parkings.domain.ParkingAreaCode;
import ca.ulaval.glo4003.parkings.domain.ParkingStickerCode;
import ca.ulaval.glo4003.parkings.services.assemblers.ParkingAreaCodeAssembler;
import ca.ulaval.glo4003.parkings.services.assemblers.ParkingStickerCodeAssembler;
import ca.ulaval.glo4003.times.domain.TimeOfDay;
import ca.ulaval.glo4003.times.services.converters.TimeOfDayConverter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class OffenseValidationConverterTest {
  @Mock private ParkingStickerCodeAssembler parkingStickerCodeAssembler;
  @Mock private ParkingAreaCodeAssembler parkingAreaCodeAssembler;
  @Mock private TimeOfDayConverter timeOfDayConverter;

  private OffenseValidationConverter offenseValidationConverter;

  private static final ParkingStickerCode PARKING_STICKER_CODE = createParkingStickerCode();
  private static final ParkingAreaCode PARKING_AREA_CODE = createParkingAreaCode();
  private static final TimeOfDay TIME_OF_DAY = createTimeOfDay();
  private OffenseValidationDto offenseValidationDto;

  @Before
  public void setUp() {
    offenseValidationConverter =
        new OffenseValidationConverter(
            parkingStickerCodeAssembler, parkingAreaCodeAssembler, timeOfDayConverter);

    offenseValidationDto = anOffenseValidationDto().build();

    when(parkingStickerCodeAssembler.assemble(offenseValidationDto.parkingStickerCode))
        .thenReturn(PARKING_STICKER_CODE);
    when(parkingAreaCodeAssembler.assemble(offenseValidationDto.parkingArea))
        .thenReturn(PARKING_AREA_CODE);
    when(timeOfDayConverter.convert(offenseValidationDto.timeOfDay)).thenReturn(TIME_OF_DAY);
  }

  @Test
  public void whenConverting_thenOffenseValidationWithParkingStickerCodeIsReturned() {
    OffenseValidation offenseValidation = offenseValidationConverter.convert(offenseValidationDto);

    assertThat(offenseValidation.getParkingStickerCode()).isEqualTo(PARKING_STICKER_CODE);
  }

  @Test
  public void whenConverting_thenOffenseValidationWithParkingAreaCodeIsReturned() {
    OffenseValidation offenseValidation = offenseValidationConverter.convert(offenseValidationDto);

    assertThat(offenseValidation.getParkingAreaCode()).isEqualTo(PARKING_AREA_CODE);
  }

  @Test
  public void whenConverting_thenOffenseValidationWithTimeOfDayIsReturned() {
    OffenseValidation offenseValidation = offenseValidationConverter.convert(offenseValidationDto);

    assertThat(offenseValidation.getTimeOfDay()).isEqualTo(TIME_OF_DAY);
  }
}
