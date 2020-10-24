package ca.ulaval.glo4003.initiative.domain;

import static ca.ulaval.glo4003.initiative.helpers.InitiativeBuilder.*;
import static ca.ulaval.glo4003.initiative.helpers.InitiativeMother.*;
import static org.mockito.Mockito.when;

import com.google.common.truth.Truth;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class InitiativeFactoryTest {
  @Mock private InitiativeCodeGenerator initiativeCodeGenerator;
  private InitiativeFactory initiativeFactory;

  private String NAME = createName();
  private InitiativeCode INITIATIVE_CODE = createCode();
  private Initiative initiative = anInitiative().withInitiativeCode(null).build();

  @Before
  public void setUp() {
    initiativeFactory = new InitiativeFactory(initiativeCodeGenerator);

    when(initiativeCodeGenerator.generate()).thenReturn(INITIATIVE_CODE);
  }

  @Test
  public void whenCreatingInitiative_thenReturnInitiativeWithCode() {
    Initiative createdInitiative = initiativeFactory.create(initiative);

    Truth.assertThat(createdInitiative.getCode()).isEqualTo(INITIATIVE_CODE);
  }
}
