package ca.ulaval.glo4003.domain.parking;

import static ca.ulaval.glo4003.domain.parking.helpers.ParkingStickerMother.createParkingStickerCode;

import ca.ulaval.glo4003.api.parking.dto.ParkingStickerCodeDto;
import ca.ulaval.glo4003.domain.parking.exception.InvalidParkingStickerCodeException;
import com.google.common.truth.Truth;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ParkingStickerCodeAssemblerTest {
  private static final ParkingStickerCode PARKING_STICKER_CODE = createParkingStickerCode();

  private ParkingStickerCodeAssembler parkingStickerCodeAssembler;

  private ParkingStickerCodeDto parkingStickerCodeDto;

  @Before
  public void setUp() {
    parkingStickerCodeAssembler = new ParkingStickerCodeAssembler();

    parkingStickerCodeDto = new ParkingStickerCodeDto();
    parkingStickerCodeDto.parkingStickerCode = PARKING_STICKER_CODE.toString();
  }

  @Test
  public void whenAssembling_thenReturnParkingCode() {
    ParkingStickerCode parkingStickerCode =
        parkingStickerCodeAssembler.assemble(parkingStickerCodeDto);

    Truth.assertThat(parkingStickerCode.toString())
        .isEqualTo(parkingStickerCodeDto.parkingStickerCode);
  }

  @Test
  public void whenAssemblingFromString_thenReturnParkingCode() {
    ParkingStickerCode parkingStickerCode =
        parkingStickerCodeAssembler.assemble(PARKING_STICKER_CODE.toString());

    Truth.assertThat(parkingStickerCode.toString()).isEqualTo(PARKING_STICKER_CODE.toString());
  }

  @Test(expected = InvalidParkingStickerCodeException.class)
  public void
      givenNullParkingStickerCode_whenAssemblingFromString_thenThrowInvalidParkingStickerCode() {
    ParkingStickerCode parkingStickerCode = parkingStickerCodeAssembler.assemble((String) null);
  }

  @Test
  public void whenAssemblingDto_thenReturnParkingCodeDto() {
    ParkingStickerCodeDto parkingStickerCodeDto =
        parkingStickerCodeAssembler.assemble(PARKING_STICKER_CODE);

    Truth.assertThat(parkingStickerCodeDto.parkingStickerCode)
        .isEqualTo(PARKING_STICKER_CODE.toString());
  }

  @Test(expected = InvalidParkingStickerCodeException.class)
  public void givenNullParkingStickerCode_whenAssemblingDto_thenThrowInvalidParkingStickerCode() {
    ParkingStickerCodeDto parkingStickerCodeDto = new ParkingStickerCodeDto();
    parkingStickerCodeDto.parkingStickerCode = null;

    parkingStickerCodeAssembler.assemble(parkingStickerCodeDto);
  }
}
