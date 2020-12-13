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
  @Mock private ParkingStickerCodeGenerator parkingStickerCodeGenerator;

  private ParkingStickerFactory parkingStickerFactory;

  private final ParkingStickerCode parkingStickerCode = createParkingStickerCode();
  private final ParkingSticker parkingSticker = aParkingSticker().build();

  @Before
  public void setUp() {
    parkingStickerFactory = new ParkingStickerFactory(parkingStickerCodeGenerator);

    BDDMockito.given(parkingStickerCodeGenerator.generate()).willReturn(parkingStickerCode);
  }

  @Test
  public void whenCreating_thenGeneratorCode() {
    ParkingSticker createdParkingSticker = parkingStickerFactory.create(parkingSticker);

    Truth.assertThat(createdParkingSticker.getCode()).isSameInstanceAs(parkingStickerCode);
  }
}
