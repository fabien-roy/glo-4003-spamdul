package ca.ulaval.glo4003.accesspasses.domain;

import static ca.ulaval.glo4003.accesspasses.helpers.AccessPassMother.createAccessPassCode;
import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.when;

import ca.ulaval.glo4003.interfaces.domain.StringCodeGenerator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AccessPassCodeGeneratorTest {
  @Mock private StringCodeGenerator stringCodeGenerator;

  private AccessPassCodeGenerator accessPassCodeGenerator;

  private final AccessPassCode accessPassCode = createAccessPassCode();

  @Before
  public void setUp() {
    accessPassCodeGenerator = new AccessPassCodeGenerator(stringCodeGenerator);
  }

  @Test
  public void givenPassKeyword_whenGenerating_thenUseCodeFromStringCodeGenerator() {
    when(stringCodeGenerator.generate("PASS")).thenReturn(accessPassCode.toString());

    AccessPassCode code = accessPassCodeGenerator.generate();

    assertThat(code).isEqualTo(accessPassCode);
  }
}
