package ca.ulaval.glo4003.domain.account;

import ca.ulaval.glo4003.domain.parking.ParkingSticker;
import java.util.List;
import jersey.repackaged.com.google.common.collect.Lists;

public class Account {
  private AccountId id;
  private List<ParkingSticker> parkingStickers = Lists.newArrayList();

  public void setId(AccountId id) {
    this.id = id;
  }

  public void addParkingSticker(ParkingSticker parkingSticker) {
    parkingStickers.add(parkingSticker);
  }

  public List<ParkingSticker> getParkingStickers() {
    return parkingStickers;
  }
}
