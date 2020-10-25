package ca.ulaval.glo4003.funds.exception;

public class InvalidBillQueryParamException extends RuntimeException {
  public final String error = "Invalid bill query param";
  public final String description = "Bill query param is not supported";
}
