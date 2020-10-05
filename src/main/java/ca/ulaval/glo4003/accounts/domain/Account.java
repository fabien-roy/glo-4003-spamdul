package ca.ulaval.glo4003.accounts.domain;

import ca.ulaval.glo4003.cars.domain.Car;
import ca.ulaval.glo4003.funds.domain.Bill;
import ca.ulaval.glo4003.parkings.domain.ParkingSticker;
import ca.ulaval.glo4003.users.domain.User;
import java.util.ArrayList;
import java.util.List;

public class Account {
  private final AccountId id;
  private final User user;
  private List<ParkingSticker> parkingStickers = new ArrayList<>();
  private List<Car> cars = new ArrayList<>();
  private List<Bill> bills = new ArrayList<>();

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

  public List<ParkingSticker> getParkingStickers() {
    return parkingStickers;
  }

  public List<Car> getCars() {
    return cars;
  }

  public List<Bill> getBills() {
    return bills;
  }

  public void addParkingSticker(ParkingSticker parkingSticker) {
    parkingStickers.add(parkingSticker);
  }

  public void addCar(Car car) {
    cars.add(car);
  }

  public void addBill(Bill bill) {
    bills.add(bill);
  }
}
