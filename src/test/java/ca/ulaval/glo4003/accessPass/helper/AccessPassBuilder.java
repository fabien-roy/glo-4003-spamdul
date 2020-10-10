package ca.ulaval.glo4003.accessPass.helper;

import static ca.ulaval.glo4003.accessPass.helper.AccessPassMother.createDay;
import static ca.ulaval.glo4003.accounts.helpers.AccountMother.createAccountId;

import ca.ulaval.glo4003.access.domain.AccessPass;

public class AccessPassBuilder {

  public static AccessPassBuilder anAccessPass() {
    return new AccessPassBuilder();
  }

  public AccessPass build() {
    return new AccessPass(createAccountId(), createDay());
  }
}
