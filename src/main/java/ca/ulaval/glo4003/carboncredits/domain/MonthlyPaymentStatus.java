package ca.ulaval.glo4003.carboncredits.domain;

import ca.ulaval.glo4003.carboncredits.exceptions.InvalidMonthlyPaymentStatusException;
import java.util.HashMap;
import java.util.Map;

public enum MonthlyPaymentStatus {
  ENABLE("enable"),
  DISABLE("disable");

  String monthlyPaymentStatus;
  private static final Map<String, MonthlyPaymentStatus> lookup = new HashMap<>();

  static {
    for (MonthlyPaymentStatus name : MonthlyPaymentStatus.values()) {
      lookup.put(name.toString(), name);
    }
  }

  MonthlyPaymentStatus(String monthlyPaymentStatus) {
    this.monthlyPaymentStatus = monthlyPaymentStatus;
  }

  public static MonthlyPaymentStatus get(String monthlyPaymentStatus) {
    if (monthlyPaymentStatus == null) throw new InvalidMonthlyPaymentStatusException();

    MonthlyPaymentStatus foundMonthlyPaymentStatus = lookup.get(monthlyPaymentStatus.toLowerCase());

    if (foundMonthlyPaymentStatus == null) throw new InvalidMonthlyPaymentStatusException();

    return foundMonthlyPaymentStatus;
  }

  @Override
  public String toString() {
    return monthlyPaymentStatus;
  }
}
