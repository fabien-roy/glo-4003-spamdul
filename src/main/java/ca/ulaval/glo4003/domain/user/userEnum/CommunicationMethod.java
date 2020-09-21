package ca.ulaval.glo4003.domain.user.userEnum;

import ca.ulaval.glo4003.domain.user.exception.InvalidCommunicationMethodAttributeException;
import java.util.HashMap;
import java.util.Map;

public enum CommunicationMethod {
  EMAIL("email"),
  POSTAL("postal");

  private String name;
  private static final Map<String, CommunicationMethod> lookup =
      new HashMap<String, CommunicationMethod>();

  static {
    for (CommunicationMethod name : CommunicationMethod.values()) {
      lookup.put(name.toString(), name);
    }
  }

  CommunicationMethod(String name) {
    this.name = name;
  }

  public static CommunicationMethod get(String name) {
    CommunicationMethod foundType = lookup.get(name);

    if (foundType == null) throw new InvalidCommunicationMethodAttributeException();

    return foundType;
  }

  @Override
  public String toString() {
    return name;
  }
}
