package ca.ulaval.glo4003.domain.parking;

import static ca.ulaval.glo4003.domain.parking.helpers.ParkingStickerMother.createParkingStickerCode;

import ca.ulaval.glo4003.api.parking.dto.ParkingStickerCodeDto;
import com.google.common.truth.Truth;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ParkingStickerCodeAssemblerTest {
  private static final ParkingStickerCode PARKING_STICKER_CODE = createParkingStickerCode();

  private ParkingStickerCodeAssembler parkingStickerCodeAssembler;

  @Before
  public void setUp() {
    parkingStickerCodeAssembler = new ParkingStickerCodeAssembler();
  }

  @Test
  public void whenAssembling_thenReturnParkingCode() {
    ParkingStickerCodeDto parkingStickerCodeDto =
        parkingStickerCodeAssembler.assemble(PARKING_STICKER_CODE);

    Truth.assertThat(parkingStickerCodeDto.code).isEqualTo(PARKING_STICKER_CODE.toString());
  }
}
