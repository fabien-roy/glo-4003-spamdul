package ca.ulaval.glo4003.domain.bill.exceptions;

public class BillException extends RuntimeException {
  public final String error;
  public final String description;

  BillException(String error, String description) {
    this.error = error;
    this.description = description;
  }
}
