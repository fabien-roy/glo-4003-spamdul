package ca.ulaval.glo4003.piggybank.infrastructure;

import static ca.ulaval.glo4003.funds.helpers.MoneyMother.createMoney;

import ca.ulaval.glo4003.funds.domain.Money;
import ca.ulaval.glo4003.piggybank.domain.PiggyBankRepository;
import ca.ulaval.glo4003.piggybank.exceptions.PiggyBankInsufficientAmountException;
import com.google.common.truth.Truth;
import org.junit.Before;
import org.junit.Test;

public class PiggyBankRepositoryInMemoryTest {

  private static final Money ADDED_MONEY = new Money(300);
  private static final Money REMOVED_MONEY = new Money(100);
  private static final Money REMOVED_TOO_HIGH_MONEY_AMOUNT = new Money(400);
  private static final Money FINAL_AMOUNT = ADDED_MONEY.minus(REMOVED_MONEY);
  private PiggyBankRepository piggyBankRepository;

  @Before
  public void setUp() {
    piggyBankRepository = new PiggyBankRepositoryInMemory();
  }

  @Test
  public void givenMoney_whenAdding_thenMoneyIsAdded() {
    Money money = createMoney();
    piggyBankRepository.add(money);

    Truth.assertThat(piggyBankRepository.get()).isEqualTo(money);
  }

  @Test
  public void givenMoney_whenRemoving_thenMoneyIsRemoved() {
    piggyBankRepository.add(ADDED_MONEY);

    piggyBankRepository.remove(REMOVED_MONEY);

    Truth.assertThat(piggyBankRepository.get()).isEqualTo(FINAL_AMOUNT);
  }

  @Test(expected = PiggyBankInsufficientAmountException.class)
  public void givenTooHighMoneyAmount_whenRemoving_thenThrowPiggyBankInsufficientAmountException() {
    piggyBankRepository.add(ADDED_MONEY);

    piggyBankRepository.remove(REMOVED_TOO_HIGH_MONEY_AMOUNT);
  }

  @Test
  public void whenTakingAll_thenReturnAmountInPiggyBank() {
    piggyBankRepository.add(ADDED_MONEY);

    Money returnedAmount = piggyBankRepository.takeAll();

    Truth.assertThat(returnedAmount).isEqualTo(ADDED_MONEY);
  }

  @Test
  public void whenTakingAll_thenPiggyBankIsEmpty() {
    piggyBankRepository.add(ADDED_MONEY);

    piggyBankRepository.takeAll();

    Truth.assertThat(piggyBankRepository.get()).isEqualTo(Money.ZERO());
  }

  @Test
  public void whenGetting_thenMoneyInPiggyBankIsReturned() {
    piggyBankRepository.add(ADDED_MONEY);

    Truth.assertThat(piggyBankRepository.get()).isEqualTo(ADDED_MONEY);
  }
}
