package ca.ulaval.glo4003.initiative.domain;

import java.util.UUID;

public class InitiativeCodeGenerator {
  public InitiativeCode generate() {
    return new InitiativeCode(UUID.randomUUID());
  }
}
