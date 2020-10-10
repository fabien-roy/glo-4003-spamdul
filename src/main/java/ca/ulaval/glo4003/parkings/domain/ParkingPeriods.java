package ca.ulaval.glo4003.parkings.domain;

import ca.ulaval.glo4003.parkings.exceptions.InvalidParkingPeriodException;
import java.util.HashMap;
import java.util.Map;

public enum ParkingPeriods {
  ONE_DAY("1j/sem/session"),
  MONTHLY("mensuel"),
  ONE_SESSION("1 session"),
  TWO_SESSIONS("2 session"),
  THREE_SESSIONS("3 session");

  private String period;
  private static final Map<String, ParkingPeriods> lookup = new HashMap<>();

  static {
    for (ParkingPeriods period : ParkingPeriods.values()) {
      lookup.put(period.toString(), period);
    }
  }

  ParkingPeriods(String period) {
    this.period = period;
  }

  @Override
  public String toString() {
    return period;
  }

  public static ParkingPeriods get(String period) {
    ParkingPeriods foundPeriod = lookup.get(period.toLowerCase());

    if (foundPeriod == null) throw new InvalidParkingPeriodException();

    return foundPeriod;
  }
}
