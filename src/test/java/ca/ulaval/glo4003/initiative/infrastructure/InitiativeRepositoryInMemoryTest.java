package ca.ulaval.glo4003.initiative.infrastructure;

import static ca.ulaval.glo4003.funds.helpers.MoneyMother.createMoney;
import static ca.ulaval.glo4003.initiative.helpers.InitiativeBuilder.anInitiative;

import ca.ulaval.glo4003.funds.domain.Money;
import ca.ulaval.glo4003.initiative.domain.Initiative;
import ca.ulaval.glo4003.initiative.domain.InitiativeCode;
import ca.ulaval.glo4003.initiative.domain.InitiativeRepository;
import ca.ulaval.glo4003.initiative.exception.InitiativeNotFoundException;
import com.google.common.truth.Truth;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class InitiativeRepositoryInMemoryTest {
  private InitiativeRepository initiativeRepository;

  private final Initiative initiative = anInitiative().build();
  private final Initiative otherInitiative = anInitiative().build();
  private final Money allocatedAmount = createMoney();

  @Before
  public void setUp() {
    initiativeRepository = new InitiativeRepositoryInMemory();
  }

  @Test
  public void whenSavingInitiative_thenReturnCode() {
    InitiativeCode initiativeCode = initiativeRepository.save(initiative);

    Truth.assertThat(initiativeCode).isEqualTo(initiative.getCode());
  }

  @Test
  public void givenMultipleInitiative_whenGettingAllInitiatives_thenReturnAllInitiatives() {
    initiativeRepository.save(initiative);
    initiativeRepository.save(otherInitiative);

    List<Initiative> initiatives = initiativeRepository.getAll();

    Truth.assertThat(initiatives).contains(initiative);
    Truth.assertThat(initiatives).contains(otherInitiative);
  }

  @Test(expected = InitiativeNotFoundException.class)
  public void whenGettingNonExistingInitiative_thenThrowInitiativeNotFoundException() {
    initiativeRepository.get(initiative.getCode());
  }

  @Test
  public void givenInitiativeCode_whenGettingInitiative_thenReturnInitiative() {
    initiativeRepository.save(initiative);

    Initiative InitiativeFromRepo = initiativeRepository.get(initiative.getCode());

    Truth.assertThat(InitiativeFromRepo).isEqualTo(initiative);
  }

  @Test(expected = InitiativeNotFoundException.class)
  public void givenNoInitiative_whenUpdating_thenThrowInitiativeNotFoundException() {
    initiativeRepository.update(initiative);
  }

  @Test
  public void givenBill_whenUpdating_thenBillIsUpdated() {
    initiativeRepository.save(initiative);
    Initiative initiativeBeforeUpdating = initiativeRepository.get(initiative.getCode());
    initiative.addAllocatedAmount(allocatedAmount);

    initiativeRepository.update(initiative);
    Initiative initiativeAfterUpdating = initiativeRepository.get(initiative.getCode());

    Truth.assertThat(initiativeBeforeUpdating.getAllocatedAmount())
        .isNotEqualTo(initiativeAfterUpdating);
  }
}
