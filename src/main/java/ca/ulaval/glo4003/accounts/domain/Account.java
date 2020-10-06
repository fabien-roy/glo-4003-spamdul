package ca.ulaval.glo4003.accounts.domain;

import ca.ulaval.glo4003.cars.domain.Car;
import ca.ulaval.glo4003.funds.domain.BillId;
import ca.ulaval.glo4003.parkings.domain.ParkingStickerCode;
import ca.ulaval.glo4003.users.domain.User;
import java.util.ArrayList;
import java.util.List;

public class Account {
  private final AccountId id;
  private final User user;
  private List<ParkingStickerCode> parkingStickerCodes = new ArrayList<>();
  private List<Car> cars = new ArrayList<>(); // TODO : List of LicensePlate
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

  public List<Car> getCars() {
    return cars;
  }

  public List<BillId> getBillIds() {
    return billIds;
  }

  public void addParkingStickerCode(ParkingStickerCode parkingSticker) {
    parkingStickerCodes.add(parkingSticker);
  }

  public void addCar(Car car) {
    cars.add(car);
  }

  public void addBillId(BillId billId) {
    billIds.add(billId);
  }
}
