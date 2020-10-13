package ca.ulaval.glo4003.access.domain;

public class AccessPassFactory {
  private final AccessPassCodeGenerator accessPassCodeGenerator;

  public AccessPassFactory(AccessPassCodeGenerator accessPassCodeGenerator) {
    this.accessPassCodeGenerator = accessPassCodeGenerator;
  }

  public AccessPass create(AccessPass accessPass) {
    AccessPassCode accessPassCode = accessPassCodeGenerator.generate();
    accessPass.setCode(accessPassCode);
    return accessPass;
  }
}
