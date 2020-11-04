package ca.ulaval.glo4003.accesspasses.infrastructure;

import ca.ulaval.glo4003.accesspasses.domain.AccessPass;
import ca.ulaval.glo4003.accesspasses.domain.AccessPassCode;
import ca.ulaval.glo4003.accesspasses.domain.AccessPassRepository;
import ca.ulaval.glo4003.accesspasses.exceptions.NotFoundAccessPassException;
import ca.ulaval.glo4003.cars.domain.LicensePlate;
import java.util.HashMap;

public class AccessPassInMemoryRepository implements AccessPassRepository {
  private HashMap<AccessPassCode, AccessPass> accessPassesByCode = new HashMap<>();
  private HashMap<LicensePlate, AccessPass> accessPassesByLicense = new HashMap<>();

  @Override
  public AccessPassCode save(AccessPass accessPass) {
    accessPassesByCode.put(accessPass.getCode(), accessPass);

    // TODO should have something to represent null license plate for accessPass instead of null
    // value
    if (!accessPass.getLicensePlate().equals(null)) {
      accessPassesByLicense.put(accessPass.getLicensePlate(), accessPass);
    }

    return accessPass.getCode();
  }

  @Override
  public AccessPass get(AccessPassCode code) {
    AccessPass accessPass = accessPassesByCode.get(code);

    if (accessPass == null) throw new NotFoundAccessPassException();

    return accessPass;
  }

  @Override
  public AccessPass get(LicensePlate licensePlate) {
    AccessPass accessPass = accessPassesByLicense.get(licensePlate);

    if (accessPass == null) throw new NotFoundAccessPassException();

    return accessPass;
  }
}
