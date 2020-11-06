package ca.ulaval.glo4003.reports.domain.scopes;

import static com.google.common.truth.Truth.assertThat;

import ca.ulaval.glo4003.times.domain.TimePeriod;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ReportScopeBuilderTest {

  @Mock private TimePeriod timePeriod; // TODO : Use TimePeriodBuilder (you're in for a ride)

  private ReportScopeBuilder reportScopeBuilder;

  @Before
  public void setUp() {
    reportScopeBuilder = new ReportScopeBuilder();
  }

  @Test
  public void givenPeriod_whenBuilding_thenBuildReportScopeWithPeriod() {
    ReportScope reportScope = reportScopeBuilder.aScope().withPeriod(timePeriod).build();

    assertThat(reportScope.getPeriod()).isSameInstanceAs(timePeriod);
  }

  @Test
  public void givenYearlyReportScopeType_whenBuilding_thenBuildYearlyReportScope() {
    ReportScopeType yearlyScopeType = ReportScopeType.YEARLY;

    ReportScope reportScope = reportScopeBuilder.aScope().withType(yearlyScopeType).build();

    assertThat(reportScope).isInstanceOf(YearlyScope.class);
  }

  @Test
  public void givenMonthlyReportScopeType_whenBuilding_thenBuildMonthlyReportScope() {
    ReportScopeType monthlyScopeType = ReportScopeType.MONTHLY;

    ReportScope reportScope = reportScopeBuilder.aScope().withType(monthlyScopeType).build();

    assertThat(reportScope).isInstanceOf(MonthlyScope.class);
  }

  @Test
  public void givenDailyReportScopeType_whenBuilding_thenBuildDailyReportScope() {
    ReportScopeType dailyScopeType = ReportScopeType.DAILY;

    ReportScope reportScope = reportScopeBuilder.aScope().withType(dailyScopeType).build();

    assertThat(reportScope).isInstanceOf(DailyScope.class);
  }
}
