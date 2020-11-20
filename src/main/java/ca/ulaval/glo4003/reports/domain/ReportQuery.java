package ca.ulaval.glo4003.reports.domain;

import java.util.List;

public interface ReportQuery {

  List<ReportPeriod> execute();
}
