package ca.ulaval.glo4003.reports.services.assemblers;

import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import ca.ulaval.glo4003.reports.domain.ReportPeriodData;
import ca.ulaval.glo4003.reports.domain.dimensions.ReportDimensionData;
import ca.ulaval.glo4003.reports.domain.metrics.ReportMetricData;
import ca.ulaval.glo4003.reports.services.dto.ReportDimensionDataDto;
import ca.ulaval.glo4003.reports.services.dto.ReportMetricDataDto;
import ca.ulaval.glo4003.reports.services.dto.ReportPeriodDataDto;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ReportPeriodDataAssemblerTest {

  @Mock private ReportDimensionDataAssembler reportDimensionDataAssembler;
  @Mock private ReportMetricDataAssembler reportMetricDataAssembler;
  @Mock private ReportPeriodData data;
  @Mock private ReportPeriodData otherData;

  private ReportPeriodDataAssembler reportPeriodDataAssembler;

  private final List<ReportDimensionData> dimensions =
      Collections.singletonList(mock(ReportDimensionData.class));
  private final List<ReportDimensionData> otherDimensions =
      Collections.singletonList(mock(ReportDimensionData.class));
  private final List<ReportMetricData> metrics =
      Collections.singletonList(mock(ReportMetricData.class));
  private final List<ReportMetricData> otherMetrics =
      Collections.singletonList(mock(ReportMetricData.class));
  private final List<ReportDimensionDataDto> dimensionDtos =
      Collections.singletonList(mock(ReportDimensionDataDto.class));
  private final List<ReportDimensionDataDto> otherDimensionDtos =
      Arrays.asList(mock(ReportDimensionDataDto.class), mock(ReportDimensionDataDto.class));
  private final List<ReportMetricDataDto> metricDtos =
      Collections.singletonList(mock(ReportMetricDataDto.class));
  private final List<ReportMetricDataDto> otherMetricDtos =
      Arrays.asList(mock(ReportMetricDataDto.class), mock(ReportMetricDataDto.class));
  private List<ReportPeriodData> singleData;
  private List<ReportPeriodData> multipleData;

  @Before
  public void setUp() {
    reportPeriodDataAssembler =
        new ReportPeriodDataAssembler(reportDimensionDataAssembler, reportMetricDataAssembler);

    singleData = Collections.singletonList(data);
    multipleData = Arrays.asList(data, otherData);

    when(data.getDimensions()).thenReturn(dimensions);
    when(otherData.getDimensions()).thenReturn(otherDimensions);
    when(reportDimensionDataAssembler.assembleMany(dimensions)).thenReturn(dimensionDtos);
    when(reportDimensionDataAssembler.assembleMany(otherDimensions)).thenReturn(otherDimensionDtos);

    when(data.getMetrics()).thenReturn(metrics);
    when(otherData.getMetrics()).thenReturn(otherMetrics);
    when(reportMetricDataAssembler.assembleMany(metrics)).thenReturn(metricDtos);
    when(reportMetricDataAssembler.assembleMany(otherMetrics)).thenReturn(otherMetricDtos);
  }

  @Test
  public void givenSingleData_whenAssemblingMany_thenAssembleASingleData() {
    List<ReportPeriodDataDto> dtos = reportPeriodDataAssembler.assembleMany(singleData);

    assertThat(dtos).hasSize(1);
  }

  @Test
  public void givenMultipleData_whenAssemblingMany_thenAssembleMultipleData() {
    List<ReportPeriodDataDto> dtos = reportPeriodDataAssembler.assembleMany(multipleData);

    assertThat(dtos).hasSize(2);
  }

  @Test
  public void givenSingleData_whenAssemblingMany_thenAssembleDimensions() {
    List<ReportPeriodDataDto> dtos = reportPeriodDataAssembler.assembleMany(singleData);

    assertThat(dtos.get(0).dimensions).hasSize(dimensionDtos.size());
    assertThat(dtos.get(0).dimensions.get(0)).isSameInstanceAs(dimensionDtos.get(0));
  }

  @Test
  public void givenMultipleData_whenAssemblingMany_thenAssembleDimensions() {
    List<ReportPeriodDataDto> dtos = reportPeriodDataAssembler.assembleMany(multipleData);

    assertThat(dtos.get(0).dimensions).hasSize(dimensionDtos.size());
    assertThat(dtos.get(1).dimensions).hasSize(otherDimensionDtos.size());
    assertThat(dtos.get(0).dimensions.get(0)).isSameInstanceAs(dimensionDtos.get(0));
    assertThat(dtos.get(1).dimensions.get(0)).isSameInstanceAs(otherDimensionDtos.get(0));
    assertThat(dtos.get(1).dimensions.get(1)).isSameInstanceAs(otherDimensionDtos.get(1));
  }

  @Test
  public void givenSingleData_whenAssemblingMany_thenAssembleMetrics() {
    List<ReportPeriodDataDto> dtos = reportPeriodDataAssembler.assembleMany(singleData);

    assertThat(dtos.get(0).metrics).hasSize(metricDtos.size());
    assertThat(dtos.get(0).metrics.get(0)).isSameInstanceAs(metricDtos.get(0));
  }

  @Test
  public void givenMultipleData_whenAssemblingMany_thenAssembleMetrics() {
    List<ReportPeriodDataDto> dtos = reportPeriodDataAssembler.assembleMany(multipleData);

    assertThat(dtos.get(0).metrics).hasSize(metricDtos.size());
    assertThat(dtos.get(1).metrics).hasSize(otherMetricDtos.size());
    assertThat(dtos.get(0).metrics.get(0)).isSameInstanceAs(metricDtos.get(0));
    assertThat(dtos.get(1).metrics.get(0)).isSameInstanceAs(otherMetricDtos.get(0));
    assertThat(dtos.get(1).metrics.get(1)).isSameInstanceAs(otherMetricDtos.get(1));
  }
}
