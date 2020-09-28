package ca.ulaval.glo4003.injection.user;

import ca.ulaval.glo4003.api.user.UserResource;
import ca.ulaval.glo4003.api.user.UserResourceImplementation;
import ca.ulaval.glo4003.domain.account.AccountFactory;
import ca.ulaval.glo4003.domain.account.AccountIdAssembler;
import ca.ulaval.glo4003.domain.account.AccountRepository;
import ca.ulaval.glo4003.domain.time.CustomDateAssembler;
import ca.ulaval.glo4003.domain.user.UserAssembler;
import ca.ulaval.glo4003.domain.user.UserService;

public class UserResourceConfig {

  public UserResource createUserResource(
      AccountRepository accountRepository,
      AccountFactory accountFactory,
      AccountIdAssembler accountIdAssembler,
      CustomDateAssembler customDateAssembler) {
    UserAssembler userAssembler = new UserAssembler(customDateAssembler);

    UserService userService =
        new UserService(accountRepository, accountFactory, accountIdAssembler, userAssembler);

    return new UserResourceImplementation(userService);
  }
}
