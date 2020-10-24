package ca.ulaval.glo4003.initiatives.domain;

public class InitiativeFactory {
  private InitiativeCodeGenerator initiativeCodeGenerator;

  public InitiativeFactory(InitiativeCodeGenerator initiativeCodeGenerator) {
    this.initiativeCodeGenerator = initiativeCodeGenerator;
  }

  public Initiative create(Initiative initiative) {
    InitiativeCode initiativeCode = initiativeCodeGenerator.generate();
    initiative.setCode(initiativeCode);
    return initiative;
  }
}
