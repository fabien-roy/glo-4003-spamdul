package ca.ulaval.glo4003.initiative.domain;

import static ca.ulaval.glo4003.initiative.helpers.InitiativeMother.*;
import static org.mockito.Mockito.when;

import ca.ulaval.glo4003.funds.domain.Money;
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
  private Money DEFAULT_ALLOCATED_MONEY = Money.ZERO();

  @Before
  public void setUp() {
    initiativeFactory = new InitiativeFactory(initiativeCodeGenerator);

    when(initiativeCodeGenerator.generate()).thenReturn(INITIATIVE_CODE);
  }

  @Test
  public void whenCreatingInitiative_thenReturnInitiativeWithCode() {
    Initiative initiative = initiativeFactory.createInitiative(NAME);

    Truth.assertThat(initiative.getInitiativeCode()).isEqualTo(INITIATIVE_CODE);
  }

  @Test
  public void whenCreatingInitiative_thenReturnInitiativeWithGivenName() {
    Initiative initiative = initiativeFactory.createInitiative(NAME);

    Truth.assertThat(initiative.getInitiativeName()).isEqualTo(NAME);
  }

  @Test
  public void whenCreatingInitiative_thenReturnInitiativeWithNoAllocatedMoney() {
    Initiative initiative = initiativeFactory.createInitiative(NAME);

    Truth.assertThat(initiative.getAllocatedAmount()).isEqualTo(DEFAULT_ALLOCATED_MONEY);
  }
}
