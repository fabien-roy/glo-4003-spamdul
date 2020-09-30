package ca.ulaval.glo4003.accounts.services;

import static ca.ulaval.glo4003.accounts.helpers.AccountBuilder.anAccount;
import static ca.ulaval.glo4003.domain.car.helpers.CarBuilder.aCar;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import ca.ulaval.glo4003.accounts.assemblers.AccountIdAssembler;
import ca.ulaval.glo4003.accounts.domain.Account;
import ca.ulaval.glo4003.accounts.domain.AccountRepository;
import ca.ulaval.glo4003.domain.car.Car;
import com.google.common.truth.Truth;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AccountServiceTest {

  @Mock private AccountIdAssembler accountIdAssembler;
  @Mock private AccountRepository accountRepository;

  private AccountService accountService;
  private Account account;
  private Car car;

  @Before
  public void setup() {
    accountService = new AccountService(accountIdAssembler, accountRepository);

    account = anAccount().build();
    car = aCar().build();

    when(accountIdAssembler.assemble(account.getId().toString())).thenReturn(account.getId());
    when(accountRepository.findById(account.getId())).thenReturn(account);
  }

  @Test
  public void whenAddingCar_shouldAssembleAccountId() {
    accountService.addCarToAccount(account.getId().toString(), car);

    verify(accountIdAssembler).assemble(account.getId().toString());
  }

  @Test
  public void whenAddingCar_shouldAddCarToAccount() {
    accountService.addCarToAccount(account.getId().toString(), car);

    Truth.assertThat(account.getCars()).contains(car);
  }

  @Test
  public void whenAddingCar_shouldSaveAccountToRepository() {
    accountService.addCarToAccount(account.getId().toString(), car);

    verify(accountRepository).save(account);
  }
}
