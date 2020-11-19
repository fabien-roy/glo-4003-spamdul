package ca.ulaval.glo4003.reports.helpers;

import static ca.ulaval.glo4003.reports.helpers.ReportMetricMother.createReportMetricType;
import static ca.ulaval.glo4003.reports.helpers.ReportMetricMother.createReportMetricValue;

import ca.ulaval.glo4003.reports.domain.metrics.ReportMetricData;
import ca.ulaval.glo4003.reports.domain.metrics.ReportMetricType;

public class ReportMetricDataBuilder {

  private ReportMetricType reportMetricType = createReportMetricType();
  private double value = createReportMetricValue();

  public static ReportMetricDataBuilder aReportMetricData() {
    return new ReportMetricDataBuilder();
  }

  public ReportMetricDataBuilder withType(ReportMetricType reportMetricType) {
    this.reportMetricType = reportMetricType;
    return this;
  }

  public ReportMetricDataBuilder withValue(double value) {
    this.value = value;
    return this;
  }

  public ReportMetricData build() {
    return new ReportMetricData() {
      @Override
      public ReportMetricType getType() {
        return reportMetricType;
      }

      @Override
      public double getValue() {
        return value;
      }
    };
  }
}
