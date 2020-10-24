package ca.ulaval.glo4003.initiatives.domain;

import static com.google.common.truth.Truth.assertThat;

import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class InitiativeCodeGeneratorTest {
  private InitiativeCodeGenerator initiativeCodeGenerator;

  @Before
  public void setUp() {
    initiativeCodeGenerator = new InitiativeCodeGenerator();
  }

  @Test
  public void whenGenerating_thenReturnDifferentCodes() {
    InitiativeCode firstCode = initiativeCodeGenerator.generate();
    InitiativeCode secondCode = initiativeCodeGenerator.generate();

    assertThat(firstCode).isNotEqualTo(secondCode);
  }

  @Test
  public void whenGenerating_thenReturnCodeWithInitKeyword() {
    String initKeyword = "INIT";

    InitiativeCode code = initiativeCodeGenerator.generate();

    assertThat(code.toString()).contains(initKeyword);
  }

  @Test
  public void whenGenerating_thenReturnTwoPartedCode() {
    String separator = "-";

    InitiativeCode code = initiativeCodeGenerator.generate();
    List<String> codeParts = Arrays.asList(code.toString().split(separator));

    assertThat(codeParts).hasSize(2);
  }

  @Test
  public void whenGenerating_thenReturnCodeWithSixAlphanumericalCharactersAsSecondPart() {
    String separator = "-";
    String alphanumericalRegex = "[A-Z0-9]+";

    InitiativeCode code = initiativeCodeGenerator.generate();
    String secondPart = code.toString().split(separator)[1];

    assertThat(secondPart).hasLength(6);
    assertThat(secondPart).matches(alphanumericalRegex);
  }
}
