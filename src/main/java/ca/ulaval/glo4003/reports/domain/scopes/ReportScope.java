package ca.ulaval.glo4003.reports.domain.scopes;

import ca.ulaval.glo4003.reports.domain.ReportPeriod;
import java.util.ArrayList;
import java.util.List;

public abstract class ReportScope {
  // TODO : #248
  // https://github.com/ExiledNarwal28/glo-2003-airbnb/blob/master/src/main/java/ca/ulaval/glo2003/reports/domain/scopes/ReportScope.java

  public List<ReportPeriod> getReportPeriods() {
    // TODO
    return new ArrayList<>();
  }
}
