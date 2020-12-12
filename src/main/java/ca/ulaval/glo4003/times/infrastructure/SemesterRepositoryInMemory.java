package ca.ulaval.glo4003.times.infrastructure;

import ca.ulaval.glo4003.times.domain.SemesterCode;
import ca.ulaval.glo4003.times.domain.SemesterRepository;
import ca.ulaval.glo4003.times.domain.TimePeriod;
import ca.ulaval.glo4003.times.domain.exceptions.NotFoundSemesterException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SemesterRepositoryInMemory implements SemesterRepository {
  private final Map<SemesterCode, TimePeriod> semesters = new HashMap<>();

  @Override
  public void save(SemesterCode code, TimePeriod period) {
    semesters.put(code, period);
  }

  @Override
  public TimePeriod findByCode(SemesterCode code) {
    TimePeriod foundSemester = semesters.get(code);

    if (foundSemester == null) {
      throw new NotFoundSemesterException(getSemesterCodes());
    }

    return foundSemester;
  }

  private List<SemesterCode> getSemesterCodes() {
    return new ArrayList<>(semesters.keySet());
  }
}
