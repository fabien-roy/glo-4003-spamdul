package ca.ulaval.glo4003.accounts.domain;

import static ca.ulaval.glo4003.accounts.helpers.AccountBuilder.anAccount;
import static ca.ulaval.glo4003.funds.helpers.BillMother.createBillId;

import ca.ulaval.glo4003.funds.domain.BillId;
import ca.ulaval.glo4003.funds.exception.BillNotFoundException;
import org.junit.Test;

public class AccountTest {
  private Account account = anAccount().build();
  private BillId billId = createBillId();

  @Test(expected = BillNotFoundException.class)
  public void whenVerifyAccountHasBillIdAndItDoesNot_thenThrowBillNotFound() {
    account.verifyAccountHasBillId(billId);
  }
}
