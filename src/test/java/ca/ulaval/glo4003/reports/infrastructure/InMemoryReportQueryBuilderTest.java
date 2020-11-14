package ca.ulaval.glo4003.reports.infrastructure;

import static ca.ulaval.glo4003.reports.helpers.ReportDimensionMother.createReportDimensionType;
import static ca.ulaval.glo4003.reports.helpers.ReportMetricMother.createReportMetricType;
import static ca.ulaval.glo4003.reports.helpers.ReportScopeMother.createReportScopeType;
import static ca.ulaval.glo4003.times.helpers.TimePeriodBuilder.aTimePeriod;
import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.when;

import ca.ulaval.glo4003.reports.domain.ReportQueryBuilder;
import ca.ulaval.glo4003.reports.domain.dimensions.ReportDimension;
import ca.ulaval.glo4003.reports.domain.dimensions.ReportDimensionBuilder;
import ca.ulaval.glo4003.reports.domain.dimensions.ReportDimensionType;
import ca.ulaval.glo4003.reports.domain.metrics.ReportMetric;
import ca.ulaval.glo4003.reports.domain.metrics.ReportMetricBuilder;
import ca.ulaval.glo4003.reports.domain.metrics.ReportMetricType;
import ca.ulaval.glo4003.reports.domain.scopes.ReportScope;
import ca.ulaval.glo4003.reports.domain.scopes.ReportScopeBuilder;
import ca.ulaval.glo4003.reports.domain.scopes.ReportScopeType;
import ca.ulaval.glo4003.times.domain.TimePeriod;
import java.util.Collections;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class InMemoryReportQueryBuilderTest {

  @Mock private ReportScopeBuilder scopeBuilder;
  @Mock private ReportMetricBuilder metricBuilder;
  @Mock private ReportDimensionBuilder dimensionBuilder;
  @Mock private ReportScope scope;
  @Mock private ReportMetric metric;
  @Mock private ReportDimension dimension;

  private ReportQueryBuilder<InMemoryReportQuery> reportQueryBuilder;

  private final TimePeriod period = aTimePeriod().build();
  private final ReportScopeType scopeType = createReportScopeType();
  private final ReportMetricType metricType = createReportMetricType();
  private final List<ReportMetricType> metricTypes = Collections.singletonList(metricType);
  private final ReportDimensionType dimensionType = createReportDimensionType();
  private final List<ReportDimensionType> dimensionTypes = Collections.singletonList(dimensionType);

  @Before
  public void setUp() {
    when(scopeBuilder.aReportScope()).thenReturn(scopeBuilder);
    when(scopeBuilder.withPeriod(period)).thenReturn(scopeBuilder);
    when(scopeBuilder.withType(scopeType)).thenReturn(scopeBuilder);
    when(scopeBuilder.build()).thenReturn(scope);

    List<ReportMetric> metrics = Collections.singletonList(metric);
    when(metricBuilder.someMetrics()).thenReturn(metricBuilder);
    when(metricBuilder.withTypes(metricTypes)).thenReturn(metricBuilder);
    when(metricBuilder.buildMany()).thenReturn(metrics);

    List<ReportDimension> dimensions = Collections.singletonList(dimension);
    when(dimensionBuilder.someDimensions()).thenReturn(dimensionBuilder);
    when(dimensionBuilder.withTypes(dimensionTypes)).thenReturn(dimensionBuilder);
    when(dimensionBuilder.buildMany()).thenReturn(dimensions);

    when(dimensionBuilder.someDimensions()).thenReturn(dimensionBuilder);

    reportQueryBuilder =
        new InMemoryReportQueryBuilder(scopeBuilder, metricBuilder, dimensionBuilder);
  }

  @Test
  public void givenPeriodAndScope_whenBuilding_thenReturnReportQueryWithScope() {
    InMemoryReportQuery reportQuery = buildReportQuery();

    assertThat(reportQuery.getScope()).isSameInstanceAs(scope);
  }

  @Test
  public void givenMetricTypes_whenBuilding_thenReturnReportQueryWithMetrics() {
    InMemoryReportQuery reportQuery = buildReportQuery();

    assertThat(reportQuery.getMetrics()).hasSize(1);
    assertThat(reportQuery.getMetrics().get(0)).isSameInstanceAs(metric);
  }

  @Test
  public void givenDimensionTypes_whenBuilding_thenReturnReportQueryWithDimensions() {
    InMemoryReportQuery reportQuery = buildReportQuery();

    assertThat(reportQuery.getDimensions()).hasSize(1);
    assertThat(reportQuery.getDimensions().get(0)).isSameInstanceAs(dimension);
  }

  private InMemoryReportQuery buildReportQuery() {
    return reportQueryBuilder
        .aReportQuery()
        .withPeriod(period)
        .withScope(scopeType)
        .withMetrics(metricTypes)
        .withDimensions(dimensionTypes)
        .build();
  }
}
