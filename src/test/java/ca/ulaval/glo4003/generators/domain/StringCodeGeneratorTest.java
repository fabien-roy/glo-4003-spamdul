package ca.ulaval.glo4003.generators.domain;

import static com.google.common.truth.Truth.assertThat;

import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class StringCodeGeneratorTest {
  private static final String KEYWORD = "TEST";

  private StringCodeGenerator stringCodeGenerator;

  @Before
  public void setUp() {
    stringCodeGenerator = new StringCodeGenerator();
  }

  @Test
  public void whenGenerating_thenReturnDifferentCodes() {
    String firstCode = stringCodeGenerator.generate(KEYWORD);
    String secondCode = stringCodeGenerator.generate(KEYWORD);

    assertThat(firstCode).isNotEqualTo(secondCode);
  }

  @Test
  public void whenGenerating_thenReturnCodeWithKeyword() {
    String code = stringCodeGenerator.generate(KEYWORD);

    assertThat(code).contains(KEYWORD);
  }

  @Test
  public void whenGenerating_thenReturnTwoPartedCode() {
    String separator = "-";

    String code = stringCodeGenerator.generate(KEYWORD);
    List<String> codeParts = Arrays.asList(code.split(separator));

    assertThat(codeParts).hasSize(2);
  }

  @Test
  public void whenGenerating_thenReturnCodeWithSixAlphanumericalCharactersAsSecondPart() {
    String separator = "-";
    String alphanumericalRegex = "[A-Z0-9]+";

    String code = stringCodeGenerator.generate(KEYWORD);
    String secondPart = code.split(separator)[1];

    assertThat(secondPart).hasLength(6);
    assertThat(secondPart).matches(alphanumericalRegex);
  }
}
