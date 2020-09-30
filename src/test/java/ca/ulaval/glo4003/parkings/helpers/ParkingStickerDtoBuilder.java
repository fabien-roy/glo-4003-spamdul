package ca.ulaval.glo4003.parkings.helpers;

import static ca.ulaval.glo4003.accounts.helpers.AccountMother.createAccountId;
import static ca.ulaval.glo4003.communications.helpers.EmailAddressMother.createEmailAddress;
import static ca.ulaval.glo4003.locations.helpers.PostalCodeMother.createPostalCode;
import static ca.ulaval.glo4003.parkings.helpers.ParkingAreaMother.createParkingAreaCode;
import static ca.ulaval.glo4003.parkings.helpers.ParkingStickerMother.createReceptionMethod;
import static ca.ulaval.glo4003.times.helpers.DayMother.createDay;

import ca.ulaval.glo4003.parkings.api.dto.ParkingStickerDto;

public class ParkingStickerDtoBuilder {
  private String accountId = createAccountId().toString();
  private String parkingArea = createParkingAreaCode().toString();
  private String receptionMethod = createReceptionMethod().toString();
  private String postalCode = createPostalCode().toString();
  private String email = createEmailAddress().toString();
  private String validDay = createDay().toString();

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

  public ParkingStickerDtoBuilder withPostalCode(String postalCode) {
    this.postalCode = postalCode;
    return this;
  }

  public ParkingStickerDtoBuilder withEmail(String email) {
    this.email = email;
    return this;
  }

  public ParkingStickerDtoBuilder withValidDay(String validDay) {
    this.validDay = validDay;
    return this;
  }

  public ParkingStickerDto build() {
    ParkingStickerDto parkingStickerDto = new ParkingStickerDto();
    parkingStickerDto.accountId = accountId;
    parkingStickerDto.parkingArea = parkingArea;
    parkingStickerDto.receptionMethod = receptionMethod;
    parkingStickerDto.postalCode = postalCode;
    parkingStickerDto.email = email;
    parkingStickerDto.validDay = validDay;
    return parkingStickerDto;
  }
}