package ca.ulaval.glo4003.initiatives.domain;

import static ca.ulaval.glo4003.initiatives.helpers.InitiativeMother.createInitiativeCode;
import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.when;

import ca.ulaval.glo4003.interfaces.domain.StringCodeGenerator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class InitiativeCodeGeneratorTest {
  @Mock private StringCodeGenerator stringCodeGenerator;

  private InitiativeCodeGenerator initiativeCodeGenerator;

  private final InitiativeCode initiativeCode = createInitiativeCode();

  @Before
  public void setUp() {
    initiativeCodeGenerator = new InitiativeCodeGenerator(stringCodeGenerator);
  }

  @Test
  public void givenInitKeyword_whenGenerating_thenUseCodeFromStringCodeGenerator() {
    when(stringCodeGenerator.generate("INIT")).thenReturn(initiativeCode.toString());

    InitiativeCode code = initiativeCodeGenerator.generate();

    assertThat(code).isEqualTo(initiativeCode);
  }
}
