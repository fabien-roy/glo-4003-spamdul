package ca.ulaval.glo4003.access.domain;

import ca.ulaval.glo4003.access.exceptions.InvalidAccessPeriodsException;
import java.util.HashMap;
import java.util.Map;

public enum AccessPeriods {
  ONE_HOUR("1h"),
  ONE_DAY("1j"),
  ONE_DAY_BY_WEEK_FOR_SESSION("1j/semaine/session"),
  ONE_SESSION("1 session"),
  TWO_SESSIONS("2 session"),
  THREE_SESSIONS("3 session");

  private String period;
  private static final Map<String, AccessPeriods> lookup = new HashMap<>();

  static {
    for (AccessPeriods period : AccessPeriods.values()) {
      lookup.put(period.toString(), period);
    }
  }

  AccessPeriods(String period) {
    this.period = period;
  }

  @Override
  public String toString() {
    return period;
  }

  public static AccessPeriods get(String period) {
    AccessPeriods foundPeriod = lookup.get(period.toLowerCase());

    if (foundPeriod == null) throw new InvalidAccessPeriodsException();

    return foundPeriod;
  }
}
