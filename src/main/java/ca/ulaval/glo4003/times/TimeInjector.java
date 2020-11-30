package ca.ulaval.glo4003.times;

import ca.ulaval.glo4003.files.domain.StringFileReader;
import ca.ulaval.glo4003.files.filesystem.JsonFileReader;
import ca.ulaval.glo4003.times.domain.*;
import ca.ulaval.glo4003.times.filesystem.SemesterFileHelper;
import ca.ulaval.glo4003.times.infrastructure.SemesterRepositoryInMemory;
import ca.ulaval.glo4003.times.services.SemesterService;
import ca.ulaval.glo4003.times.services.assemblers.CustomDateAssembler;
import ca.ulaval.glo4003.times.services.assemblers.CustomDateTimeAssembler;
import ca.ulaval.glo4003.times.services.assemblers.SemesterCodeAssembler;
import ca.ulaval.glo4003.times.services.assemblers.TimeOfDayAssembler;
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

  public CustomDateAssembler createCustomDateAssembler() {
    return new CustomDateAssembler();
  }

  public CustomDateTimeAssembler createCustomDateTimeAssembler() {
    return new CustomDateTimeAssembler();
  }

  public TimeOfDayAssembler createTimeOfDayAssembler() {
    return new TimeOfDayAssembler();
  }

  public SemesterService createSemesterService() {
    return new SemesterService(semesterRepository);
  }

  private void addSemestersToRepository() {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    SemesterFileHelper semesterFileHelper = new SemesterFileHelper(fileReader);
    SemesterCodeAssembler semesterCodeAssembler = new SemesterCodeAssembler();

    List<SemesterDto> semesters = semesterFileHelper.getSemesters();

    for (SemesterDto semester : semesters) {
      SemesterCode code = semesterCodeAssembler.assemble(semester.code);
      CustomDateTime start =
          new CustomDateTime(LocalDate.parse(semester.start, formatter).atTime(LocalTime.MIN));
      CustomDateTime end =
          new CustomDateTime(LocalDate.parse(semester.end, formatter).atTime(LocalTime.MAX));
      TimePeriod period = new TimePeriod(start, end);
      semesterRepository.save(code, period);
    }
  }
}
