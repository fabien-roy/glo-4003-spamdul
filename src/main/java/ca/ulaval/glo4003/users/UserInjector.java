package ca.ulaval.glo4003.users;

import ca.ulaval.glo4003.access.services.AccessService;
import ca.ulaval.glo4003.accounts.assemblers.AccountIdAssembler;
import ca.ulaval.glo4003.accounts.domain.AccountFactory;
import ca.ulaval.glo4003.accounts.domain.AccountRepository;
import ca.ulaval.glo4003.cars.services.CarService;
import ca.ulaval.glo4003.times.assemblers.CustomDateAssembler;
import ca.ulaval.glo4003.users.api.UserResource;
import ca.ulaval.glo4003.users.api.UserResourceImplementation;
import ca.ulaval.glo4003.users.assemblers.UserAssembler;
import ca.ulaval.glo4003.users.services.UserService;

public class UserInjector {

  public UserResource createUserResource(
      AccountRepository accountRepository,
      AccountFactory accountFactory,
      AccountIdAssembler accountIdAssembler,
      CustomDateAssembler customDateAssembler,
      AccessService accessService,
      CarService carService) {
    UserAssembler userAssembler = new UserAssembler(customDateAssembler);

    UserService userService =
        new UserService(accountRepository, accountFactory, accountIdAssembler, userAssembler);

    return new UserResourceImplementation(userService, accessService, carService);
  }
}
