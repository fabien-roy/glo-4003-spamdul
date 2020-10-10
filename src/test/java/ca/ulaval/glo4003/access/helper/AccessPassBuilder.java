package ca.ulaval.glo4003.access.helper;

import static ca.ulaval.glo4003.accounts.helpers.AccountMother.createAccountId;
import static ca.ulaval.glo4003.times.helpers.DayMother.createDay;

import ca.ulaval.glo4003.access.domain.AccessPass;

public class AccessPassBuilder {

  public static AccessPassBuilder anAccessPass() {
    return new AccessPassBuilder();
  }

  public AccessPass build() {
    return new AccessPass(createAccountId(), createDay());
  }
}
