package ca.ulaval.glo4003.reports.domain;

import ca.ulaval.glo4003.times.domain.CustomDateTime;
import java.util.ArrayList;
import java.util.List;

public class ReportPeriod {
  // TODO : #248
  // https://github.com/ExiledNarwal28/glo-2003-airbnb/blob/master/src/main/java/ca/ulaval/glo2003/reports/domain/ReportPeriod.java

  public List<ReportPeriodData> getData() {
    // TODO
    return new ArrayList<>();
  }

  public void setData(List<ReportPeriodData> data) {
    // TODO
  }

  public void setSingleData(List<ReportEvent> periodEvents) {
    // TODO
  }

  public boolean contains(CustomDateTime dateTime) {
    // TODO
    return false;
  }
}
