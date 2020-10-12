package ca.ulaval.glo4003.access.infrastructure;

import ca.ulaval.glo4003.access.domain.AccessPass;
import ca.ulaval.glo4003.access.domain.AccessPassCode;
import ca.ulaval.glo4003.access.domain.AccessPassRepository;
import java.util.HashMap;

public class AccessPassInMemoryRepository implements AccessPassRepository {
  private HashMap<AccessPassCode, AccessPass> accessPasses = new HashMap<>();

  @Override
  public AccessPassCode save(AccessPass accessPass) {
    accessPasses.put(accessPass.getAccessPassCode(), accessPass);
    return accessPass.getAccessPassCode();
  }
}
