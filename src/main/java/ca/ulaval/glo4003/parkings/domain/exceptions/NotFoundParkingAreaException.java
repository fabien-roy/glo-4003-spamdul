package ca.ulaval.glo4003.parkings.domain.exceptions;

import ca.ulaval.glo4003.errors.domain.ErrorCode;
import ca.ulaval.glo4003.errors.domain.exceptions.ApplicationException;
import ca.ulaval.glo4003.parkings.domain.ParkingAreaCode;
import java.util.List;
import java.util.stream.Collectors;

public class NotFoundParkingAreaException extends ApplicationException {
  private static final String ERROR = "Parking area not found";
  private static final String DESCRIPTION = "Parking area should be one of %s";
  private static final ErrorCode CODE = ErrorCode.NOT_FOUND;
  private final List<ParkingAreaCode> parkingAreaCodes;

  public NotFoundParkingAreaException(List<ParkingAreaCode> parkingAreaCodes) {
    super(ERROR, DESCRIPTION, CODE);
    this.parkingAreaCodes = parkingAreaCodes;
  }

  @Override
  public String getDescription() {
    List<String> parkingCodes =
        parkingAreaCodes.stream().map(ParkingAreaCode::toString).collect(Collectors.toList());
    return String.format(DESCRIPTION, enumerateStrings(parkingCodes));
  }
}
