package ca.ulaval.glo4003.parkings.infrastructure;

import static ca.ulaval.glo4003.parkings.helpers.ParkingStickerBuilder.aParkingSticker;

import ca.ulaval.glo4003.parkings.domain.ParkingSticker;
import ca.ulaval.glo4003.parkings.domain.ParkingStickerRepository;
import ca.ulaval.glo4003.parkings.exceptions.NotFoundParkingStickerException;
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

    ParkingSticker foundParkingSticker = parkingStickerRepository.get(parkingSticker.getCode());

    Truth.assertThat(foundParkingSticker).isSameInstanceAs(parkingSticker);
  }

  @Test(expected = NotFoundParkingStickerException.class)
  public void
      givenNonExistentParkingStickerCode_whenGettingParkingSticker_thenThrowNotFoundParkingStickerCodeException() {
    parkingStickerRepository.get(parkingSticker.getCode());
  }
}
