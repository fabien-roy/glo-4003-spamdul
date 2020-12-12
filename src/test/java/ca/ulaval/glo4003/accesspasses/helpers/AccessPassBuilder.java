package ca.ulaval.glo4003.accesspasses.helpers;

import static ca.ulaval.glo4003.accesspasses.helpers.AccessPassMother.createAccessPassCode;
import static ca.ulaval.glo4003.accesspasses.helpers.AccessPassMother.createAccessPeriod;
import static ca.ulaval.glo4003.cars.helpers.LicensePlateMother.createLicensePlate;
import static ca.ulaval.glo4003.communications.helpers.EmailMother.createEmailAddress;
import static ca.ulaval.glo4003.locations.helpers.PostalCodeMother.createPostalCode;
import static ca.ulaval.glo4003.parkings.helpers.ParkingAreaMother.createParkingAreaCode;
import static ca.ulaval.glo4003.parkings.helpers.ParkingStickerMother.createReceptionMethod;

import ca.ulaval.glo4003.accesspasses.domain.AccessPass;
import ca.ulaval.glo4003.accesspasses.domain.AccessPassCode;
import ca.ulaval.glo4003.accesspasses.domain.AccessPeriod;
import ca.ulaval.glo4003.cars.domain.LicensePlate;
import ca.ulaval.glo4003.communications.domain.EmailAddress;
import ca.ulaval.glo4003.locations.domain.PostalCode;
import ca.ulaval.glo4003.parkings.domain.ParkingAreaCode;
import ca.ulaval.glo4003.parkings.domain.ReceptionMethod;
import ca.ulaval.glo4003.times.domain.CustomDateTime;
import ca.ulaval.glo4003.times.domain.DayOfWeek;
import ca.ulaval.glo4003.times.domain.TimePeriod;
import com.github.javafaker.Faker;
import java.util.ArrayList;
import java.util.List;

public class AccessPassBuilder {
  private AccessPassCode code = createAccessPassCode();
  private AccessPeriod accessPeriod = createAccessPeriod();
  private DayOfWeek accessDay;
  private LicensePlate licensePlate = createLicensePlate();
  private List<TimePeriod> accessTimePeriods = new ArrayList<>();
  private ReceptionMethod receptionMethod = createReceptionMethod();
  private ParkingAreaCode parkingAreaCode = createParkingAreaCode();
  private EmailAddress emailAddress = createEmailAddress();
  private PostalCode postalCode = createPostalCode();
  private boolean hasEnteredCampus = false;

  public static AccessPassBuilder anAccessPass() {
    return new AccessPassBuilder();
  }

  public AccessPassBuilder withCode(AccessPassCode code) {
    this.code = code;
    return this;
  }

  public AccessPassBuilder withValidDay(DayOfWeek validDay) {
    this.accessDay = validDay;
    return this;
  }

  public AccessPassBuilder withLicensePlate(LicensePlate licensePlate) {
    this.licensePlate = licensePlate;
    return this;
  }

  public AccessPassBuilder addAccessPeriodCurrentlyValid() {
    int firstUnimportantInt = Faker.instance().number().numberBetween(7, 99);
    int secondUnimportantInt = Faker.instance().number().numberBetween(14, 99);
    CustomDateTime start = CustomDateTime.now().minusDays(firstUnimportantInt);
    CustomDateTime end = CustomDateTime.now().plusDays(secondUnimportantInt);
    TimePeriod period = new TimePeriod(start, end);
    this.accessTimePeriods.add(period);
    return this;
  }

  public AccessPassBuilder addAccessPeriodCurrentlyInvalid() {
    int firstUnimportantInt = Faker.instance().number().numberBetween(49, 99);
    int secondUnimportantInt = Faker.instance().number().numberBetween(7, 49);
    CustomDateTime start = CustomDateTime.now().minusDays(firstUnimportantInt);
    CustomDateTime end = CustomDateTime.now().minusDays(secondUnimportantInt);
    TimePeriod period = new TimePeriod(start, end);
    this.accessTimePeriods.add(period);
    return this;
  }

  public AccessPassBuilder thatEnteredCampus() {
    this.hasEnteredCampus = true;
    return this;
  }

  public AccessPass build() {
    AccessPass accessPass =
        new AccessPass(
            accessPeriod,
            accessDay,
            licensePlate,
            accessTimePeriods,
            parkingAreaCode,
            receptionMethod,
            postalCode,
            emailAddress);
    accessPass.setCode(code);

    if (hasEnteredCampus) accessPass.enterCampus();

    return accessPass;
  }
}
