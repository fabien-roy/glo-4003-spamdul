package ca.ulaval.glo4003.access.infrastructure;

import ca.ulaval.glo4003.access.domain.AccessPass;
import ca.ulaval.glo4003.access.domain.AccessPassCode;
import ca.ulaval.glo4003.access.domain.AccessPassRepository;
import ca.ulaval.glo4003.access.exceptions.NotFoundAccessPassException;
import java.util.HashMap;

public class AccessPassInMemoryRepository implements AccessPassRepository {
  private HashMap<AccessPassCode, AccessPass> accessPasses = new HashMap<>();

  @Override
  public AccessPassCode save(AccessPass accessPass) {
    accessPasses.put(accessPass.getCode(), accessPass);
    return accessPass.getCode();
  }

  @Override
  public AccessPass get(AccessPassCode code) {
    AccessPass accessPass = accessPasses.get(code);

    if (accessPass == null) throw new NotFoundAccessPassException();

    return accessPass;
  }
}
