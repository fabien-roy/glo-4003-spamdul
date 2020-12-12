package ca.ulaval.glo4003.parkings.domain;

import static ca.ulaval.glo4003.parkings.helpers.ParkingStickerMother.createParkingStickerCode;
import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.when;

import ca.ulaval.glo4003.generators.domain.StringCodeGenerator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ParkingStickerCodeGeneratorTest {
  @Mock private StringCodeGenerator stringCodeGenerator;

  private ParkingStickerCodeGenerator parkingStickerCodeGenerator;

  private final ParkingStickerCode parkingStickerCode = createParkingStickerCode();

  @Before
  public void setUp() {
    parkingStickerCodeGenerator = new ParkingStickerCodeGenerator(stringCodeGenerator);
  }

  @Test
  public void givenParkKeyword_whenGenerating_thenUseCodeFromStringCodeGenerator() {
    when(stringCodeGenerator.generate("PARK")).thenReturn(parkingStickerCode.toString());

    ParkingStickerCode code = parkingStickerCodeGenerator.generate();

    assertThat(code).isEqualTo(parkingStickerCode);
  }
}
