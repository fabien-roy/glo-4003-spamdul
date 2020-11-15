package ca.ulaval.glo4003.reports.assemblers;

import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.when;

import ca.ulaval.glo4003.reports.api.dto.ReportMetricDataDto;
import ca.ulaval.glo4003.reports.domain.metrics.ReportMetricData;
import ca.ulaval.glo4003.reports.domain.metrics.ReportMetricType;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ReportMetricDataAssemblerTest {

  @Mock private ReportMetricData metric;
  @Mock private ReportMetricData otherMetric;

  private ReportMetricDataAssembler reportMetricDataAssembler;

  private final ReportMetricType metricType = ReportMetricType.PROFITS;
  private final ReportMetricType otherMetricType = ReportMetricType.GATE_ENTRIES;
  private final double metricValue = 1.0;
  private final double otherMetricValue = 2.0;
  private List<ReportMetricData> singleMetric;
  private List<ReportMetricData> multipleMetrics;

  @Before
  public void setUp() {
    reportMetricDataAssembler = new ReportMetricDataAssembler();

    when(metric.getType()).thenReturn(metricType);
    when(metric.getValue()).thenReturn(metricValue);
    when(otherMetric.getType()).thenReturn(otherMetricType);
    when(otherMetric.getValue()).thenReturn(otherMetricValue);

    singleMetric = Collections.singletonList(metric);
    multipleMetrics = Arrays.asList(metric, otherMetric);
  }

  @Test
  public void givenSingleMetric_whenAssemblingMany_thenAssembleASingleMetric() {
    List<ReportMetricDataDto> dtos = reportMetricDataAssembler.assembleMany(singleMetric);

    assertThat(dtos).hasSize(1);
  }

  @Test
  public void givenMultipleMetrics_whenAssemblingMany_thenAssembleMultipleMetrics() {
    List<ReportMetricDataDto> dtos = reportMetricDataAssembler.assembleMany(multipleMetrics);

    assertThat(dtos).hasSize(2);
  }

  @Test
  public void givenSingleMetric_whenAssemblingMany_thenSetName() {
    List<ReportMetricDataDto> dtos = reportMetricDataAssembler.assembleMany(singleMetric);

    assertThat(dtos.get(0).name).isEqualTo(metricType.toString());
  }

  @Test
  public void givenMultipleMetrics_whenAssemblingMany_thenSetNames() {
    List<ReportMetricDataDto> dtos = reportMetricDataAssembler.assembleMany(multipleMetrics);

    assertThat(dtos.get(0).name).isEqualTo(metricType.toString());
    assertThat(dtos.get(1).name).isEqualTo(otherMetricType.toString());
  }

  @Test
  public void givenSingleMetric_whenAssemblingMany_thenSetValue() {
    List<ReportMetricDataDto> dtos = reportMetricDataAssembler.assembleMany(singleMetric);

    assertThat(dtos.get(0).value).isEqualTo(metricValue);
  }

  @Test
  public void givenMultipleMetrics_whenAssemblingMany_thenSetValues() {
    List<ReportMetricDataDto> dtos = reportMetricDataAssembler.assembleMany(multipleMetrics);

    assertThat(dtos.get(0).value).isEqualTo(metricValue);
    assertThat(dtos.get(1).value).isEqualTo(otherMetricValue);
  }
}
