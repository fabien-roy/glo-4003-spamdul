package ca.ulaval.glo4003.initiatives.infrastructure;

import static ca.ulaval.glo4003.funds.helpers.MoneyMother.createMoney;
import static ca.ulaval.glo4003.funds.helpers.MoneyMother.createMoneyBelowAmount;
import static ca.ulaval.glo4003.initiatives.helpers.InitiativeBuilder.anInitiative;

import ca.ulaval.glo4003.funds.domain.Money;
import ca.ulaval.glo4003.funds.domain.exceptions.InsufficientAvailableMoneyException;
import ca.ulaval.glo4003.initiatives.domain.Initiative;
import ca.ulaval.glo4003.initiatives.domain.InitiativeCode;
import ca.ulaval.glo4003.initiatives.domain.exceptions.NotFoundInitiativeException;
import com.google.common.truth.Truth;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class InitiativeRepositoryInMemoryTest {
  private InitiativeRepositoryInMemory initiativeRepository;

  private final Initiative initiative = anInitiative().build();
  private final Initiative otherInitiative = anInitiative().build();
  private final Money allocatedAmount = createMoney();

  private static final Money REMOVED_TOO_HIGH_MONEY_AMOUNT = createMoney();
  private static final Money ADDED_MONEY = createMoneyBelowAmount(REMOVED_TOO_HIGH_MONEY_AMOUNT);
  private static final Money REMOVED_MONEY = createMoneyBelowAmount(ADDED_MONEY);
  private static final Money FINAL_AMOUNT = ADDED_MONEY.minus(REMOVED_MONEY);

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

  @Test(expected = NotFoundInitiativeException.class)
  public void whenGettingNonExistingInitiative_thenThrowInitiativeNotFoundException() {
    initiativeRepository.get(initiative.getCode());
  }

  @Test
  public void givenInitiativeCode_whenGettingInitiative_thenReturnInitiative() {
    initiativeRepository.save(initiative);

    Initiative InitiativeFromRepo = initiativeRepository.get(initiative.getCode());

    Truth.assertThat(InitiativeFromRepo).isEqualTo(initiative);
  }

  @Test(expected = NotFoundInitiativeException.class)
  public void givenNoInitiative_whenUpdating_thenThrowInitiativeNotFoundException() {
    initiativeRepository.update(initiative);
  }

  @Test
  public void givenInitiative_whenUpdating_thenInitiativeIsUpdated() {
    initiativeRepository.save(initiative);
    Initiative initiativeBeforeUpdating = initiativeRepository.get(initiative.getCode());
    initiative.addAllocatedAmount(allocatedAmount);

    initiativeRepository.update(initiative);
    Initiative initiativeAfterUpdating = initiativeRepository.get(initiative.getCode());

    Truth.assertThat(initiativeBeforeUpdating.getAllocatedAmount())
        .isNotEqualTo(initiativeAfterUpdating);
  }

  @Test
  public void givenMoney_whenAdding_thenMoneyIsAdded() {
    Money money = createMoney();
    initiativeRepository.addMoney(money);

    Truth.assertThat(initiativeRepository.getAvailableMoney()).isEqualTo(money);
  }

  @Test
  public void givenMoney_whenRemoving_thenMoneyIsRemoved() {
    initiativeRepository.addMoney(ADDED_MONEY);

    initiativeRepository.takeMoney(REMOVED_MONEY);

    Truth.assertThat(initiativeRepository.getAvailableMoney()).isEqualTo(FINAL_AMOUNT);
  }

  @Test(expected = InsufficientAvailableMoneyException.class)
  public void givenTooHighMoneyAmount_whenRemoving_thenThrowInsufficientAvailableMoneyException() {
    initiativeRepository.addMoney(ADDED_MONEY);

    initiativeRepository.takeMoney(REMOVED_TOO_HIGH_MONEY_AMOUNT);
  }
}
