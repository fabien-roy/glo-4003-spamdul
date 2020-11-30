package ca.ulaval.glo4003.times;

import ca.ulaval.glo4003.files.domain.StringFileReader;
import ca.ulaval.glo4003.files.filesystem.JsonFileReader;
import ca.ulaval.glo4003.times.domain.*;
import ca.ulaval.glo4003.times.filesystem.SemesterFileHelper;
import ca.ulaval.glo4003.times.infrastructure.SemesterRepositoryInMemory;
import ca.ulaval.glo4003.times.services.SemesterService;
import ca.ulaval.glo4003.times.services.converters.CustomDateConverter;
import ca.ulaval.glo4003.times.services.converters.CustomDateTimeConverter;
import ca.ulaval.glo4003.times.services.converters.SemesterCodeConverter;
import ca.ulaval.glo4003.times.services.converters.TimeOfDayConverter;
import ca.ulaval.glo4003.times.services.dto.SemesterDto;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class TimeInjector {
  private final SemesterRepository semesterRepository = new SemesterRepositoryInMemory();
  private final StringFileReader fileReader = new JsonFileReader();

  public TimeInjector() {
    addSemestersToRepository();
  }

  public CustomDateConverter createCustomDateConverter() {
    return new CustomDateConverter();
  }

  public CustomDateTimeConverter createCustomDateTimeConverter() {
    return new CustomDateTimeConverter();
  }

  public TimeOfDayConverter createTimeOfDayConverter() {
    return new TimeOfDayConverter();
  }

  public SemesterService createSemesterService() {
    return new SemesterService(semesterRepository);
  }

  private void addSemestersToRepository() {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    SemesterFileHelper semesterFileHelper = new SemesterFileHelper(fileReader);
    SemesterCodeConverter semesterCodeConverter = new SemesterCodeConverter();

    List<SemesterDto> semesters = semesterFileHelper.getSemesters();

    for (SemesterDto semester : semesters) {
      SemesterCode code = semesterCodeConverter.convert(semester.code);
      CustomDateTime start =
          new CustomDateTime(LocalDate.parse(semester.start, formatter).atTime(LocalTime.MIN));
      CustomDateTime end =
          new CustomDateTime(LocalDate.parse(semester.end, formatter).atTime(LocalTime.MAX));
      TimePeriod period = new TimePeriod(start, end);
      semesterRepository.save(code, period);
    }
  }
}
