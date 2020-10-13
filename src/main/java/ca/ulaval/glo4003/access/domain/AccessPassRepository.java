package ca.ulaval.glo4003.access.domain;

public interface AccessPassRepository {
  AccessPassCode save(AccessPass accessPass);

  AccessPass get(AccessPassCode accessPassCode);
}
