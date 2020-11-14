package ca.ulaval.glo4003.reports.domain.scopes;

import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;

import ca.ulaval.glo4003.reports.domain.ReportPeriod;
import ca.ulaval.glo4003.times.domain.TimeMonth;
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
public class MonthlyScopeTest {

  @Mock private TimePeriod period;
  @Mock private TimeMonth firstMonth;
  @Mock private TimeMonth secondMonth;

  private MonthlyScope monthlyScope;

  @Before
  public void setUp() {
    monthlyScope = new MonthlyScope(period);
  }

  private void givenScopeWithSingleMonth() {
    reset(period);
    when(period.getMonths()).thenReturn(Collections.singletonList(firstMonth));
    setUp();
  }

  private void givenScopeWithMultipleMonths() {
    reset(period);
    when(period.getMonths()).thenReturn(Arrays.asList(firstMonth, secondMonth));
    setUp();
  }

  @Test
  public void givenSingleMonth_whenGettingReportPeriods_thenReturnSingleReportPeriod() {
    givenScopeWithSingleMonth();

    List<ReportPeriod> reportPeriods = monthlyScope.getReportPeriods();

    assertThat(reportPeriods).hasSize(1);
  }

  @Test
  public void givenSingleMonth_whenGettingReportPeriods_thenSetPeriodNameToThatMonth() {
    givenScopeWithSingleMonth();

    List<ReportPeriod> reportPeriods = monthlyScope.getReportPeriods();

    assertThat(reportPeriods.get(0).getName()).isEqualTo(firstMonth.toString());
  }

  @Test
  public void givenSingleMonth_whenGettingReportPeriods_thenSetPeriodToThatMonth() {
    givenScopeWithSingleMonth();

    List<ReportPeriod> reportPeriods = monthlyScope.getReportPeriods();

    assertThat(reportPeriods.get(0).getPeriod()).isEqualTo(firstMonth.toPeriod());
  }

  @Test
  public void givenMultipleMonths_whenGettingReportPeriods_thenReturnMultipleReportPeriods() {
    givenScopeWithMultipleMonths();

    List<ReportPeriod> reportPeriods = monthlyScope.getReportPeriods();

    assertThat(reportPeriods).hasSize(2);
  }

  @Test
  public void givenMultipleMonths_whenGettingReportPeriods_thenSetPeriodNamesToThoseMonths() {
    givenScopeWithMultipleMonths();

    List<ReportPeriod> reportPeriods = monthlyScope.getReportPeriods();

    assertThat(reportPeriods.get(0).getName()).isEqualTo(firstMonth.toString());
    assertThat(reportPeriods.get(1).getName()).isEqualTo(secondMonth.toString());
  }

  @Test
  public void givenMultipleMonths_whenGettingReportPeriods_thenSetPeriodsToThoseMonths() {
    givenScopeWithMultipleMonths();

    List<ReportPeriod> reportPeriods = monthlyScope.getReportPeriods();

    assertThat(reportPeriods.get(0).getPeriod()).isEqualTo(firstMonth.toPeriod());
    assertThat(reportPeriods.get(1).getPeriod()).isEqualTo(secondMonth.toPeriod());
  }
}
