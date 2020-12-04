package ca.ulaval.glo4003.parkings.services.assemblers;

import static ca.ulaval.glo4003.parkings.helpers.ParkingStickerMother.createParkingStickerCode;

import ca.ulaval.glo4003.parkings.domain.ParkingStickerCode;
import ca.ulaval.glo4003.parkings.exceptions.InvalidParkingStickerCodeException;
import ca.ulaval.glo4003.parkings.services.dto.ParkingStickerCodeDto;
import com.google.common.truth.Truth;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ParkingStickerCodeAssemblerTest {
  private ParkingStickerCodeAssembler parkingStickerCodeAssembler;

  private final ParkingStickerCode parkingStickerCode = createParkingStickerCode();

  @Before
  public void setUp() {
    parkingStickerCodeAssembler = new ParkingStickerCodeAssembler();
  }

  @Test
  public void whenAssemblingFromString_thenReturnParkingCode() {
    ParkingStickerCode assembledParkingStickerCode =
        parkingStickerCodeAssembler.assemble(parkingStickerCode.toString());

    Truth.assertThat(assembledParkingStickerCode).isEqualTo(parkingStickerCode);
  }

  @Test(expected = InvalidParkingStickerCodeException.class)
  public void
      givenNullParkingStickerCode_whenAssemblingFromString_thenThrowInvalidParkingStickerCode() {
    parkingStickerCodeAssembler.assemble((String) null);
  }

  @Test
  public void whenAssemblingDto_thenReturnParkingCodeDto() {
    ParkingStickerCodeDto parkingStickerCodeDto =
        parkingStickerCodeAssembler.assemble(parkingStickerCode);

    Truth.assertThat(parkingStickerCodeDto.parkingStickerCode)
        .isEqualTo(parkingStickerCode.toString());
  }
}
