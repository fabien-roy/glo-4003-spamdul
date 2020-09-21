package ca.ulaval.glo4003.api.parking.helpers;

import static ca.ulaval.glo4003.domain.account.helpers.AccountObjectMother.createAccountId;
import static ca.ulaval.glo4003.domain.parking.helpers.ParkingAreaObjectMother.createParkingAreaCode;
import static ca.ulaval.glo4003.domain.parking.helpers.ParkingStickerObjectMother.createAddress;
import static ca.ulaval.glo4003.domain.parking.helpers.ParkingStickerObjectMother.createReceptionMethod;

import ca.ulaval.glo4003.api.parking.dto.ParkingStickerDto;

public class ParkingStickerDtoBuilder {
  private String accountId = createAccountId().toString();
  private String parkingArea = createParkingAreaCode().toString();
  private String receptionMethod = createReceptionMethod().toString();
  private String address = createAddress();

  private ParkingStickerDtoBuilder() {}

  public static ParkingStickerDtoBuilder aParkingStickerDto() {
    return new ParkingStickerDtoBuilder();
  }

  public ParkingStickerDtoBuilder withAccountId(String accountId) {
    this.accountId = accountId;
    return this;
  }

  public ParkingStickerDtoBuilder withParkingArea(String parkingArea) {
    this.parkingArea = parkingArea;
    return this;
  }

  public ParkingStickerDtoBuilder withReceptionMethod(String receptionMethod) {
    this.receptionMethod = receptionMethod;
    return this;
  }

  public ParkingStickerDtoBuilder withoutAddress() {
    this.address = null;
    return this;
  }

  public ParkingStickerDto build() {
    ParkingStickerDto parkingStickerDto = new ParkingStickerDto();
    parkingStickerDto.accountId = accountId;
    parkingStickerDto.parkingArea = parkingArea;
    parkingStickerDto.receptionMethod = receptionMethod;
    parkingStickerDto.address = address;
    return parkingStickerDto;
  }
}
