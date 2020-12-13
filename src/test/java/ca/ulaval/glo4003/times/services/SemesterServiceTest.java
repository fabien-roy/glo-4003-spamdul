package ca.ulaval.glo4003.times.services;

import static ca.ulaval.glo4003.times.helpers.TimePeriodBuilder.aTimePeriod;
import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.when;

import ca.ulaval.glo4003.times.domain.SemesterCode;
import ca.ulaval.glo4003.times.domain.SemesterRepository;
import ca.ulaval.glo4003.times.domain.TimePeriod;
import ca.ulaval.glo4003.times.services.converters.SemesterCodeConverter;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class SemesterServiceTest {
  @Mock private SemesterRepository semesterRepository;
  @Mock private SemesterCodeConverter semesterCodeConverter;

  private SemesterService semesterService;

  private final SemesterCode semesterCode = new SemesterCode("A20");
  private final SemesterCode otherSemesterCode = new SemesterCode("H20");
  private final TimePeriod timePeriod = aTimePeriod().build();
  private final TimePeriod otherTimePeriod = aTimePeriod().build();

  @Before
  public void setUp() {
    semesterService = new SemesterService(semesterRepository, semesterCodeConverter);

    when(semesterCodeConverter.convert(semesterCode.toString())).thenReturn(semesterCode);
    when(semesterCodeConverter.convert(otherSemesterCode.toString())).thenReturn(otherSemesterCode);
    when(semesterRepository.findByCode(semesterCode)).thenReturn(timePeriod);
    when(semesterRepository.findByCode(otherSemesterCode)).thenReturn(otherTimePeriod);
  }

  @Test
  public void whenGettingSemester_thenReturnGoodTimePeriod() {
    List<TimePeriod> timePeriods =
        semesterService.getSemester(
            new String[] {semesterCode.toString(), otherSemesterCode.toString()});

    assertThat(timePeriods).hasSize(2);
    assertThat(timePeriods).contains(timePeriod);
    assertThat(timePeriods).contains(otherTimePeriod);
  }

  @Test
  public void givenNullSemesters_whenGettingSemester_thenReturnEmptyList() {
    List<TimePeriod> timePeriods = semesterService.getSemester(null);

    assertThat(timePeriods).hasSize(0);
  }
}
