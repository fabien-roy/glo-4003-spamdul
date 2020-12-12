package ca.ulaval.glo4003.accounts.infrastructure;

import ca.ulaval.glo4003.accesspasses.domain.AccessPass;
import ca.ulaval.glo4003.accesspasses.domain.AccessPassCode;
import ca.ulaval.glo4003.accesspasses.domain.exceptions.NotFoundAccessPassException;
import ca.ulaval.glo4003.accounts.domain.Account;
import ca.ulaval.glo4003.accounts.domain.AccountId;
import ca.ulaval.glo4003.accounts.domain.AccountRepository;
import ca.ulaval.glo4003.accounts.domain.exceptions.NotFoundAccountException;
import ca.ulaval.glo4003.cars.domain.Car;
import ca.ulaval.glo4003.cars.domain.LicensePlate;
import ca.ulaval.glo4003.cars.domain.exceptions.NotFoundCarException;
import ca.ulaval.glo4003.funds.domain.Bill;
import ca.ulaval.glo4003.funds.domain.BillId;
import ca.ulaval.glo4003.funds.domain.exceptions.NotFoundBillException;
import ca.ulaval.glo4003.parkings.domain.ParkingSticker;
import ca.ulaval.glo4003.parkings.domain.ParkingStickerCode;
import ca.ulaval.glo4003.parkings.domain.exceptions.NotFoundParkingStickerException;
import java.util.*;
import java.util.stream.Collectors;

public class AccountRepositoryInMemory implements AccountRepository {
  private final Map<AccountId, Account> accounts = new HashMap<>();

  @Override
  public AccountId save(Account account) {
    accounts.put(account.getId(), account);
    return account.getId();
  }

  @Override
  public Account get(AccountId accountId) {
    Account foundAccount = accounts.get(accountId);

    if (foundAccount == null) throw new NotFoundAccountException();

    return foundAccount;
  }

  @Override
  public ParkingSticker getParkingSticker(ParkingStickerCode parkingStickerCode) {
    Optional<ParkingSticker> parkingSticker =
        accounts.values().stream()
            .map(account -> account.getParkingSticker(parkingStickerCode))
            .filter(Objects::nonNull)
            .findFirst();

    if (parkingSticker.isPresent()) {
      return parkingSticker.get();
    } else {
      throw new NotFoundParkingStickerException();
    }
  }

  @Override
  public AccessPass getAccessPass(AccessPassCode accessPassCode) {
    Optional<AccessPass> accessPass =
        accounts.values().stream()
            .map(account -> account.getAccessPass(accessPassCode))
            .filter(Objects::nonNull)
            .findFirst();

    if (accessPass.isPresent()) {
      return accessPass.get();
    } else {
      throw new NotFoundAccessPassException();
    }
  }

  @Override
  public List<AccessPass> getAccessPasses(LicensePlate licensePlate) {
    List<AccessPass> accessPasses =
        accounts.values().stream()
            .flatMap(account -> account.getAccessPasses(licensePlate).stream())
            .collect(Collectors.toList());

    if (accessPasses.isEmpty()) throw new NotFoundAccessPassException();

    return accessPasses;
  }

  @Override
  public Car getCar(LicensePlate licensePlate) {
    Optional<Car> car =
        accounts.values().stream()
            .map(account -> account.getCar(licensePlate))
            .filter(Objects::nonNull)
            .findFirst();

    if (car.isPresent()) {
      return car.get();
    } else throw new NotFoundCarException();
  }

  @Override
  public void update(Account account) {
    Account foundAccount = get(account.getId());

    accounts.put(foundAccount.getId(), account);
  }

  @Override
  public void update(AccessPass accessPass) {
    Account account = get(accessPass.getCode());

    account.addAccessPass(accessPass);

    accounts.put(account.getId(), account);
  }

  @Override
  public void update(Bill bill) {
    Account account = get(bill.getId());

    account.addBill(bill);

    accounts.put(account.getId(), account);
  }

  private Account get(AccessPassCode accessPassCode) {
    Optional<Account> foundAccount =
        accounts.values().stream()
            .filter(account -> account.getAccessPass(accessPassCode) != null)
            .findFirst();

    if (foundAccount.isPresent()) {
      return foundAccount.get();
    } else {
      throw new NotFoundAccessPassException();
    }
  }

  private Account get(BillId billId) {
    Optional<Account> foundAccount =
        accounts.values().stream().filter(account -> account.getBill(billId) != null).findFirst();

    if (foundAccount.isPresent()) {
      return foundAccount.get();
    } else {
      throw new NotFoundBillException();
    }
  }
}
