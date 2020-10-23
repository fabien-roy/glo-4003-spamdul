package ca.ulaval.glo4003.accesspasses.domain;

import static com.google.common.truth.Truth.assertThat;

import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class AccessPassCodeGeneratorTest {
  private AccessPassCodeGenerator accessPassCodeGenerator;

  @Before
  public void setUp() {
    accessPassCodeGenerator = new AccessPassCodeGenerator();
  }

  @Test
  public void whenGenerating_thenReturnDifferentCodes() {
    AccessPassCode firstCode = accessPassCodeGenerator.generate();
    AccessPassCode secondCode = accessPassCodeGenerator.generate();

    assertThat(firstCode).isNotEqualTo(secondCode);
  }

  @Test
  public void whenGenerating_thenReturnCodeWithPassKeyword() {
    String passKeyword = "PASS";

    AccessPassCode code = accessPassCodeGenerator.generate();

    assertThat(code.toString()).contains(passKeyword);
  }

  @Test
  public void whenGenerating_thenReturnTwoPartedCode() {
    String separator = "-";

    AccessPassCode code = accessPassCodeGenerator.generate();
    List<String> codeParts = Arrays.asList(code.toString().split(separator));

    assertThat(codeParts).hasSize(2);
  }

  @Test
  public void whenGenerating_thenReturnCodeWithSixAlphanumericalCharactersAsSecondPart() {
    String separator = "-";
    String alphanumericalRegex = "[A-Z0-9]+";

    AccessPassCode code = accessPassCodeGenerator.generate();
    String secondPart = code.toString().split(separator)[1];

    assertThat(secondPart).hasLength(6);
    assertThat(secondPart).matches(alphanumericalRegex);
  }
}
