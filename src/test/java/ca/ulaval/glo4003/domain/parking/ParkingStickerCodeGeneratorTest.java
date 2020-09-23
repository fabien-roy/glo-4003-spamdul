package ca.ulaval.glo4003.domain.parking;

import com.google.common.truth.Truth;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ParkingStickerCodeGeneratorTest {

  private ParkingStickerCodeGenerator parkingStickerCodeGenerator;

  @Before
  public void setUp() {
    parkingStickerCodeGenerator = new ParkingStickerCodeGenerator();
  }

  @Test
  public void whenGenerating_thenReturnDifferentCodes() {
    ParkingStickerCode firstCode = parkingStickerCodeGenerator.generate();
    ParkingStickerCode secondCode = parkingStickerCodeGenerator.generate();

    Truth.assertThat(firstCode).isNotEqualTo(secondCode);
  }
}
