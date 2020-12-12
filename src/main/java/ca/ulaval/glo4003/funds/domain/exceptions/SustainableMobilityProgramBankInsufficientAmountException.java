package ca.ulaval.glo4003.funds.domain.exceptions;

import ca.ulaval.glo4003.errors.domain.ErrorCode;
import ca.ulaval.glo4003.errors.domain.exceptions.ApplicationException;

public class SustainableMobilityProgramBankInsufficientAmountException
    extends ApplicationException {
  private static final String ERROR = "Insufficient amount";
  private static final String DESCRIPTION =
      "Sustainable mobility program bank has an insufficient amount";
  private static final ErrorCode CODE = ErrorCode.INVALID_REQUEST;

  public SustainableMobilityProgramBankInsufficientAmountException() {
    super(ERROR, DESCRIPTION, CODE);
  }
}
