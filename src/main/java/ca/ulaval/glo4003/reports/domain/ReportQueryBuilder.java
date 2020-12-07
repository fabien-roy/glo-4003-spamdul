package ca.ulaval.glo4003.reports.domain;

import ca.ulaval.glo4003.parkings.domain.ParkingAreaCode;
import ca.ulaval.glo4003.reports.domain.dimensions.ReportDimensionType;
import ca.ulaval.glo4003.reports.domain.metrics.ReportMetricType;
import ca.ulaval.glo4003.reports.domain.scopes.ReportScopeType;
import ca.ulaval.glo4003.times.domain.TimePeriod;
import java.util.List;

// TODO #326 : Delete this
public interface ReportQueryBuilder<Q extends ReportQuery> {

  ReportQueryBuilder<Q> aReportQuery();

  ReportQueryBuilder<Q> withReportEventType(ReportEventType reportEventType);

  ReportQueryBuilder<Q> withPeriod(TimePeriod period);

  ReportQueryBuilder<Q> withScope(ReportScopeType scopeType);

  ReportQueryBuilder<Q> withMetrics(List<ReportMetricType> metricTypes);

  ReportQueryBuilder<Q> withDimensions(List<ReportDimensionType> dimensionTypes);

  ReportQueryBuilder<Q> withParkingAreaCodes(List<ParkingAreaCode> parkingAreaCodes);

  Q build();
}
