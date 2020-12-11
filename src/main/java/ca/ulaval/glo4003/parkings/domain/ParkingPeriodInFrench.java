package ca.ulaval.glo4003.parkings.domain;

import ca.ulaval.glo4003.parkings.domain.exceptions.InvalidParkingPeriodException;
import java.util.HashMap;
import java.util.Map;

public enum ParkingPeriodInFrench {
  UNE_JOURNEE_PAR_SEMAINE_PAR_SESSION("1j/sem/session"),
  MENSUEL("mensuel"),
  UNE_SESSION("1 session"),
  DEUX_SESSIONS("2 session"),
  TROIS_SESSIONS("3 session");

  private String period;
  private static final Map<String, ParkingPeriodInFrench> lookup = new HashMap<>();

  static {
    for (ParkingPeriodInFrench period : ParkingPeriodInFrench.values()) {
      lookup.put(period.toString(), period);
    }
  }

  ParkingPeriodInFrench(String period) {
    this.period = period;
  }

  public static ParkingPeriodInFrench get(String period) {
    if (period == null) throw new InvalidParkingPeriodException();

    ParkingPeriodInFrench foundPeriod = lookup.get(period.toLowerCase());

    if (foundPeriod == null) throw new InvalidParkingPeriodException();

    return foundPeriod;
  }

  @Override
  public String toString() {
    return period;
  }
}
