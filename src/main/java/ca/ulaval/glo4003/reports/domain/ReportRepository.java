package ca.ulaval.glo4003.reports.domain;

import java.util.List;

public interface ReportRepository<Q extends ReportQuery> {

  void addEvent(ReportEvent event);

  List<ReportPeriod> getPeriods(Q query);
}
