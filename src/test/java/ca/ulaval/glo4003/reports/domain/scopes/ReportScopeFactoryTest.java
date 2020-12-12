package ca.ulaval.glo4003.reports.domain.scopes;

import static ca.ulaval.glo4003.reports.helpers.ReportScopeMother.createYear;
import static ca.ulaval.glo4003.times.helpers.CustomDateTimeMother.createMonth;
import static com.google.common.truth.Truth.assertThat;

import ca.ulaval.glo4003.times.domain.TimeMonth;
import ca.ulaval.glo4003.times.domain.TimePeriod;
import ca.ulaval.glo4003.times.domain.TimeYear;
import org.junit.Before;
import org.junit.Test;

public class ReportScopeFactoryTest {

  private ReportScopeFactory reportScopeFactory;

  private final int year = createYear();
  private final String month = createMonth().toString();

  @Before
  public void setUp() {
    reportScopeFactory = new ReportScopeFactory();
  }

  @Test
  public void whenCreatingYearlyScope_thenCreateYearlyScope() {
    ReportScope scope = reportScopeFactory.createYearlyScope(year);

    assertThat(scope).isInstanceOf(YearlyScope.class);
  }

  @Test
  public void whenCreatingYearlyScope_thenUseGivenYearPeriod() {
    TimePeriod givenYearPeriod = new TimeYear(year).toPeriod();

    ReportScope scope = reportScopeFactory.createYearlyScope(year);

    assertThat(scope.getPeriod()).isEqualTo(givenYearPeriod);
  }

  @Test
  public void whenCreatingMonthlyScope_thenCreateMonthlyScope() {
    ReportScope scope = reportScopeFactory.createMonthlyScope();

    assertThat(scope).isInstanceOf(MonthlyScope.class);
  }

  @Test
  public void whenCreatingMonthlyScope_thenUseCurrentYearPeriod() {
    TimePeriod currentYearPeriod = TimeYear.now().toPeriod();

    ReportScope scope = reportScopeFactory.createMonthlyScope();

    assertThat(scope.getPeriod()).isEqualTo(currentYearPeriod);
  }

  @Test
  public void whenCreatingDailyScope_thenCreateDailyScope() {
    ReportScope scope = reportScopeFactory.createDailyScope(month);

    assertThat(scope).isInstanceOf(DailyScope.class);
  }

  @Test
  public void whenCreatingDailyScope_thenUseGivenMonthPeriod() {
    TimePeriod givenMonthPeriod = new TimeMonth(month).toPeriod();

    ReportScope scope = reportScopeFactory.createDailyScope(month);

    assertThat(scope.getPeriod()).isEqualTo(givenMonthPeriod);
  }
}
