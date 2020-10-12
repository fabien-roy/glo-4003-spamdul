package ca.ulaval.glo4003.accounts.domain;

import ca.ulaval.glo4003.access.domain.AccessPassCode;
import ca.ulaval.glo4003.cars.domain.LicensePlate;
import ca.ulaval.glo4003.funds.domain.BillId;
import ca.ulaval.glo4003.funds.exception.BillNotFoundException;
import ca.ulaval.glo4003.parkings.domain.ParkingStickerCode;
import ca.ulaval.glo4003.users.domain.User;
import java.util.ArrayList;
import java.util.List;

public class Account {
  private final AccountId id;
  private final User user;
  private List<ParkingStickerCode> parkingStickerCodes = new ArrayList<>();
  private List<AccessPassCode> accessPassCodes = new ArrayList<>();
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

  public List<ParkingStickerCode> getParkingStickerCodes() {
    return parkingStickerCodes;
  }

  public List<LicensePlate> getLicensePlates() {
    return licensePlates;
  }

  public List<BillId> getBillIds() {
    return billIds;
  }

  public void addParkingStickerCode(ParkingStickerCode parkingSticker) {
    parkingStickerCodes.add(parkingSticker);
  }

  public void addAccessPassCode(AccessPassCode accessPassCode) {
    accessPassCodes.add(accessPassCode);
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

  public List<AccessPassCode> getAccessPassCodes() {
    return accessPassCodes;
  }
}
