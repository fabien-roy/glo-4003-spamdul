package ca.ulaval.glo4003.times.infrastructure;

import ca.ulaval.glo4003.times.domain.SemesterCode;
import ca.ulaval.glo4003.times.domain.SemestersRepository;
import ca.ulaval.glo4003.times.domain.TimePeriod;
import ca.ulaval.glo4003.times.exceptions.SemesterNotFoundException;
import java.util.HashMap;
import java.util.Map;

public class SemestersInMemory implements SemestersRepository {
  private final Map<SemesterCode, TimePeriod> semesters = new HashMap<>();

  @Override
  public void save(SemesterCode code, TimePeriod period) {
    semesters.put(code, period);
  }

  @Override
  public TimePeriod findByCode(SemesterCode code) {
    TimePeriod foundSemester = semesters.get(code);

    if (foundSemester == null) {
      throw new SemesterNotFoundException();
    }

    return foundSemester;
  }
}
