package ca.ulaval.glo4003.times.services;

import static ca.ulaval.glo4003.times.helpers.SemesterCodeMother.createSemesterCode;
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
  private final String semester = createSemesterCode().toString();
  private final String otherSemester = createSemesterCode().toString();

  @Mock private SemesterRepository semesterRepository;
  @Mock private SemesterCodeConverter semesterCodeConverter;
  @Mock private SemesterCode semesterCode;
  @Mock private SemesterCode otherSemesterCode;
  @Mock private TimePeriod timePeriod;
  @Mock private TimePeriod otherTimePeriod;

  private SemesterService semesterService;

  @Before
  public void setUp() {
    when(semesterCodeConverter.convert(semester)).thenReturn(semesterCode);
    when(semesterRepository.findByCode(semesterCode)).thenReturn(timePeriod);
    when(semesterCodeConverter.convert(otherSemester)).thenReturn(otherSemesterCode);
    when(semesterRepository.findByCode(otherSemesterCode)).thenReturn(otherTimePeriod);

    semesterService = new SemesterService(semesterRepository, semesterCodeConverter);
  }

  @Test
  public void whenGettingSemester_thenReturnGoodTimePeriod() {
    List<TimePeriod> timePeriods =
        semesterService.getSemester(new String[] {semester, otherSemester});

    assertThat(timePeriods).contains(timePeriod);
    assertThat(timePeriods).contains(otherTimePeriod);
    assertThat(timePeriods.size()).isEqualTo(2);
  }
}
