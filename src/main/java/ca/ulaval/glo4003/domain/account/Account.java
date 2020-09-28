package ca.ulaval.glo4003.domain.account;

import ca.ulaval.glo4003.domain.bill.Bill;
import ca.ulaval.glo4003.domain.parking.ParkingSticker;
import ca.ulaval.glo4003.domain.parking.ParkingStickerCode;
import ca.ulaval.glo4003.domain.user.User;
import java.util.ArrayList;
import java.util.List;

public class Account {
  private AccountId id;
  private User user;
  private List<ParkingStickerCode> parkingStickerCodes = new ArrayList<>();
  private Bill bill = new Bill();

  public Account(AccountId id, User user) {
    this.id = id;
    this.user = user;
  }

  public Bill getBill() {
    return bill;
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

  public void addParkingSticker(ParkingSticker parkingSticker) {
    parkingStickerCodes.add(parkingSticker.getCode());

    bill.calculateZonePriceWithCommunicationType(
        parkingSticker.getReceptionMethod(),
        parkingSticker.getParkingAreaCode().toString(),
        "1j/sem/session");
  }
}
