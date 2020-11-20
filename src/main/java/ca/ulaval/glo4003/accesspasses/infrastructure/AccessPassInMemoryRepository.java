package ca.ulaval.glo4003.accesspasses.infrastructure;

import ca.ulaval.glo4003.accesspasses.domain.AccessPass;
import ca.ulaval.glo4003.accesspasses.domain.AccessPassCode;
import ca.ulaval.glo4003.accesspasses.domain.AccessPassRepository;
import ca.ulaval.glo4003.accesspasses.exceptions.NotFoundAccessPassException;
import ca.ulaval.glo4003.cars.domain.LicensePlate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

  @Override
  public List<AccessPass> get(LicensePlate licensePlate) {
    List<AccessPass> accessPassesFound =
        accessPasses.entrySet().stream()
            .filter(accessPass -> accessPass.getValue().getLicensePlate().equals(licensePlate))
            .map(Map.Entry::getValue)
            .collect(Collectors.toList());

    if (accessPassesFound.isEmpty()) throw new NotFoundAccessPassException();

    return accessPassesFound;
  }

  @Override
  public void update(AccessPass accessPass) {
    accessPasses.replace(accessPass.getCode(), accessPass);
  }

  @Override
  public List<AccessPass> getAll() {
    List<AccessPass> accessPassList = new ArrayList<>();
    for (AccessPassCode accessPassCode : accessPasses.keySet()) {
      accessPassList.add(accessPasses.get(accessPassCode));
    }
    return accessPassList;
  }
}
