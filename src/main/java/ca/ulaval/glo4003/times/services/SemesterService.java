package ca.ulaval.glo4003.times.services;

import ca.ulaval.glo4003.times.domain.SemesterCode;
import ca.ulaval.glo4003.times.domain.SemestersRepository;
import ca.ulaval.glo4003.times.domain.TimePeriod;

public class SemesterService {
  private final SemestersRepository semestersRepository;

  public SemesterService(SemestersRepository semestersRepository) {
    this.semestersRepository = semestersRepository;
  }

  public TimePeriod getSemester(SemesterCode code) {
    return semestersRepository.findByCode(code);
  }
}
