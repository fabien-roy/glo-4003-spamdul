package ca.ulaval.glo4003.times.domain;

public interface SemesterRepository {
  void save(SemesterCode code, TimePeriod period);

  TimePeriod findByCode(SemesterCode code);
}
