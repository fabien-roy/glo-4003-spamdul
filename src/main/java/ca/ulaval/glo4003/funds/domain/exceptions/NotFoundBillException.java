package ca.ulaval.glo4003.funds.domain.exceptions;

import ca.ulaval.glo4003.errors.domain.ErrorCode;
import ca.ulaval.glo4003.errors.domain.exceptions.ApplicationException;
import ca.ulaval.glo4003.funds.domain.BillId;

public class NotFoundBillException extends ApplicationException {
  private static final String ERROR = "Bill not found";
  private static final String DESCRIPTION = "Bill with id %s was not found";
  private static final ErrorCode CODE = ErrorCode.NOT_FOUND;
  private final BillId billId;

  public NotFoundBillException(BillId billId) {
    super(ERROR, DESCRIPTION, CODE);
    this.billId = billId;
  }

  @Override
  public String getDescription() {
    return String.format(DESCRIPTION, billId);
  }
}
