package ca.ulaval.glo4003.domain.offense;

import static ca.ulaval.glo4003.api.offense.helpers.OffenseValidationDtoBuilder.anOffenseValidationDto;
import static ca.ulaval.glo4003.domain.parking.helpers.ParkingAreaMother.createParkingAreaCode;
import static ca.ulaval.glo4003.domain.parking.helpers.ParkingStickerMother.createParkingStickerCode;
import static ca.ulaval.glo4003.domain.time.helpers.TimeOfDayMother.createTimeOfDay;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import ca.ulaval.glo4003.api.offense.dto.OffenseValidationDto;
import ca.ulaval.glo4003.domain.parking.ParkingAreaCode;
import ca.ulaval.glo4003.domain.parking.ParkingAreaCodeAssembler;
import ca.ulaval.glo4003.domain.parking.ParkingStickerCode;
import ca.ulaval.glo4003.domain.parking.ParkingStickerCodeAssembler;
import ca.ulaval.glo4003.domain.time.TimeOfDay;
import ca.ulaval.glo4003.domain.time.TimeOfDayAssembler;
import com.google.common.truth.Truth;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class OffenseValidationAssemblerTest {
  @Mock private ParkingStickerCodeAssembler parkingStickerCodeAssembler;
  @Mock private ParkingAreaCodeAssembler parkingAreaCodeAssembler;
  private static final ParkingStickerCode PARKING_STICKER_CODE = createParkingStickerCode();
  private static final ParkingAreaCode PARKING_AREA_CODE = createParkingAreaCode();
  private static final TimeOfDay TIME_OF_DAY = createTimeOfDay();
  private OffenseValidationDto offenseValidationDto;
  private OffenseValidationAssembler offenseValidationAssembler;
  @Mock private TimeOfDayAssembler timeOfDayAssembler;

  @Before
  public void setUp() {
    offenseValidationAssembler =
        new OffenseValidationAssembler(
            parkingStickerCodeAssembler, parkingAreaCodeAssembler, timeOfDayAssembler);

    offenseValidationDto = anOffenseValidationDto().build();

    when(parkingStickerCodeAssembler.assemble(offenseValidationDto.parkingStickerCode))
        .thenReturn(PARKING_STICKER_CODE);
    when(parkingAreaCodeAssembler.assemble(offenseValidationDto.parkingArea))
        .thenReturn(PARKING_AREA_CODE);
    when(timeOfDayAssembler.assemble(offenseValidationDto.timeOfDay)).thenReturn(TIME_OF_DAY);
  }

  @Test
  public void whenAssembling_thenOffenseValidationWithParkingStickerCodeIsReturned() {
    OffenseValidation offenseValidation = offenseValidationAssembler.assemble(offenseValidationDto);

    Truth.assertThat(offenseValidation.getParkingStickerCode()).isEqualTo(PARKING_STICKER_CODE);
  }

  @Test
  public void whenAssembling_thenOffenseValidationWithParkingAreaCodeIsReturned() {
    OffenseValidation offenseValidation = offenseValidationAssembler.assemble(offenseValidationDto);

    Truth.assertThat(offenseValidation.getParkingAreaCode()).isEqualTo(PARKING_AREA_CODE);
  }

  @Test
  public void whenAssembling_thenOffenseValidationWithTimeOfDayIsReturned() {
    OffenseValidation offenseValidation = offenseValidationAssembler.assemble(offenseValidationDto);

    Truth.assertThat(offenseValidation.getTimeOfDay()).isEqualTo(TIME_OF_DAY);
  }
}
