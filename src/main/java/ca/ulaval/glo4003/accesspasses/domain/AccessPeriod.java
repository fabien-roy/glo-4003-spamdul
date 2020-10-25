package ca.ulaval.glo4003.accesspasses.domain;

import ca.ulaval.glo4003.accesspasses.exceptions.InvalidAccessPeriodException;
import java.util.HashMap;
import java.util.Map;

public enum AccessPeriod {
  ONE_HOUR("1h"),
  ONE_DAY("1d"),
  ONE_DAY_BY_WEEK_FOR_SESSION("1d/week/session"),
  ONE_SESSION("1 session"),
  TWO_SESSIONS("2 session"),
  THREE_SESSIONS("3 session");

  private String period;
  private static final Map<String, AccessPeriod> lookup = new HashMap<>();

  static {
    for (AccessPeriod period : AccessPeriod.values()) {
      lookup.put(period.toString(), period);
    }
  }

  AccessPeriod(String period) {
    this.period = period;
  }

  @Override
  public String toString() {
    return period;
  }

  public static AccessPeriod get(String period) {
    if (period == null) throw new InvalidAccessPeriodException();

    AccessPeriod foundPeriod = lookup.get(period.toLowerCase());

    if (foundPeriod == null) throw new InvalidAccessPeriodException();

    return foundPeriod;
  }
}
