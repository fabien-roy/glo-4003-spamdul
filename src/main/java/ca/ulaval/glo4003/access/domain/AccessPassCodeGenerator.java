package ca.ulaval.glo4003.access.domain;

import java.util.UUID;

public class AccessPassCodeGenerator {
  public AccessPassCode generate() {
    return new AccessPassCode(UUID.randomUUID().toString());
  }
}
