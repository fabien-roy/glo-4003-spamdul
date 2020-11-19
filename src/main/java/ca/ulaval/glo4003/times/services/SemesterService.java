package ca.ulaval.glo4003.times.services;

import ca.ulaval.glo4003.times.domain.SemesterCode;
import ca.ulaval.glo4003.times.domain.SemesterRepository;
import ca.ulaval.glo4003.times.domain.TimePeriod;

public class SemesterService {
  private final SemesterRepository semesterRepository;

  public SemesterService(SemesterRepository semesterRepository) {
    this.semesterRepository = semesterRepository;
  }

  public TimePeriod getSemester(SemesterCode code) {
    return semesterRepository.findByCode(code);
  }
}
