package ca.ulaval.glo4003.reports.infrastructure;

import static ca.ulaval.glo4003.reports.helpers.ReportAggregateFunctionMother.createReportAggregateFunctionType;
import static ca.ulaval.glo4003.reports.helpers.ReportMetricMother.createReportMetricType;
import static ca.ulaval.glo4003.reports.helpers.ReportPeriodBuilder.aReportPeriod;
import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.when;

import ca.ulaval.glo4003.reports.domain.ReportPeriod;
import ca.ulaval.glo4003.reports.domain.ReportSummaryBuilder;
import ca.ulaval.glo4003.reports.domain.aggregatefunctions.ReportAggregateFunctionType;
import ca.ulaval.glo4003.reports.domain.metrics.ReportMetricType;
import ca.ulaval.glo4003.reports.infrastructure.aggregatefunctions.ReportAggregateFunctionBuilderInMemory;
import ca.ulaval.glo4003.reports.infrastructure.aggregatefunctions.ReportAggregateFunctionInMemory;
import java.util.Collections;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ReportSummaryBuilderInMemoryTest {

  @Mock private ReportAggregateFunctionBuilderInMemory aggregateFunctionBuilder;
  @Mock private ReportAggregateFunctionBuilderInMemory aggregateFunctionBuilderWithoutType;
  @Mock private ReportAggregateFunctionBuilderInMemory aggregateFunctionBuilderWithTypes;
  @Mock private ReportAggregateFunctionInMemory aggregateFunction;

  private ReportSummaryBuilder summaryBuilder;

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
    summaryBuilder = new ReportSummaryBuilderInMemory(aggregateFunctionBuilder);

    when(aggregateFunctionBuilder.someAggregateFunctions()).thenReturn(aggregateFunctionBuilder);
    when(aggregateFunctionBuilder.withTypes(Collections.emptyList()))
        .thenReturn(aggregateFunctionBuilderWithoutType);
    when(aggregateFunctionBuilder.withTypes(aggregateFunctionTypes))
        .thenReturn(aggregateFunctionBuilderWithTypes);
    when(aggregateFunctionBuilderWithoutType.buildMany()).thenReturn(Collections.emptyList());
    when(aggregateFunctionBuilderWithTypes.buildMany())
        .thenReturn(Collections.singletonList(aggregateFunction));

    when(aggregateFunction.aggregate(periods, metricType)).thenReturn(aggregatedPeriod);
  }

  @Test
  public void givenNoPeriod_whenBuilding_thenReturnNoPeriod() {
    List<ReportPeriod> aggregatedPeriods =
        summaryBuilder
            .aReportSummary()
            .withAggregateFunctions(aggregateFunctionTypes)
            .withMetric(metricType)
            .build();

    assertThat(aggregatedPeriods).hasSize(1);
    assertThat(aggregatedPeriods.get(0)).isNull();
  }

  @Test
  public void givenNoAggregateFunction_whenBuilding_thenReturnNoPeriod() {
    List<ReportPeriod> aggregatedPeriods =
        summaryBuilder.aReportSummary().withPeriods(periods).withMetric(metricType).build();

    assertThat(aggregatedPeriods).hasSize(0);
  }

  @Test
  public void
      givenPeriodsAndAggregateFunctionsAndMetric_whenBuilding_thenReturnAggregatedPeriods() {
    List<ReportPeriod> aggregatedPeriods =
        summaryBuilder
            .aReportSummary()
            .withPeriods(periods)
            .withAggregateFunctions(aggregateFunctionTypes)
            .withMetric(metricType)
            .build();

    assertThat(aggregatedPeriods).hasSize(1);
    assertThat(aggregatedPeriods.get(0)).isSameInstanceAs(aggregatedPeriod);
  }
}
