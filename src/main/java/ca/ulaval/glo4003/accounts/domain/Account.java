package ca.ulaval.glo4003.accounts.domain;

import ca.ulaval.glo4003.bills.domain.Bill;
import ca.ulaval.glo4003.cars.domain.Car;
import ca.ulaval.glo4003.parkings.domain.ParkingSticker;
import ca.ulaval.glo4003.parkings.domain.ParkingStickerCode;
import ca.ulaval.glo4003.users.domain.User;
import java.util.ArrayList;
import java.util.List;

public class Account {
  private final AccountId id;
  private final User user;
  private List<ParkingStickerCode> parkingStickerCodes = new ArrayList<>();
  private List<Car> cars = new ArrayList<>();
  private Bill bill = new Bill();

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

  public void setParkingStickerCodes(List<ParkingStickerCode> parkingStickerCodes) {
    this.parkingStickerCodes = parkingStickerCodes;
  }

  public void setBill(Bill bill) {
    this.bill = bill;
  }

  public List<ParkingStickerCode> getParkingStickerCodes() {
    return parkingStickerCodes;
  }

  public List<Car> getCars() {
    return cars;
  }

  public Bill getBill() {
    return bill;
  }

  public void addParkingSticker(ParkingSticker parkingSticker) {
    parkingStickerCodes.add(parkingSticker.getCode());

    bill.calculateZonePriceWithCommunicationType(
        parkingSticker.getReceptionMethod(),
        parkingSticker.getParkingAreaCode().toString(),
        "1j/sem/session");
  }

  public void addCar(Car car) {
    cars.add(car);
  }
}
