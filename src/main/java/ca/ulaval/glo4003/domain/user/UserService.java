package ca.ulaval.glo4003.domain.user;

import ca.ulaval.glo4003.api.contact.dto.AccountIdDto;
import ca.ulaval.glo4003.api.contact.dto.UserDto;
import ca.ulaval.glo4003.domain.account.*;

public class UserService {
  private AccountRepository accountRepository;
  private AccountFactory accountFactory;
  private UserAssembler userAssembler;

  public UserService(
      AccountRepository accountRepository,
      AccountFactory accountFactory,
      UserAssembler userAssembler) {
    this.accountRepository = accountRepository;
    this.accountFactory = accountFactory;
    this.userAssembler = userAssembler;
  }

  public AccountIdDto addUser(UserDto userDto) {
    Account account = this.accountFactory.createAccount(userDto);

    AccountId accountId = this.accountRepository.save(account);

    AccountIdDto accountIdDto = new AccountIdDto();
    accountIdDto.accountId = accountId.toString();

    return accountIdDto;
  }

  public UserDto getUser(String accountId) {
    Account account = this.accountRepository.findById(new AccountId(accountId));

    return this.userAssembler.create(account);
  }
}
