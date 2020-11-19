package ca.ulaval.glo4003.times;

import ca.ulaval.glo4003.files.domain.StringFileReader;
import ca.ulaval.glo4003.files.filesystem.JsonFileReader;
import ca.ulaval.glo4003.times.assemblers.CustomDateAssembler;
import ca.ulaval.glo4003.times.assemblers.CustomDateTimeAssembler;
import ca.ulaval.glo4003.times.assemblers.SemesterCodeAssembler;
import ca.ulaval.glo4003.times.assemblers.TimeOfDayAssembler;
import ca.ulaval.glo4003.times.domain.CustomDateTime;
import ca.ulaval.glo4003.times.domain.SemesterCode;
import ca.ulaval.glo4003.times.domain.SemestersRepository;
import ca.ulaval.glo4003.times.domain.TimePeriod;
import ca.ulaval.glo4003.times.filesystem.SemesterFileHelper;
import ca.ulaval.glo4003.times.filesystem.dto.SemesterDto;
import ca.ulaval.glo4003.times.infrastructure.SemestersInMemory;
import ca.ulaval.glo4003.times.services.SemesterService;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class TimeInjector {
  private final SemestersRepository semestersRepository = new SemestersInMemory();
  private final StringFileReader fileReader = new JsonFileReader();

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
    addSemestersToRepository();
    return new SemesterService(semestersRepository);
  }

  private void addSemestersToRepository() {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    SemesterFileHelper semesterFileHelper = new SemesterFileHelper(fileReader);
    SemesterCodeAssembler semesterCodeAssembler = new SemesterCodeAssembler();

    List<SemesterDto> semesters = semesterFileHelper.getSemesters();

    for (SemesterDto semester : semesters) {
      SemesterCode code = semesterCodeAssembler.assemble(semester.code);
      CustomDateTime start = new CustomDateTime(LocalDateTime.parse(semester.start, formatter));
      CustomDateTime end = new CustomDateTime(LocalDateTime.parse(semester.end, formatter));
      TimePeriod period = new TimePeriod(start, end);
      semestersRepository.save(code, period);
    }
  }
}
