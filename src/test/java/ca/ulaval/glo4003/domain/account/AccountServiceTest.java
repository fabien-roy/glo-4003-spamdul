package ca.ulaval.glo4003.domain.account;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import ca.ulaval.glo4003.domain.car.Car;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AccountServiceTest {

  private static final String AN_ACCOUNT_ID = "000";
  private AccountService accountService;
  @Mock private AccountIdAssembler accountIdAssembler;
  @Mock private AccountRepository accountRepository;
  @Mock private AccountId accountId;
  @Mock private Car car;
  @Mock private Account account;

  @Before
  public void setup() {
    accountService = new AccountService(accountIdAssembler, accountRepository);

    when(accountIdAssembler.assemble(AN_ACCOUNT_ID)).thenReturn(accountId);
    when(accountRepository.findById(accountId)).thenReturn(account);
  }

  @Test
  public void whenAddingCar_shouldAssembleAccountId() {
    accountService.addCarToAccount(AN_ACCOUNT_ID, car);

    verify(accountIdAssembler).assemble(AN_ACCOUNT_ID);
  }

  @Test
  public void whenAddingCar_shouldFindCorrespondingAccountInRepository() {
    accountService.addCarToAccount(AN_ACCOUNT_ID, car);

    verify(accountRepository).findById(accountId);
  }

  @Test
  public void whenAddingCar_shouldAddCarToAccount() {
    accountService.addCarToAccount(AN_ACCOUNT_ID, car);

    verify(account).addCar(car);
  }
}
