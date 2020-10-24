package ca.ulaval.glo4003.initiative.infrastructure;

import static ca.ulaval.glo4003.initiative.helpers.InitiativeBuilder.aInitiative;
import static ca.ulaval.glo4003.initiative.helpers.InitiativeMother.createAmount;

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

  private final Initiative initiative = aInitiative().build();
  private final Initiative initiative2 = aInitiative().build();
  private final Money ADDED_ALLOCATED_AMOUNT = new Money(createAmount());

  @Before
  public void setUp() {
    initiativeRepository = new InitiativeRepositoryInMemory();
  }

  @Test
  public void whenSavingInitiative_thenReturnCode() {
    InitiativeCode initiativeCode = initiativeRepository.save(initiative);

    Truth.assertThat(initiativeCode).isEqualTo(initiative.getInitiativeCode());
  }

  @Test
  public void givenMultipleInitiative_whenGettingAllInitiatives_thenReturnAllInitiatives() {
    initiativeRepository.save(initiative);
    initiativeRepository.save(initiative2);

    List<Initiative> initiatives = initiativeRepository.getAllInitiatives();

    Truth.assertThat(initiatives).contains(initiative);
    Truth.assertThat(initiatives).contains(initiative2);
  }

  @Test(expected = InitiativeNotFoundException.class)
  public void whenGettingNonExistingInitiative_thenThrowInitiativeNotFoundException() {
    initiativeRepository.get(initiative.getInitiativeCode());
  }

  @Test
  public void givenInitiativeCode_whenGettingInitiative_thenReturnInitiative() {
    initiativeRepository.save(initiative);

    Initiative InitiativeFromRepo = initiativeRepository.get(initiative.getInitiativeCode());

    Truth.assertThat(InitiativeFromRepo).isEqualTo(initiative);
  }

  @Test(expected = InitiativeNotFoundException.class)
  public void givenNoInitiative_whenUpdating_thenThrowInitiativeNotFoundException() {
    initiativeRepository.update(initiative);
  }

  @Test
  public void givenBill_whenUpdating_thenBillIsUpdated() {
    initiativeRepository.save(initiative);
    Initiative initiativeBeforeUpdating = initiativeRepository.get(initiative.getInitiativeCode());
    initiative.addAllocatedAmount(ADDED_ALLOCATED_AMOUNT);

    initiativeRepository.update(initiative);
    Initiative initiativeAfterUpdating = initiativeRepository.get(initiative.getInitiativeCode());

    Truth.assertThat(initiativeBeforeUpdating.getAllocatedAmount())
        .isNotEqualTo(initiativeAfterUpdating);
  }
}
