package ca.ulaval.glo4003.accesspasses.domain;

import ca.ulaval.glo4003.cars.domain.LicensePlate;
import java.util.List;

// TODO #313 : Remove AccessPassRepository
public interface AccessPassRepository {
  AccessPassCode save(AccessPass accessPass);

  AccessPass get(AccessPassCode code);

  List<AccessPass> get(LicensePlate licensePlate);

  void update(AccessPass accessPass);

  List<AccessPass> getAll();
}
