package ca.ulaval.glo4003.accesspasses.helpers;

import static ca.ulaval.glo4003.accesspasses.helpers.AccessPassMother.createAccessPassCode;
import static ca.ulaval.glo4003.accounts.helpers.AccountMother.createAccountId;
import static ca.ulaval.glo4003.cars.helpers.LicensePlateMother.createLicensePlate;
import static ca.ulaval.glo4003.times.helpers.DayOfWeekMother.createDayOfWeek;

import ca.ulaval.glo4003.accesspasses.domain.AccessPass;
import ca.ulaval.glo4003.accesspasses.domain.AccessPassCode;
import ca.ulaval.glo4003.accounts.domain.AccountId;
import ca.ulaval.glo4003.cars.domain.LicensePlate;
import ca.ulaval.glo4003.times.domain.DayOfWeek;

public class AccessPassBuilder {
  private AccessPassCode code = createAccessPassCode();
  private AccountId accountId = createAccountId();
  private DayOfWeek accessDay = createDayOfWeek();
  private LicensePlate licensePlate = createLicensePlate();
  private boolean isAdmittedOnCampus = false;

  public static AccessPassBuilder anAccessPass() {
    return new AccessPassBuilder();
  }

  public AccessPassBuilder withValidDay(DayOfWeek validDay) {
    this.accessDay = validDay;
    return this;
  }

  public AccessPassBuilder withLicensePlate(LicensePlate licensePlate) {
    this.licensePlate = licensePlate;
    return this;
  }

  public AccessPassBuilder withAccessToCampus() {
    this.isAdmittedOnCampus = true;
    return this;
  }

  public AccessPass build() {
    AccessPass accessPass = new AccessPass(accountId, accessDay, licensePlate, isAdmittedOnCampus);
    accessPass.setCode(code);
    return accessPass;
  }
}
