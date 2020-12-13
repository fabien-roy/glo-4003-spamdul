package ca.ulaval.glo4003.accesspasses.helpers;

import static ca.ulaval.glo4003.communications.helpers.EmailMother.createEmailAddress;
import static ca.ulaval.glo4003.communications.helpers.PostalCodeMother.createPostalCode;
import static ca.ulaval.glo4003.parkings.helpers.ParkingStickerMother.createReceptionMethod;
import static ca.ulaval.glo4003.times.helpers.SemesterCodeMother.createSemesterCode;

import ca.ulaval.glo4003.accesspasses.services.dto.BicycleAccessPassDto;

public class BicycleAccessPassDtoBuilder {
  private String semester = createSemesterCode().toString();
  private String receptionMethod = createReceptionMethod().toString();
  private String postalCode = createPostalCode().toString();
  private String email = createEmailAddress().toString();

  public static BicycleAccessPassDtoBuilder aBicycleAccessPassDto() {
    return new BicycleAccessPassDtoBuilder();
  }

  public BicycleAccessPassDtoBuilder withSemester(String semester) {
    this.semester = semester;
    return this;
  }

  public BicycleAccessPassDtoBuilder withReceptionMethod(String receptionMethod) {
    this.receptionMethod = receptionMethod;
    return this;
  }

  public BicycleAccessPassDtoBuilder withEmailAddress(String email) {
    this.email = email;
    return this;
  }

  public BicycleAccessPassDtoBuilder withPostalCode(String postalCode) {
    this.postalCode = postalCode;
    return this;
  }

  public BicycleAccessPassDto build() {
    BicycleAccessPassDto bicycleAccessPassDto = new BicycleAccessPassDto();
    bicycleAccessPassDto.semester = semester;
    bicycleAccessPassDto.receptionMethod = receptionMethod;
    bicycleAccessPassDto.postalCode = postalCode;
    bicycleAccessPassDto.email = email;
    return bicycleAccessPassDto;
  }
}
