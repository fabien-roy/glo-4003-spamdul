package ca.ulaval.glo4003.users;

import ca.ulaval.glo4003.accesspasses.services.AccessPassService;
import ca.ulaval.glo4003.accounts.domain.AccountFactory;
import ca.ulaval.glo4003.accounts.domain.AccountRepository;
import ca.ulaval.glo4003.accounts.services.AccountService;
import ca.ulaval.glo4003.cars.services.CarService;
import ca.ulaval.glo4003.funds.services.BillService;
import ca.ulaval.glo4003.parkings.services.ParkingStickerService;
import ca.ulaval.glo4003.users.api.UserResource;
import ca.ulaval.glo4003.users.services.UserService;

public class UserInjector {

  public UserResource createUserResource(
      AccountRepository accountRepository,
      AccountFactory accountFactory,
      AccessPassService accessPassService,
      CarService carService,
      AccountService accountService,
      ParkingStickerService parkingStickerService,
      BillService billService) {

    UserService userService = new UserService(accountRepository, accountFactory);

    return new UserResource(
        userService,
        accessPassService,
        carService,
        accountService,
        parkingStickerService,
        billService);
  }
}
