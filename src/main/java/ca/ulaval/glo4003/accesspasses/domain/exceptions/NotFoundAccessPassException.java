package ca.ulaval.glo4003.accesspasses.domain.exceptions;

import ca.ulaval.glo4003.accesspasses.domain.AccessPassCode;
import ca.ulaval.glo4003.cars.domain.LicensePlate;
import ca.ulaval.glo4003.errors.domain.ErrorCode;
import ca.ulaval.glo4003.errors.domain.exceptions.ApplicationException;

public class NotFoundAccessPassException extends ApplicationException {
  private static final String ERROR = "Access pass not found";
  private static final String DESCRIPTION = "Access pass with code %s was not found";
  private static final String DESCRIPTION_WITH_LICENSE_PLATE =
      "Access pass associated with license plate %s was not found";
  private static final ErrorCode CODE = ErrorCode.NOT_FOUND;
  private AccessPassCode accessPassCode;
  private LicensePlate licensePlate;

  public NotFoundAccessPassException(AccessPassCode accessPassCode) {
    super(ERROR, DESCRIPTION, CODE);
    this.accessPassCode = accessPassCode;
  }

  public NotFoundAccessPassException(LicensePlate licensePlate) {
    super(ERROR, DESCRIPTION, CODE);
    this.licensePlate = licensePlate;
  }

  @Override
  public String getDescription() {
    return accessPassCode == null
        ? String.format(DESCRIPTION_WITH_LICENSE_PLATE, licensePlate.toString())
        : String.format(DESCRIPTION, accessPassCode.toString());
  }
}
