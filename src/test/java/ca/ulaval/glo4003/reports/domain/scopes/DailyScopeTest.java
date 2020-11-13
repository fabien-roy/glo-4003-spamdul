package ca.ulaval.glo4003.reports.domain.scopes;

import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;

import ca.ulaval.glo4003.reports.domain.ReportPeriod;
import ca.ulaval.glo4003.times.domain.TimeDay;
import ca.ulaval.glo4003.times.domain.TimePeriod;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class DailyScopeTest {

  @Mock private TimePeriod period;
  @Mock private TimeDay firstDay;
  @Mock private TimeDay secondDay;

  private DailyScope dailyScope;

  @Before
  public void setUp() {
    dailyScope = new DailyScope(period);
  }

  private void givenScopeWithSingleDay() {
    reset(period);
    when(period.getDays()).thenReturn(Collections.singletonList(firstDay));
    setUp();
  }

  private void givenScopeWithMultipleDays() {
    reset(period);
    when(period.getDays()).thenReturn(Arrays.asList(firstDay, secondDay));
    setUp();
  }

  @Test
  public void givenSingleDay_whenGettingReportPeriods_thenReturnSingleReportPeriod() {
    givenScopeWithSingleDay();

    List<ReportPeriod> reportPeriods = dailyScope.getReportPeriods();

    assertThat(reportPeriods).hasSize(1);
  }

  @Test
  public void givenSingleDay_whenGettingReportPeriods_thenSetPeriodNameToThatDai() {
    givenScopeWithSingleDay();

    List<ReportPeriod> reportPeriods = dailyScope.getReportPeriods();

    assertThat(reportPeriods.get(0).getName()).isEqualTo(firstDay.toString());
  }

  @Test
  public void givenSingleDay_whenGettingReportPeriods_thenSetPeriodToThatDai() {
    givenScopeWithSingleDay();

    List<ReportPeriod> reportPeriods = dailyScope.getReportPeriods();

    assertThat(reportPeriods.get(0).getPeriod()).isEqualTo(firstDay.toPeriod());
  }

  @Test
  public void givenMultipleDays_whenGettingReportPeriods_thenReturnMultipleReportPeriods() {
    givenScopeWithMultipleDays();

    List<ReportPeriod> reportPeriods = dailyScope.getReportPeriods();

    assertThat(reportPeriods).hasSize(2);
  }

  @Test
  public void givenMultipleDays_whenGettingReportPeriods_thenSetPeriodNamesToThoseDays() {
    givenScopeWithMultipleDays();

    List<ReportPeriod> reportPeriods = dailyScope.getReportPeriods();

    assertThat(reportPeriods.get(0).getName()).isEqualTo(firstDay.toString());
    assertThat(reportPeriods.get(1).getName()).isEqualTo(secondDay.toString());
  }

  @Test
  public void givenMultipleDays_whenGettingReportPeriods_thenSetPeriodsToThoseDays() {
    givenScopeWithMultipleDays();

    List<ReportPeriod> reportPeriods = dailyScope.getReportPeriods();

    assertThat(reportPeriods.get(0).getPeriod()).isEqualTo(firstDay.toPeriod());
    assertThat(reportPeriods.get(1).getPeriod()).isEqualTo(secondDay.toPeriod());
  }
}
