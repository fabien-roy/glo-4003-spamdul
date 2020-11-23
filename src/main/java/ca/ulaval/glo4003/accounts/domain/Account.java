package ca.ulaval.glo4003.accounts.domain;

import ca.ulaval.glo4003.accesspasses.domain.AccessPass;
import ca.ulaval.glo4003.accesspasses.domain.AccessPassCode;
import ca.ulaval.glo4003.cars.domain.LicensePlate;
import ca.ulaval.glo4003.funds.domain.BillId;
import ca.ulaval.glo4003.funds.exception.BillNotFoundException;
import ca.ulaval.glo4003.parkings.domain.ParkingStickerCode;
import ca.ulaval.glo4003.users.domain.User;
import java.util.*;

public class Account {
  private final AccountId id;
  private final User user;
  private final Map<AccessPassCode, AccessPass> accessPasses = new HashMap<>();
  private List<ParkingStickerCode> parkingStickerCodes = new ArrayList<>();
  private List<LicensePlate> licensePlates = new ArrayList<>();
  private List<BillId> billIds = new ArrayList<>();

  public Account(AccountId id, User user) {
    this.id = id;
    this.user = user;
  }

  public AccountId getId() {
    return id;
  }

  public User getUser() {
    return user;
  }

  // TODO #313 : If this is only used by tests, it is unnecessary
  public Collection<AccessPass> getAccessPasses() {
    return accessPasses.values();
  }

  public List<ParkingStickerCode> getParkingStickerCodes() {
    return parkingStickerCodes;
  }

  public List<LicensePlate> getLicensePlates() {
    return licensePlates;
  }

  public List<BillId> getBillIds() {
    return billIds;
  }

  // TODO #313 : Test Account.addAccessPass
  public void addAccessPass(AccessPass accessPass) {
    accessPasses.put(accessPass.getCode(), accessPass);
  }

  public void addParkingStickerCode(ParkingStickerCode parkingSticker) {
    parkingStickerCodes.add(parkingSticker);
  }

  public void addLicensePlate(LicensePlate licensePlate) {
    licensePlates.add(licensePlate);
  }

  public void addBillId(BillId billId) {
    billIds.add(billId);
  }

  public void verifyAccountHasBillId(BillId billId) {
    if (!billIds.contains(billId)) {
      throw new BillNotFoundException();
    }
  }
}
