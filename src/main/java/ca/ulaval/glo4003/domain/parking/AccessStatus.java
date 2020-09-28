package ca.ulaval.glo4003.domain.parking;

import java.util.HashMap;
import java.util.Map;

public enum AccessStatus {
  ACCESS_GRANTED("Access granted"),
  ACCESS_REFUSED("Access refused");

  private String status;
  private static final Map<String, AccessStatus> lookup = new HashMap<>();

  static {
    for (AccessStatus status : AccessStatus.values()) {
      lookup.put(status.toString(), status);
    }
  }

  AccessStatus(String status) {
    this.status = status;
  }

  @Override
  public String toString() {
    return status;
  }
}
