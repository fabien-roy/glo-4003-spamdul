package ca.ulaval.glo4003.parkings.services.converters;

import ca.ulaval.glo4003.parkings.domain.ParkingPeriod;
import ca.ulaval.glo4003.parkings.domain.ParkingPeriodInFrench;
import ca.ulaval.glo4003.parkings.exceptions.InvalidParkingPeriodException;

public class ParkingPeriodConverter {
  public ParkingPeriod convert(ParkingPeriodInFrench parkingPeriodInFrench) {
    switch (parkingPeriodInFrench) {
      case UNE_JOURNEE_PAR_SEMAINE_PAR_SESSION:
        return ParkingPeriod.ONE_DAY_PER_WEEK_PER_SEMESTER;
      case MENSUEL:
        return ParkingPeriod.MONTHLY;
      case UNE_SESSION:
        return ParkingPeriod.ONE_SEMESTER;
      case DEUX_SESSIONS:
        return ParkingPeriod.TWO_SEMESTERS;
      case TROIS_SESSIONS:
        return ParkingPeriod.THREE_SEMESTERS;
    }

    throw new InvalidParkingPeriodException();
  }
}
