package ca.ulaval.glo4003.parkings.domain;

import static ca.ulaval.glo4003.parkings.helpers.ParkingStickerBuilder.aParkingSticker;
import static ca.ulaval.glo4003.parkings.helpers.ParkingStickerMother.createParkingStickerCode;

import com.google.common.truth.Truth;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ParkingStickerFactoryTest {
  private static final ParkingStickerCode PARKING_STICKER_CODE = createParkingStickerCode();

  @Mock private ParkingStickerCodeGenerator parkingStickerCodeGenerator;

  private ParkingSticker parkingSticker = aParkingSticker().build();

  private ParkingStickerFactory parkingStickerFactory;

  @Before
  public void setUp() {
    parkingStickerFactory = new ParkingStickerFactory(parkingStickerCodeGenerator);

    BDDMockito.given(parkingStickerCodeGenerator.generate()).willReturn(PARKING_STICKER_CODE);
  }

  @Test
  public void whenCreating_thenGeneratorCode() {
    ParkingSticker createdParkingSticker = parkingStickerFactory.create(parkingSticker);

    Truth.assertThat(createdParkingSticker.getCode()).isSameInstanceAs(PARKING_STICKER_CODE);
  }
}
