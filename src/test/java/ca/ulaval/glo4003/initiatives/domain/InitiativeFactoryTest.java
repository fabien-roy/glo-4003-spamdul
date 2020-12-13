package ca.ulaval.glo4003.initiatives.domain;

import static ca.ulaval.glo4003.initiatives.helpers.InitiativeBuilder.anInitiative;
import static ca.ulaval.glo4003.initiatives.helpers.InitiativeMother.createInitiativeCode;
import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class InitiativeFactoryTest {
  @Mock private InitiativeCodeGenerator initiativeCodeGenerator;

  private InitiativeFactory initiativeFactory;

  private final InitiativeCode initiativeCode = createInitiativeCode();
  private final Initiative initiative = anInitiative().withInitiativeCode(null).build();

  @Before
  public void setUp() {
    initiativeFactory = new InitiativeFactory(initiativeCodeGenerator);

    when(initiativeCodeGenerator.generate()).thenReturn(initiativeCode);
  }

  @Test
  public void whenCreatingInitiative_thenReturnInitiativeWithCode() {
    Initiative createdInitiative = initiativeFactory.create(initiative);

    assertThat(createdInitiative.getCode()).isEqualTo(initiativeCode);
  }
}
