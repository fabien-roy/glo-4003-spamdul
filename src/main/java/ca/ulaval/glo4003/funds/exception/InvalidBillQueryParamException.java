package ca.ulaval.glo4003.funds.exception;

public class InvalidBillQueryParamException extends RuntimeException {
  private static final String ERROR = "Invalid bill query param";
  private static final String DESCRIPTION = "Bill query param is not supported";

  // TODO : Should extends an abstract class?
}
