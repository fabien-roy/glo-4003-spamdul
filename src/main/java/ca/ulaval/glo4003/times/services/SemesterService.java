package ca.ulaval.glo4003.times.services;

import ca.ulaval.glo4003.times.domain.SemesterCode;
import ca.ulaval.glo4003.times.domain.SemesterRepository;
import ca.ulaval.glo4003.times.domain.TimePeriod;
import ca.ulaval.glo4003.times.services.converters.SemesterCodeConverter;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class SemesterService {
  private final SemesterRepository semesterRepository;
  private final SemesterCodeConverter semesterCodeConverter;

  public SemesterService(
      SemesterRepository semesterRepository, SemesterCodeConverter semesterCodeConverter) {
    this.semesterRepository = semesterRepository;
    this.semesterCodeConverter = semesterCodeConverter;
  }

  private TimePeriod getSemester(SemesterCode code) {
    return semesterRepository.findByCode(code);
  }

  public List<TimePeriod> getSemester(String[] semesters) {
    if (semesters == null) return Collections.emptyList();

    return Arrays.stream(semesters)
        .map(semesterCodeConverter::convert)
        .map(this::getSemester)
        .collect(Collectors.toList());
  }
}
