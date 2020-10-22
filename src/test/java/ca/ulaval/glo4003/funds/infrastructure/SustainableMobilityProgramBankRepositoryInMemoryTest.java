package ca.ulaval.glo4003.funds.infrastructure;

import static ca.ulaval.glo4003.funds.helpers.MoneyMother.createMoney;

import ca.ulaval.glo4003.funds.domain.Money;
import ca.ulaval.glo4003.funds.domain.SustainableMobilityProgramBankRepository;
import ca.ulaval.glo4003.funds.exception.SustainableMobilityProgramBankInsufficientAmountException;
import com.google.common.truth.Truth;
import org.junit.Before;
import org.junit.Test;

public class SustainableMobilityProgramBankRepositoryInMemoryTest {

  private static final Money ADDED_MONEY = new Money(300);
  private static final Money REMOVED_MONEY = new Money(100);
  private static final Money REMOVED_TOO_HIGH_MONEY_AMOUNT = new Money(400);
  private static final Money FINAL_AMOUNT = ADDED_MONEY.minus(REMOVED_MONEY);
  private SustainableMobilityProgramBankRepository sustainableMobilityProgramBankRepository;

  @Before
  public void setUp() {
    sustainableMobilityProgramBankRepository =
        new SustainableMobilityProgramBankRepositoryInMemory();
  }

  @Test
  public void givenMoney_whenAdding_thenMoneyIsAdded() {
    Money money = createMoney();
    sustainableMobilityProgramBankRepository.add(money);

    Truth.assertThat(sustainableMobilityProgramBankRepository.get()).isEqualTo(money);
  }

  @Test
  public void givenMoney_whenRemoving_thenMoneyIsRemoved() {
    sustainableMobilityProgramBankRepository.add(ADDED_MONEY);

    sustainableMobilityProgramBankRepository.remove(REMOVED_MONEY);

    Truth.assertThat(sustainableMobilityProgramBankRepository.get()).isEqualTo(FINAL_AMOUNT);
  }

  @Test(expected = SustainableMobilityProgramBankInsufficientAmountException.class)
  public void
      givenTooHighMoneyAmount_whenRemoving_thenThrowSustainableMobilityProgramBankInsufficientAmountException() {
    sustainableMobilityProgramBankRepository.add(ADDED_MONEY);

    sustainableMobilityProgramBankRepository.remove(REMOVED_TOO_HIGH_MONEY_AMOUNT);
  }

  @Test
  public void whenTakingAll_thenReturnAmountInSustainableMobilityProgramBank() {
    sustainableMobilityProgramBankRepository.add(ADDED_MONEY);

    Money returnedAmount = sustainableMobilityProgramBankRepository.takeAll();

    Truth.assertThat(returnedAmount).isEqualTo(ADDED_MONEY);
  }

  @Test
  public void whenTakingAll_thenSustainableMobilityProgramBankIsEmpty() {
    sustainableMobilityProgramBankRepository.add(ADDED_MONEY);

    sustainableMobilityProgramBankRepository.takeAll();

    Truth.assertThat(sustainableMobilityProgramBankRepository.get()).isEqualTo(Money.ZERO());
  }

  @Test
  public void whenGetting_thenMoneyInSustainableMobilityProgramBankIsReturned() {
    sustainableMobilityProgramBankRepository.add(ADDED_MONEY);

    Truth.assertThat(sustainableMobilityProgramBankRepository.get()).isEqualTo(ADDED_MONEY);
  }
}
