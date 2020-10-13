package ca.ulaval.glo4003.access.helpers;

import static ca.ulaval.glo4003.access.helpers.AccessPassMother.createAccessPassCode;
import static ca.ulaval.glo4003.accounts.helpers.AccountMother.createAccountId;
import static ca.ulaval.glo4003.cars.helpers.LicensePlateMother.createLicensePlate;
import static ca.ulaval.glo4003.times.helpers.DayMother.createDay;

import ca.ulaval.glo4003.access.domain.AccessPass;
import ca.ulaval.glo4003.access.domain.AccessPassCode;
import ca.ulaval.glo4003.accounts.domain.AccountId;
import ca.ulaval.glo4003.cars.domain.LicensePlate;
import ca.ulaval.glo4003.times.domain.DayOfWeek;

public class AccessPassBuilder {
  private AccessPassCode code = createAccessPassCode();
  private AccountId accountId = createAccountId();
  private DayOfWeek validDay = createDay();
  private LicensePlate licensePlate = createLicensePlate();

  public static AccessPassBuilder anAccessPass() {
    return new AccessPassBuilder();
  }

  public AccessPassBuilder withValidDay(DayOfWeek validDay) {
    this.validDay = validDay;
    return this;
  }

  public AccessPassBuilder withLicensePlate(LicensePlate licensePlate) {
    this.licensePlate = licensePlate;
    return this;
  }

  public AccessPass build() {
    AccessPass accessPass = new AccessPass(accountId, validDay, licensePlate);
    accessPass.setCode(code);
    return accessPass;
  }
}
