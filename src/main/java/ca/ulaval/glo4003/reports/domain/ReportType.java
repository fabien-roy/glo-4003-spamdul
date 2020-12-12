package ca.ulaval.glo4003.reports.domain;

import ca.ulaval.glo4003.reports.domain.exceptions.InvalidReportTypeException;
import java.util.HashMap;
import java.util.Map;

public enum ReportType {
  DAY_OF_MONTH("dayOfMonth"),
  MONTHLY("monthly"),
  SUMMARY("summary");

  String reportType;
  private static final Map<String, ReportType> lookup = new HashMap<>();

  static {
    for (ReportType reportTypeName : ReportType.values()) {
      lookup.put(reportTypeName.toString().toLowerCase(), reportTypeName);
    }
  }

  ReportType(String reportType) {
    this.reportType = reportType;
  }

  public static ReportType get(String reportType) {
    if (reportType == null) throw new InvalidReportTypeException();

    ReportType foundReportType = lookup.get(reportType.toLowerCase());

    if (foundReportType == null) throw new InvalidReportTypeException();

    return foundReportType;
  }

  @Override
  public String toString() {
    return reportType;
  }
}
