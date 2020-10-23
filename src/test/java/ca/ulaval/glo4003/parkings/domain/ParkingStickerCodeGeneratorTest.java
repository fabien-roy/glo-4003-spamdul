package ca.ulaval.glo4003.parkings.domain;

import static com.google.common.truth.Truth.assertThat;

import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

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

    assertThat(firstCode).isNotEqualTo(secondCode);
  }

  @Test
  public void whenGenerating_thenReturnCodeWithParkKeyword() {
    String passKeyword = "PARK";

    ParkingStickerCode code = parkingStickerCodeGenerator.generate();

    assertThat(code.toString()).contains(passKeyword);
  }

  @Test
  public void whenGenerating_thenReturnTwoPartedCode() {
    String separator = "-";

    ParkingStickerCode code = parkingStickerCodeGenerator.generate();
    List<String> codeParts = Arrays.asList(code.toString().split(separator));

    assertThat(codeParts).hasSize(2);
  }

  @Test
  public void whenGenerating_thenReturnCodeWithSixAlphanumericalCharactersAsSecondPart() {
    String separator = "-";
    String alphanumericalRegex = "[A-Z0-9]+";

    ParkingStickerCode code = parkingStickerCodeGenerator.generate();
    String secondPart = code.toString().split(separator)[1];

    assertThat(secondPart).hasLength(6);
    assertThat(secondPart).matches(alphanumericalRegex);
  }
}
