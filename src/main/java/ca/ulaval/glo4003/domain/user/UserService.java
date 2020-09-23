package ca.ulaval.glo4003.domain.user;

import ca.ulaval.glo4003.api.user.dto.AccountIdDto;
import ca.ulaval.glo4003.api.user.dto.UserDto;
import ca.ulaval.glo4003.domain.account.*;

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
    Account account = this.accountFactory.createAccount(userDto);

    AccountId accountId = this.accountRepository.save(account);

    AccountIdDto accountIdDto = new AccountIdDto();
    accountIdDto.accountId = accountId.toString();

    return accountIdDto;
  }

  public UserDto getUser(String stringId) {
    AccountId accountId = accountIdAssembler.assemble(stringId);
    Account account = accountRepository.findById(accountId);

    return userAssembler.create(account);
  }
}
