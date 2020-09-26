package ca.ulaval.glo4003.infrastructure.parking;

import static ca.ulaval.glo4003.domain.parking.helpers.ParkingStickerBuilder.aParkingSticker;

import ca.ulaval.glo4003.domain.parking.ParkingSticker;
import ca.ulaval.glo4003.domain.parking.ParkingStickerRepository;
import ca.ulaval.glo4003.domain.parking.exception.NotFoundParkingStickerCodeException;
import com.google.common.truth.Truth;
import org.junit.Before;
import org.junit.Test;

public class ParkingStickerRepositoryInMemoryTest {
  private ParkingSticker parkingSticker;

  private ParkingStickerRepository parkingStickerRepository;

  @Before
  public void setUp() {
    parkingStickerRepository = new ParkingStickerRepositoryInMemory();
    parkingSticker = aParkingSticker().build();
  }

  @Test
  public void whenSavingParkingSticker_thenParkingStickerCanBeFound() {
    parkingStickerRepository.save(parkingSticker);

    ParkingSticker foundParkingSticker =
        parkingStickerRepository.findByCode(parkingSticker.getCode());

    Truth.assertThat(foundParkingSticker).isSameInstanceAs(parkingSticker);
  }

  @Test(expected = NotFoundParkingStickerCodeException.class)
  public void
      givenNonExistentParkingStickerCode_whenGettingParkingSticker_thenThrowNotFoundParkingStickerCodeException() {
    parkingStickerRepository.findByCode(parkingSticker.getCode());
  }
}
