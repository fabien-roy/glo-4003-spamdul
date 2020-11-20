package ca.ulaval.glo4003.reports.domain.scopes;

import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;

import ca.ulaval.glo4003.reports.domain.ReportPeriod;
import ca.ulaval.glo4003.times.domain.TimePeriod;
import ca.ulaval.glo4003.times.domain.TimeYear;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class YearlyScopeTest {

  @Mock private TimePeriod period;
  @Mock private TimeYear firstYear;
  @Mock private TimeYear secondYear;

  private YearlyScope yearlyScope;

  @Before
  public void setUp() {
    yearlyScope = new YearlyScope(period);
  }

  private void givenScopeWithSingleYear() {
    reset(period);
    when(period.getYears()).thenReturn(Collections.singletonList(firstYear));
    setUp();
  }

  private void givenScopeWithMultipleYears() {
    reset(period);
    when(period.getYears()).thenReturn(Arrays.asList(firstYear, secondYear));
    setUp();
  }

  @Test
  public void givenSingleYear_whenGettingReportPeriods_thenReturnSingleReportPeriod() {
    givenScopeWithSingleYear();

    List<ReportPeriod> reportPeriods = yearlyScope.getReportPeriods();

    assertThat(reportPeriods).hasSize(1);
  }

  @Test
  public void givenSingleYear_whenGettingReportPeriods_thenSetPeriodNameToThatYear() {
    givenScopeWithSingleYear();

    List<ReportPeriod> reportPeriods = yearlyScope.getReportPeriods();

    assertThat(reportPeriods.get(0).getName()).isEqualTo(firstYear.toString());
  }

  @Test
  public void givenSingleYear_whenGettingReportPeriods_thenSetPeriodToThatYear() {
    givenScopeWithSingleYear();

    List<ReportPeriod> reportPeriods = yearlyScope.getReportPeriods();

    assertThat(reportPeriods.get(0).getPeriod()).isEqualTo(firstYear.toPeriod());
  }

  @Test
  public void givenMultipleYears_whenGettingReportPeriods_thenReturnMultipleReportPeriods() {
    givenScopeWithMultipleYears();

    List<ReportPeriod> reportPeriods = yearlyScope.getReportPeriods();

    assertThat(reportPeriods).hasSize(2);
  }

  @Test
  public void givenMultipleYears_whenGettingReportPeriods_thenSetPeriodNamesToThoseYears() {
    givenScopeWithMultipleYears();

    List<ReportPeriod> reportPeriods = yearlyScope.getReportPeriods();

    assertThat(reportPeriods.get(0).getName()).isEqualTo(firstYear.toString());
    assertThat(reportPeriods.get(1).getName()).isEqualTo(secondYear.toString());
  }

  @Test
  public void givenMultipleYears_whenGettingReportPeriods_thenSetPeriodsToThoseYears() {
    givenScopeWithMultipleYears();

    List<ReportPeriod> reportPeriods = yearlyScope.getReportPeriods();

    assertThat(reportPeriods.get(0).getPeriod()).isEqualTo(firstYear.toPeriod());
    assertThat(reportPeriods.get(1).getPeriod()).isEqualTo(secondYear.toPeriod());
  }
}
