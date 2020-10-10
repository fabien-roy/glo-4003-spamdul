package ca.ulaval.glo4003.access;

import ca.ulaval.glo4003.access.api.AccessResource;
import ca.ulaval.glo4003.access.api.AccessResourceImplementation;
import ca.ulaval.glo4003.access.services.AccessService;

public class AccessInjector {
  private AccessService accessService;
  private AccessResource accessResource;

  public AccessInjector() {
    accessService = new AccessService();
    accessResource = new AccessResourceImplementation(accessService);
  }

  public AccessService getAccessService() {
    return accessService;
  }

  public AccessResource getAccessResource() {
    return accessResource;
  }
}
