package ca.ulaval.glo4003.domain.parking;

import java.util.HashMap;
import java.util.Map;

public enum ReceptionMethods {
  POSTAL("postal"),
  EMAIL("email");

  private String method;
  private static final Map<String, ReceptionMethods> lookup = new HashMap<>();

  static {
    for (ReceptionMethods method : ReceptionMethods.values()) {
      lookup.put(method.toString(), method);
    }
  }

  ReceptionMethods(String method) {
    this.method = method;
  }

  @Override
  public String toString() {
    return method;
  }

  public static ReceptionMethods get(String type) {
    ReceptionMethods foundMethod = lookup.get(type);

    if (foundMethod == null) throw new InvalidReceptionMethodException();

    return foundMethod;
  }
}
