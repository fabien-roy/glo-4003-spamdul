package ca.ulaval.glo4003.initiative.domain;

public class InitiativeFactory {
  private final InitiativeCodeGenerator initiativeCodeGenerator;

  public InitiativeFactory(InitiativeCodeGenerator initiativeCodeGenerator) {
    this.initiativeCodeGenerator = initiativeCodeGenerator;
  }

  public Initiative createInitiative(String initiativeName) {
    InitiativeCode initiativeCode = initiativeCodeGenerator.generate();

    return new Initiative(initiativeCode, initiativeName);
  }
}
