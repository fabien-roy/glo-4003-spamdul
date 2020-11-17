package ca.ulaval.glo4003.reports;

import static com.google.common.truth.Truth.assertThat;

import ca.ulaval.glo4003.reports.api.ReportProfitResource;
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
  public void whenCreatingReportService_thenReturnIt() {
    ReportProfitService reportProfitService = reportInjector.createReportService();

    assertThat(reportProfitService).isNotNull();
  }

  @Test
  public void whenCreatingReportResource_thenReturnIt() {
    ReportProfitResource reportProfitResource = reportInjector.createReportResource();

    assertThat(reportProfitResource).isNotNull();
  }
}
