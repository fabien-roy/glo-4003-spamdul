package ca.ulaval.glo4003.reports.helpers;

import static ca.ulaval.glo4003.reports.helpers.ReportPeriodMother.createReportPeriodName;
import static ca.ulaval.glo4003.times.helpers.TimePeriodBuilder.aTimePeriod;

import ca.ulaval.glo4003.reports.domain.ReportPeriod;
import ca.ulaval.glo4003.reports.domain.ReportPeriodData;
import ca.ulaval.glo4003.times.domain.TimePeriod;
import java.util.ArrayList;
import java.util.List;

public class ReportPeriodBuilder {
  private String name = createReportPeriodName();
  private TimePeriod period = aTimePeriod().build();
  private List<ReportPeriodData> data = new ArrayList();

  public static ReportPeriodBuilder aReportPeriod() {
    return new ReportPeriodBuilder();
  }

  public ReportPeriodBuilder withData(List<ReportPeriodData> data) {
    this.data = data;
    return this;
  }

  public ReportPeriod build() {
    ReportPeriod reportPeriod = new ReportPeriod(name, period);
    reportPeriod.setData(data);
    return reportPeriod;
  }
}
