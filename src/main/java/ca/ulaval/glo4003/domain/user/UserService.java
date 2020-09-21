package ca.ulaval.glo4003.domain.user;

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

  public void addUser(UserDto userDto) throws AccountValidationError {
    Account account = this.accountFactory.createAccount(userDto);

    this.accountRepository.save(account);
  }

  public UserDto getUser(String accountId) {
    Account account = this.accountRepository.findById(new AccountId(accountId));

    return this.userAssembler.create(account);
  }
}
