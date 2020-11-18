package ca.ulaval.glo4003.reports;

import static com.google.common.truth.Truth.assertThat;

import ca.ulaval.glo4003.reports.api.ReportParkingAreasResource;
import ca.ulaval.glo4003.reports.api.ReportProfitResource;
import ca.ulaval.glo4003.reports.services.ReportEventService;
import ca.ulaval.glo4003.reports.services.ReportParkingAreasService;
import ca.ulaval.glo4003.reports.services.ReportProfitService;
import org.junit.Before;
import org.junit.Test;

public class ReportInjectorTest {

  private ReportInjector reportInjector;

  @Before
  public void setUp() {
    reportInjector = new ReportInjector();
  }

  @Test
  public void whenCreatingReportProfitService_thenReturnIt() {
    ReportProfitService reportProfitService = reportInjector.createReportProfitService();

    assertThat(reportProfitService).isNotNull();
  }

  @Test
  public void whenCreatingReportProfitResource_thenReturnIt() {
    ReportProfitResource reportProfitResource = reportInjector.createReportProfitResource();

    assertThat(reportProfitResource).isNotNull();
  }

  @Test
  public void whenCreatingReportParkingAreasResource_thenReturnIt() {
    ReportParkingAreasResource reportParkingAreasResource =
        reportInjector.createReportParkingAreaResource();

    assertThat(reportParkingAreasResource).isNotNull();
  }

  @Test
  public void whenCreatingReportParkingAreasService_thenReturnIt() {
    ReportParkingAreasService reportParkingAreasService =
        reportInjector.createReportParkingAreaService();

    assertThat(reportParkingAreasService).isNotNull();
  }

  @Test
  public void whenCreatingReportEventService_thenReturnIt() {
    ReportEventService reportEventService = reportInjector.createReportEventService();

    assertThat(reportEventService).isNotNull();
  }
}
