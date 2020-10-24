package ca.ulaval.glo4003.initiatives.domain;

import com.google.common.truth.Truth;
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

    Truth.assertThat(firstCode).isNotEqualTo(secondCode);
  }
}
