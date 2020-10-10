package ca.ulaval.glo4003.access.domain;

import java.util.UUID;

public class AccessPassCodeGenerator {
  public AccessPassCode generate() {
    // TODO : Instead of UUID, shouldn't we use some sticker code pattern?
    return new AccessPassCode(UUID.randomUUID().toString());
  }
}
