package ca.ulaval.glo4003.funds.services;

import static ca.ulaval.glo4003.funds.helpers.MoneyMother.createMoney;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import ca.ulaval.glo4003.funds.domain.Money;
import ca.ulaval.glo4003.funds.domain.PiggyBankRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class PiggyBankServiceTest {
  @Mock PiggyBankRepository piggyBankRepository;
  private Money money;

  private PiggyBankService piggyBankService;

  @Before
  public void setUp() {
    piggyBankService = new PiggyBankService(piggyBankRepository);

    money = createMoney();

    when(piggyBankRepository.get()).thenReturn(money);
  }

  @Test
  public void whenExtractingPiggyBankAvailableMoney_thenReturnPiggyBankAvailableMoney() {
    piggyBankService.extractPiggyBankAvailableMoney();

    verify(piggyBankRepository).takeAll();
  }

  @Test
  public void givenMoney_whenExtractPiggyBankSpecificAmount_thenToPiggyBankRepository() {
    piggyBankService.extractPiggyBankSpecificAmount(money);

    verify(piggyBankRepository).remove(money);
  }
}
