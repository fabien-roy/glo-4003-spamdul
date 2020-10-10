package ca.ulaval.glo4003.accessPass.domain;

import ca.ulaval.glo4003.access.domain.AccessPassCode;
import ca.ulaval.glo4003.access.domain.AccessPassCodeGenerator;
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
