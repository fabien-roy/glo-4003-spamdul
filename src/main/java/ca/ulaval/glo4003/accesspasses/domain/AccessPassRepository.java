package ca.ulaval.glo4003.accesspasses.domain;

public interface AccessPassRepository {
  AccessPassCode save(AccessPass accessPass);

  AccessPass get(AccessPassCode code);
}
