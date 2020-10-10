package ca.ulaval.glo4003.access;

import ca.ulaval.glo4003.access.api.AccessResource;
import ca.ulaval.glo4003.access.api.AccessResourceImplementation;
import ca.ulaval.glo4003.access.assembler.AccessPassAssembler;
import ca.ulaval.glo4003.access.domain.AccessPassCodeGenerator;
import ca.ulaval.glo4003.access.domain.AccessPassFactory;
import ca.ulaval.glo4003.access.services.AccessService;
import ca.ulaval.glo4003.cars.services.CarService;

public class AccessInjector {
  private AccessService accessService;
  private AccessResource accessResource;

  public AccessInjector(CarService carService) {
    AccessPassAssembler accessPassAssembler = new AccessPassAssembler();
    AccessPassCodeGenerator accessPassCodeGenerator = new AccessPassCodeGenerator();
    AccessPassFactory accessPassFactory = new AccessPassFactory(accessPassCodeGenerator);
    accessService = new AccessService(accessPassAssembler, accessPassFactory, carService);
    accessResource = new AccessResourceImplementation(accessService);

    addAccessPassByConsumptionTypesToRepository();
  }

  public AccessResource getAccessResource() {
    return accessResource;
  }

  private void addAccessPassByConsumptionTypesToRepository() {}
}
