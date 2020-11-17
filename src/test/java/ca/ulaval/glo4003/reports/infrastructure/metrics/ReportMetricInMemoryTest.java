package ca.ulaval.glo4003.reports.infrastructure.metrics;

import static ca.ulaval.glo4003.reports.helpers.ReportPeriodDataBuilder.aReportPeriodData;
import static com.google.common.truth.Truth.assertThat;

import ca.ulaval.glo4003.reports.domain.ReportPeriodData;
import ca.ulaval.glo4003.reports.domain.metrics.ReportMetric;
import ca.ulaval.glo4003.reports.domain.metrics.ReportMetricType;
import org.junit.Before;
import org.junit.Test;

public abstract class ReportMetricInMemoryTest {

  protected ReportMetric metric;
  protected ReportPeriodData data;

  protected abstract ReportMetricType metricType();

  @Before
  public void setUpData() {
    data = aReportPeriodData().build();
  }

  @Test
  public void whenCalculating_thenCalculateWithMetricType() {
    metric.calculate(data);

    assertThat(data.getMetrics()).hasSize(1);
    assertThat(data.getMetrics().get(0).getType()).isEqualTo(metricType());
  }
}
