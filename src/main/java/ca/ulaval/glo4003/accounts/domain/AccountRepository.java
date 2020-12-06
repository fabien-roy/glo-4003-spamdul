package ca.ulaval.glo4003.accounts.domain;

import ca.ulaval.glo4003.accesspasses.domain.AccessPass;
import ca.ulaval.glo4003.accesspasses.domain.AccessPassCode;
import ca.ulaval.glo4003.cars.domain.Car;
import ca.ulaval.glo4003.cars.domain.LicensePlate;
import ca.ulaval.glo4003.parkings.domain.ParkingSticker;
import ca.ulaval.glo4003.parkings.domain.ParkingStickerCode;
import java.util.List;

public interface AccountRepository {
  AccountId save(Account account);

  Account get(AccountId id);

  ParkingSticker getParkingSticker(ParkingStickerCode parkingStickerCode);

  AccessPass getAccessPass(AccessPassCode accessPassCode);

  List<AccessPass> getAccessPasses(LicensePlate licensePlate);

  Car getCar(LicensePlate licensePlate);

  void update(Account account);

  void update(AccessPass accessPass);
}
