package ca.ulaval.glo4003.accesspasses.domain;

import com.google.common.truth.Truth;
import org.junit.Test;

public class AccessPassCodeGeneratorTest {
  private AccessPassCodeGenerator accessPassCodeGenerator = new AccessPassCodeGenerator();

  @Test
  public void whenGenerating_thenReturnDifferentCodes() {
    AccessPassCode firstCode = accessPassCodeGenerator.generate();
    AccessPassCode secondCode = accessPassCodeGenerator.generate();

    Truth.assertThat(firstCode).isNotEqualTo(secondCode);
  }
}
