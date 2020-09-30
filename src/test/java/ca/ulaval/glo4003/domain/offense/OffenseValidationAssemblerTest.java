package ca.ulaval.glo4003.domain.offense;


import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import ca.ulaval.glo4003.api.offense.dto.OffenseValidationDto;
import ca.ulaval.glo4003.domain.parking.ParkingAreaCode;
import ca.ulaval.glo4003.domain.parking.ParkingAreaCodeAssembler;
import ca.ulaval.glo4003.domain.parking.ParkingStickerCode;
import ca.ulaval.glo4003.domain.parking.ParkingStickerCodeAssembler;
import com.google.common.truth.Truth;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class OffenseValidationAssemblerTest {
  private ParkingStickerCodeAssembler parkingStickerCodeAssembler;
  private ParkingAreaCodeAssembler parkingAreaCodeAssembler;
  private ParkingStickerCode parkingStickerCode;
  private OffenseValidationDto offenseValidationDto;
  private OffenseValidationAssembler offenseValidationAssembler;

  @Before
  public void setUp() {
    offenseValidationAssembler =
        new OffenseValidationAssembler(parkingStickerCodeAssembler, parkingAreaCodeAssembler);

    offenseValidationDto = new OffenseValidationDto();

    when(parkingStickerCodeAssembler.assemble(offenseValidationDto.parkingStickerCode))
        .thenReturn(parkingStickerCode);
    when(parkingAreaCodeAssembler.assemble(offenseValidationDto.parkingArea))
        .thenReturn(new ParkingAreaCode("2"));
  }

  @Test
  public void whenAssembling_thenOffenseValidationWithParkingStickerCodeIsReturned() {
    OffenseValidation offenseValidation = offenseValidationAssembler.assemble(offenseValidationDto);

    Truth.assertThat(offenseValidation.getParkingStickerCode()).isEqualTo(parkingStickerCode);
  }
}
