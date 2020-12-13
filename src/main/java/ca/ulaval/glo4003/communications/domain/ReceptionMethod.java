package ca.ulaval.glo4003.communications.domain;

import ca.ulaval.glo4003.communications.domain.exceptions.InvalidReceptionMethodException;
import java.util.HashMap;
import java.util.Map;

public enum ReceptionMethod {
  POSTAL("postal"),
  EMAIL("email"),
  SSP("ssp");

  private final String method;
  private static final Map<String, ReceptionMethod> lookup = new HashMap<>();

  static {
    for (ReceptionMethod method : ReceptionMethod.values()) {
      lookup.put(method.toString(), method);
    }
  }

  ReceptionMethod(String method) {
    this.method = method;
  }

  @Override
  public String toString() {
    return method;
  }

  public static ReceptionMethod get(String method) {
    if (method == null) throw new InvalidReceptionMethodException();

    ReceptionMethod foundMethod = lookup.get(method.toLowerCase());

    if (foundMethod == null) throw new InvalidReceptionMethodException();

    return foundMethod;
  }
}
