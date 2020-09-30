package ca.ulaval.glo4003.domain.user;

import ca.ulaval.glo4003.accounts.assemblers.AccountIdAssembler;
import ca.ulaval.glo4003.accounts.domain.Account;
import ca.ulaval.glo4003.accounts.domain.AccountFactory;
import ca.ulaval.glo4003.accounts.domain.AccountId;
import ca.ulaval.glo4003.accounts.domain.AccountRepository;
import ca.ulaval.glo4003.api.user.dto.AccountIdDto;
import ca.ulaval.glo4003.api.user.dto.UserDto;

public class UserService {
  private final AccountRepository accountRepository;
  private final AccountFactory accountFactory;
  private final AccountIdAssembler accountIdAssembler;
  private final UserAssembler userAssembler;

  public UserService(
      AccountRepository accountRepository,
      AccountFactory accountFactory,
      AccountIdAssembler accountIdAssembler,
      UserAssembler userAssembler) {
    this.accountRepository = accountRepository;
    this.accountFactory = accountFactory;
    this.accountIdAssembler = accountIdAssembler;
    this.userAssembler = userAssembler;
  }

  public AccountIdDto addUser(UserDto userDto) {
    User user = userAssembler.assemble(userDto);
    Account account = accountFactory.createAccount(user);

    AccountId accountId = accountRepository.save(account);

    return accountIdAssembler.assemble(accountId);
  }

  public UserDto getUser(String stringId) {
    AccountId accountId = accountIdAssembler.assemble(stringId);
    Account account = accountRepository.findById(accountId);

    return userAssembler.assemble(account.getUser());
  }
}
