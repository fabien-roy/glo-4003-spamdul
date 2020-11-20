package ca.ulaval.glo4003.accesspasses.domain;

import ca.ulaval.glo4003.cars.domain.LicensePlate;
import java.util.List;

public interface AccessPassRepository {
  AccessPassCode save(AccessPass accessPass);

  AccessPass get(AccessPassCode code);

  List<AccessPass> get(LicensePlate licensePlate);

  void update(AccessPass accessPass);

  List<AccessPass> getAll();
}
