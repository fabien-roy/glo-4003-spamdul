package ca.ulaval.glo4003.reports.domain;

import ca.ulaval.glo4003.times.domain.CustomDateTime;
import ca.ulaval.glo4003.times.domain.TimePeriod;
import java.util.Collections;
import java.util.List;

public class ReportPeriod {
  private final String name;
  private final TimePeriod period;
  private List<ReportPeriodData> data;

  public ReportPeriod(String name, TimePeriod period) {
    this.name = name;
    this.period = period;
  }

  public String getName() {
    return name;
  }

  public TimePeriod getPeriod() {
    return period;
  }

  public List<ReportPeriodData> getData() {
    return data;
  }

  public void setData(List<ReportPeriodData> data) {
    this.data = data;
  }

  public void setSingleData(List<ReportEvent> events) {
    ReportPeriodData singleData = new ReportPeriodData(events);
    this.data = Collections.singletonList(singleData);
  }

  public boolean contains(CustomDateTime dateTime) {
    return period.contains(dateTime);
  }
}
