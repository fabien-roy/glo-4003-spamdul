package ca.ulaval.glo4003.reports.infrastructure;

import ca.ulaval.glo4003.parkings.domain.ParkingAreaCode;
import ca.ulaval.glo4003.reports.domain.ReportEventType;
import ca.ulaval.glo4003.reports.domain.ReportQueryFactory;
import ca.ulaval.glo4003.reports.domain.ReportType;
import java.util.List;

public class ReportQueryFactoryInMemory implements ReportQueryFactory<ReportQueryInMemory> {
  @Override
  public ReportQueryInMemory createGateEntriesReportQuery(
      ReportType reportType, String month, List<ParkingAreaCode> parkingAreaCodes) {
    // TODO #326
    return null;
  }

  @Override
  public ReportQueryInMemory createProfitsReportQuery(
      ReportEventType reportEventType, int year, boolean isByConsumptionType) {
    // TODO #326
    return null;
  }
}
