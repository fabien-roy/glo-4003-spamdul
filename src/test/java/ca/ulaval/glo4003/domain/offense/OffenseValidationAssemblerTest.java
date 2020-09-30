package ca.ulaval.glo4003.domain.offense;

import static ca.ulaval.glo4003.domain.time.helpers.TimeOfDayMother.createTimeOfDay;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import ca.ulaval.glo4003.api.offense.dto.OffenseValidationDto;
import ca.ulaval.glo4003.domain.parking.ParkingAreaCode;
import ca.ulaval.glo4003.domain.parking.ParkingAreaCodeAssembler;
import ca.ulaval.glo4003.domain.parking.ParkingStickerCode;
import ca.ulaval.glo4003.domain.parking.ParkingStickerCodeAssembler;
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
  private ParkingStickerCode parkingStickerCode;
  private OffenseValidationDto offenseValidationDto;
  private OffenseValidationAssembler offenseValidationAssembler;
  @Mock private TimeOfDayAssembler timeOfDayAssembler;

  @Before
  public void setUp() {
    offenseValidationAssembler =
        new OffenseValidationAssembler(
            parkingStickerCodeAssembler, parkingAreaCodeAssembler, timeOfDayAssembler);

    offenseValidationDto = new OffenseValidationDto();

    when(parkingStickerCodeAssembler.assemble(offenseValidationDto.parkingStickerCode))
        .thenReturn(parkingStickerCode);
    when(parkingAreaCodeAssembler.assemble(offenseValidationDto.parkingArea))
        .thenReturn(new ParkingAreaCode("2"));
    when(timeOfDayAssembler.assemble(offenseValidationDto.timeOfDay)).thenReturn(createTimeOfDay());
  }

  @Test
  public void whenAssembling_thenOffenseValidationWithParkingStickerCodeIsReturned() {
    OffenseValidation offenseValidation = offenseValidationAssembler.assemble(offenseValidationDto);

    Truth.assertThat(offenseValidation.getParkingStickerCode()).isEqualTo(parkingStickerCode);
  }
}
