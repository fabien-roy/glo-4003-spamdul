package ca.ulaval.glo4003.parkings.domain;

import ca.ulaval.glo4003.parkings.exceptions.InvalidParkingPeriodException;
import java.util.HashMap;
import java.util.Map;

public enum ParkingFrenchPeriod {
  UNE_JOURNEE("1j/sem/session"),
  MENSUEL("mensuel"),
  UNE_SESSION("1 session"),
  DEUX_SESSIONS("2 session"),
  TROIS_SESSIONS("3 session");

  private String period;
  private static final Map<String, ParkingFrenchPeriod> lookup = new HashMap<>();

  static {
    for (ParkingFrenchPeriod period : ParkingFrenchPeriod.values()) {
      lookup.put(period.toString(), period);
    }
  }

  ParkingFrenchPeriod(String period) {
    this.period = period;
  }

  public static ParkingFrenchPeriod get(String period) {
    if (period == null) throw new InvalidParkingPeriodException();

    ParkingFrenchPeriod foundPeriod = lookup.get(period.toLowerCase());

    if (foundPeriod == null) throw new InvalidParkingPeriodException();

    return foundPeriod;
  }

  @Override
  public String toString() {
    return period;
  }
}
