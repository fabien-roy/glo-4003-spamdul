package ca.ulaval.glo4003.accesspasses.domain;

import ca.ulaval.glo4003.cars.domain.LicensePlate;

public interface AccessPassRepository {
  AccessPassCode save(AccessPass accessPass);

  AccessPass get(AccessPassCode code);

  AccessPass get(LicensePlate licensePlate);
}
