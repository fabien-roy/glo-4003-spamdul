package ca.ulaval.glo4003.reports.infrastructure;

import static ca.ulaval.glo4003.reports.helpers.ReportAggregateFunctionMother.createReportAggregateFunctionType;
import static ca.ulaval.glo4003.reports.helpers.ReportMetricMother.createReportMetricType;
import static ca.ulaval.glo4003.reports.helpers.ReportPeriodBuilder.aReportPeriod;
import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.when;

import ca.ulaval.glo4003.reports.domain.ReportPeriod;
import ca.ulaval.glo4003.reports.domain.ReportSummaryFactory;
import ca.ulaval.glo4003.reports.domain.aggregatefunctions.ReportAggregateFunctionType;
import ca.ulaval.glo4003.reports.domain.metrics.ReportMetricType;
import ca.ulaval.glo4003.reports.infrastructure.aggregatefunctions.ReportAggregateFunctionFactoryInMemory;
import ca.ulaval.glo4003.reports.infrastructure.aggregatefunctions.ReportAggregateFunctionInMemory;
import java.util.Collections;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ReportSummaryFactoryInMemoryTest {

  @Mock private ReportAggregateFunctionFactoryInMemory aggregateFunctionFactory;
  @Mock private ReportAggregateFunctionInMemory aggregateFunction;

  private ReportSummaryFactory summaryFactory;

  private final ReportPeriod period = aReportPeriod().build();
  private final ReportPeriod aggregatedPeriod = aReportPeriod().build();
  private final List<ReportPeriod> periods = Collections.singletonList(period);
  private final ReportAggregateFunctionType aggregateFunctionType =
      createReportAggregateFunctionType();
  private final List<ReportAggregateFunctionType> aggregateFunctionTypes =
      Collections.singletonList(aggregateFunctionType);
  private final ReportMetricType metricType = createReportMetricType();

  @Before
  public void setUp() {
    summaryFactory = new ReportSummaryFactoryInMemory(aggregateFunctionFactory);

    when(aggregateFunctionFactory.createMany(Collections.emptyList()))
        .thenReturn(Collections.emptyList());
    when(aggregateFunctionFactory.createMany(aggregateFunctionTypes))
        .thenReturn(Collections.singletonList(aggregateFunction));

    when(aggregateFunction.aggregate(periods, metricType)).thenReturn(aggregatedPeriod);
  }

  @Test
  public void givenNoPeriod_whenBuilding_thenReturnNoPeriod() {
    List<ReportPeriod> aggregatedPeriods =
        summaryFactory.create(aggregateFunctionTypes, Collections.emptyList(), metricType);

    assertThat(aggregatedPeriods).hasSize(1);
    assertThat(aggregatedPeriods.get(0)).isNull();
  }

  @Test
  public void givenNoAggregateFunction_whenBuilding_thenReturnNoPeriod() {
    List<ReportPeriod> aggregatedPeriods =
        summaryFactory.create(Collections.emptyList(), periods, metricType);

    assertThat(aggregatedPeriods).hasSize(0);
  }

  @Test
  public void
      givenPeriodsAndAggregateFunctionsAndMetric_whenBuilding_thenReturnAggregatedPeriods() {
    List<ReportPeriod> aggregatedPeriods =
        summaryFactory.create(aggregateFunctionTypes, periods, metricType);

    assertThat(aggregatedPeriods).hasSize(1);
    assertThat(aggregatedPeriods.get(0)).isSameInstanceAs(aggregatedPeriod);
  }
}
