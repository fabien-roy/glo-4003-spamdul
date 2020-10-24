package ca.ulaval.glo4003.parkings.domain;

import ca.ulaval.glo4003.parkings.exceptions.InvalidParkingPeriodException;
import java.util.HashMap;
import java.util.Map;

public enum ParkingPeriod {
  ONE_DAY("1d/week/session"),
  MONTHLY("monthly"),
  ONE_SESSION("1 session"),
  TWO_SESSIONS("2 session"),
  THREE_SESSIONS("3 session");

  private String period;
  private static final Map<String, ParkingPeriod> lookup = new HashMap<>();

  static {
    for (ParkingPeriod period : ParkingPeriod.values()) {
      lookup.put(period.toString(), period);
    }
  }

  ParkingPeriod(String period) {
    this.period = period;
  }

  @Override
  public String toString() {
    return period;
  }

  public static ParkingPeriod get(String period) {
    if (period == null) throw new InvalidParkingPeriodException();

    ParkingPeriod foundPeriod = lookup.get(period.toLowerCase());

    if (foundPeriod == null) throw new InvalidParkingPeriodException();

    return foundPeriod;
  }
}
