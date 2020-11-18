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

public class AverageAggregateFunctionInMemoryTest {

  private AggregateFunction aggregateFunction;

  private final ReportMetricType metricType = ReportMetricType.PROFITS;
  private final ReportMetricType otherMetricType = ReportMetricType.GATE_ENTRIES;
  private final ReportMetricData firstMetricData = aReportMetricData().withType(metricType).build();
  private final ReportMetricData secondMetricData =
      aReportMetricData().withType(metricType).build();
  private final ReportMetricData otherMetricData =
      aReportMetricData().withType(otherMetricType).build();

  @Before
  public void setUp() {
    aggregateFunction = new AverageAggregateFunctionInMemory();
  }

  @Test
  public void whenAggregating_thenSetPeriodNameToAverage() {
    List<ReportPeriod> periods = Collections.emptyList();

    ReportPeriod period = aggregateFunction.aggregate(periods, metricType);

    assertThat(period.getName()).isEqualTo("average");
  }

  @Test
  public void whenAggregating_thenSetSingleData() {
    List<ReportPeriod> periods = Collections.emptyList();

    ReportPeriod period = aggregateFunction.aggregate(periods, metricType);

    assertThat(period.getData()).hasSize(1);
  }

  @Test
  public void whenAggregating_thenSetNoDimensions() {
    List<ReportPeriod> periods = Collections.emptyList();

    ReportPeriod period = aggregateFunction.aggregate(periods, metricType);

    assertThat(period.getData().get(0).getDimensions()).hasSize(0);
  }

  @Test
  public void whenAggregating_thenSetMetricWithType() {
    List<ReportPeriod> periods = Collections.emptyList();

    ReportPeriod period = aggregateFunction.aggregate(periods, metricType);

    assertThat(period.getData().get(0).getMetrics()).hasSize(1);
    assertThat(period.getData().get(0).getMetrics().get(0).getType()).isEqualTo(metricType);
  }

  @Test
  public void givenNoPeriod_whenAggregating_thenReturnZero() {
    double expectedAverage = 0d;
    List<ReportPeriod> periods = Collections.emptyList();

    ReportPeriod period = aggregateFunction.aggregate(periods, metricType);

    assertThat(period.getData().get(0).getMetrics().get(0).getValue()).isEqualTo(expectedAverage);
  }

  @Test
  public void givenSinglePeriodWithMetricType_whenAggregating_thenReturnThatPeriodMetricValue() {
    double expectedAverage = firstMetricData.getValue();
    ReportPeriodData periodDataWithMetricType =
        aReportPeriodData().withMetrics(Collections.singletonList(firstMetricData)).build();
    ReportPeriod periodWithMetricType =
        aReportPeriod().withData(Collections.singletonList(periodDataWithMetricType)).build();
    List<ReportPeriod> periods = Collections.singletonList(periodWithMetricType);

    ReportPeriod period = aggregateFunction.aggregate(periods, metricType);

    assertThat(period.getData().get(0).getMetrics().get(0).getValue()).isEqualTo(expectedAverage);
  }

  @Test
  public void givenSinglePeriodWithOtherMetricType_whenAggregating_thenReturnZero() {
    double expectedAverage = 0d;
    ReportPeriodData periodDataWithOtherMetricType =
        aReportPeriodData().withMetrics(Collections.singletonList(otherMetricData)).build();
    ReportPeriod periodWithOtherMetricType =
        aReportPeriod().withData(Collections.singletonList(periodDataWithOtherMetricType)).build();
    List<ReportPeriod> periods = Collections.singletonList(periodWithOtherMetricType);

    ReportPeriod period = aggregateFunction.aggregate(periods, metricType);

    assertThat(period.getData().get(0).getMetrics().get(0).getValue()).isEqualTo(expectedAverage);
  }

  @Test
  public void
      givenMultiplePeriodsWithMetricType_whenAggregating_thenReturnPeriodWithAverageOfMetricValue() {
    double expectedAverage = (firstMetricData.getValue() + secondMetricData.getValue()) / 2;
    ReportPeriodData firstPeriodDataWithMetricType =
        aReportPeriodData().withMetrics(Collections.singletonList(firstMetricData)).build();
    ReportPeriodData secondPeriodDataWithMetricType =
        aReportPeriodData().withMetrics(Collections.singletonList(secondMetricData)).build();
    ReportPeriod firstPeriodWithMetricType =
        aReportPeriod().withData(Collections.singletonList(firstPeriodDataWithMetricType)).build();
    ReportPeriod secondPeriodWithMetricType =
        aReportPeriod().withData(Collections.singletonList(secondPeriodDataWithMetricType)).build();
    List<ReportPeriod> periods =
        Arrays.asList(firstPeriodWithMetricType, secondPeriodWithMetricType);

    ReportPeriod period = aggregateFunction.aggregate(periods, metricType);

    assertThat(period.getData().get(0).getMetrics().get(0).getValue()).isEqualTo(expectedAverage);
  }

  @Test
  public void
      givenMultiplePeriodsWithDifferentMetricTypes_whenAggregating_thenReturnAverageOfMetricValueForMetricType() {
    double expectedAverage = firstMetricData.getValue();
    ReportPeriodData periodDataWithMetricType =
        aReportPeriodData().withMetrics(Collections.singletonList(firstMetricData)).build();
    ReportPeriodData periodDataWithOtherMetricType =
        aReportPeriodData().withMetrics(Collections.singletonList(otherMetricData)).build();
    ReportPeriod periodWithMetricType =
        aReportPeriod().withData(Collections.singletonList(periodDataWithMetricType)).build();
    ReportPeriod periodWithOtherMetricType =
        aReportPeriod().withData(Collections.singletonList(periodDataWithOtherMetricType)).build();
    List<ReportPeriod> periods = Arrays.asList(periodWithMetricType, periodWithOtherMetricType);

    ReportPeriod period = aggregateFunction.aggregate(periods, metricType);

    assertThat(period.getData().get(0).getMetrics().get(0).getValue()).isEqualTo(expectedAverage);
  }
}
