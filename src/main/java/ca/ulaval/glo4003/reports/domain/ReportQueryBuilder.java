package ca.ulaval.glo4003.reports.domain;

import ca.ulaval.glo4003.reports.domain.dimensions.ReportDimensionType;
import ca.ulaval.glo4003.reports.domain.metrics.ReportMetricType;
import ca.ulaval.glo4003.reports.domain.scopes.ReportScopeType;
import java.util.List;

public interface ReportQueryBuilder<Q extends ReportQuery> {

  ReportQueryBuilder<Q> aReportQuery();

  ReportQueryBuilder<Q> withScope(ReportScopeType scope);

  ReportQueryBuilder<Q> withMetrics(List<ReportMetricType> metrics);

  ReportQueryBuilder<Q> withDimensions(List<ReportDimensionType> dimensions);

  Q build();
}
