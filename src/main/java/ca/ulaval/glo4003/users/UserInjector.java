package ca.ulaval.glo4003.users;

import ca.ulaval.glo4003.accesspasses.services.AccessPassService;
import ca.ulaval.glo4003.accounts.domain.AccountFactory;
import ca.ulaval.glo4003.accounts.domain.AccountRepository;
import ca.ulaval.glo4003.accounts.services.AccountService;
import ca.ulaval.glo4003.accounts.services.assemblers.AccountIdAssembler;
import ca.ulaval.glo4003.accounts.services.converters.AccountIdConverter;
import ca.ulaval.glo4003.cars.services.CarService;
import ca.ulaval.glo4003.funds.services.BillService;
import ca.ulaval.glo4003.parkings.services.ParkingStickerService;
import ca.ulaval.glo4003.times.services.converters.CustomDateConverter;
import ca.ulaval.glo4003.users.api.UserResource;
import ca.ulaval.glo4003.users.services.UserService;
import ca.ulaval.glo4003.users.services.assemblers.UserAssembler;
import ca.ulaval.glo4003.users.services.converters.UserConverter;

public class UserInjector {

  public UserResource createUserResource(
      AccountRepository accountRepository,
      AccountFactory accountFactory,
      AccountIdConverter accountIdConverter,
      AccountIdAssembler accountIdAssembler,
      CustomDateConverter customDateConverter,
      AccessPassService accessPassService,
      CarService carService,
      AccountService accountService,
      ParkingStickerService parkingStickerService,
      BillService billService) {
    UserConverter userConverter = new UserConverter(customDateConverter);
    UserAssembler userAssembler = new UserAssembler();

    UserService userService =
        new UserService(
            accountRepository,
            accountFactory,
            accountIdConverter,
            accountIdAssembler,
            userConverter,
            userAssembler);

    return new UserResource(
        userService,
        accessPassService,
        carService,
        accountService,
        parkingStickerService,
        billService);
  }
}
