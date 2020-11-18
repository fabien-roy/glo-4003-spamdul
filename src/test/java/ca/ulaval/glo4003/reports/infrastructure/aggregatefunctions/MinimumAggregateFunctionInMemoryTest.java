package ca.ulaval.glo4003.reports.infrastructure.aggregatefunctions;

import static ca.ulaval.glo4003.reports.helpers.ReportMetricDataBuilder.aReportMetricData;
import static ca.ulaval.glo4003.reports.helpers.ReportPeriodBuilder.aReportPeriod;
import static ca.ulaval.glo4003.reports.helpers.ReportPeriodDataBuilder.aReportPeriodData;
import static com.google.common.truth.Truth.assertThat;

import ca.ulaval.glo4003.reports.domain.ReportPeriod;
import ca.ulaval.glo4003.reports.domain.ReportPeriodData;
import ca.ulaval.glo4003.reports.domain.aggregatefunctions.AggregateFunction;
import ca.ulaval.glo4003.reports.domain.metrics.ReportMetricData;
import ca.ulaval.glo4003.reports.domain.metrics.ReportMetricType;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class MinimumAggregateFunctionInMemoryTest {

  private AggregateFunction aggregateFunction;

  private final ReportMetricType metricType = ReportMetricType.PROFITS;
  private final ReportMetricType otherMetricType = ReportMetricType.GATE_ENTRIES;
  private final ReportMetricData highestMetricData =
      aReportMetricData().withType(metricType).withValue(10).build();
  private final ReportMetricData lowestMetricData =
      aReportMetricData().withType(metricType).withValue(5).build();
  private final ReportMetricData otherMetricData =
      aReportMetricData().withType(otherMetricType).withValue(20).build();

  @Before
  public void setUp() {
    aggregateFunction = new MinimumAggregateFunctionInMemory();
  }

  @Test
  public void givenNoPeriod_whenAggregating_thenReturnNull() {
    List<ReportPeriod> periods = Collections.emptyList();

    ReportPeriod period = aggregateFunction.aggregate(periods, metricType);

    assertThat(period).isNull();
  }

  @Test
  public void givenSinglePeriodWithMetricType_whenAggregating_thenReturnThatPeriod() {
    ReportPeriodData periodDataWithMetricType =
        aReportPeriodData().withMetrics(Collections.singletonList(highestMetricData)).build();
    ReportPeriod periodWithMetricType =
        aReportPeriod().withData(Collections.singletonList(periodDataWithMetricType)).build();
    List<ReportPeriod> periods = Collections.singletonList(periodWithMetricType);

    ReportPeriod period = aggregateFunction.aggregate(periods, metricType);

    assertThat(period).isSameInstanceAs(periodWithMetricType);
  }

  @Test
  public void givenSinglePeriodWithOtherMetricType_whenAggregating_thenReturnNull() {
    ReportPeriodData periodDataWithOtherMetricType =
        aReportPeriodData().withMetrics(Collections.singletonList(otherMetricData)).build();
    ReportPeriod periodWithOtherMetricType =
        aReportPeriod().withData(Collections.singletonList(periodDataWithOtherMetricType)).build();
    List<ReportPeriod> periods = Collections.singletonList(periodWithOtherMetricType);

    ReportPeriod period = aggregateFunction.aggregate(periods, metricType);

    assertThat(period).isNull();
  }

  @Test
  public void
      givenMultiplePeriodsWithMetricType_whenAggregating_thenReturnPeriodWithLowestMetricValue() {
    ReportPeriodData periodDataWithHighestMetricType =
        aReportPeriodData().withMetrics(Collections.singletonList(highestMetricData)).build();
    ReportPeriodData periodDataWithLowestMetricType =
        aReportPeriodData().withMetrics(Collections.singletonList(lowestMetricData)).build();
    ReportPeriod periodWithHighestMetricType =
        aReportPeriod()
            .withData(Collections.singletonList(periodDataWithHighestMetricType))
            .build();
    ReportPeriod periodWithLowestMetricType =
        aReportPeriod().withData(Collections.singletonList(periodDataWithLowestMetricType)).build();
    List<ReportPeriod> periods =
        Arrays.asList(periodWithHighestMetricType, periodWithLowestMetricType);

    ReportPeriod period = aggregateFunction.aggregate(periods, metricType);

    assertThat(period).isSameInstanceAs(periodWithLowestMetricType);
  }

  @Test
  public void
      givenMultiplePeriodsWithDifferentMetricTypes_whenAggregating_thenReturnPeriodWithLowestMetricValueForMetricType() {
    ReportPeriodData periodDataWithMetricType =
        aReportPeriodData().withMetrics(Collections.singletonList(lowestMetricData)).build();
    ReportPeriodData periodDataWithOtherMetricType =
        aReportPeriodData().withMetrics(Collections.singletonList(otherMetricData)).build();
    ReportPeriod periodWithMetricType =
        aReportPeriod().withData(Collections.singletonList(periodDataWithMetricType)).build();
    ReportPeriod periodWithOtherMetricType =
        aReportPeriod().withData(Collections.singletonList(periodDataWithOtherMetricType)).build();
    List<ReportPeriod> periods = Arrays.asList(periodWithMetricType, periodWithOtherMetricType);

    ReportPeriod period = aggregateFunction.aggregate(periods, metricType);

    assertThat(period).isSameInstanceAs(periodWithMetricType);
  }
}
