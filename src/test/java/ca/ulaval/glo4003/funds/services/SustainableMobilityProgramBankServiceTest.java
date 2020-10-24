package ca.ulaval.glo4003.funds.services;

import static ca.ulaval.glo4003.funds.helpers.MoneyMother.createMoney;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import ca.ulaval.glo4003.funds.domain.Money;
import ca.ulaval.glo4003.funds.domain.SustainableMobilityProgramBankRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class SustainableMobilityProgramBankServiceTest {
  @Mock SustainableMobilityProgramBankRepository sustainableMobilityProgramBankRepository;
  private Money money;

  private SustainableMobilityProgramBankService sustainableMobilityProgramBankService;

  @Before
  public void setUp() {
    sustainableMobilityProgramBankService =
        new SustainableMobilityProgramBankService(sustainableMobilityProgramBankRepository);

    money = createMoney();

    when(sustainableMobilityProgramBankRepository.get()).thenReturn(money);
  }

  @Test
  public void
      whenExtractingSustainableMobilityProgramBankAvailableMoney_thenReturnSustainableMobilityProgramBankAvailableMoney() {
    sustainableMobilityProgramBankService.extractSustainableMobilityProgramBankAvailableMoney();

    verify(sustainableMobilityProgramBankRepository).takeAll();
  }

  @Test
  public void
      givenMoney_whenExtractSustainableMobilityProgramBankSpecificAmount_thenToSustainableMobilityProgramBankRepository() {
    sustainableMobilityProgramBankService.extractSustainableMobilityProgramBankSpecificAmount(
        money);

    verify(sustainableMobilityProgramBankRepository).remove(money);
  }
}
