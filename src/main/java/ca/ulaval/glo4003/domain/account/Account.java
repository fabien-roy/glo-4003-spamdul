package ca.ulaval.glo4003.domain.account;

import ca.ulaval.glo4003.domain.car.Car;
import ca.ulaval.glo4003.domain.parking.ParkingStickerCode;
import ca.ulaval.glo4003.domain.user.User;
import java.util.ArrayList;
import java.util.List;

public class Account {
  private AccountId id;
  private User user;
  private List<ParkingStickerCode> parkingStickerCodes = new ArrayList<>();
  private List<Car> cars = new ArrayList<>();

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

  public void addParkingStickerCode(ParkingStickerCode parkingStickerCode) {
    parkingStickerCodes.add(parkingStickerCode);
  }

  public void addCar(Car car) {
    cars.add(car);
  }
}
