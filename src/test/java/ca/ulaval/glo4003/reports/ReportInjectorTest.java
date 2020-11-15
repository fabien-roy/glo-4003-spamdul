package ca.ulaval.glo4003.reports;

import static com.google.common.truth.Truth.assertThat;

import ca.ulaval.glo4003.reports.api.ReportResource;
import ca.ulaval.glo4003.reports.services.ReportService;
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
    ReportService reportService = reportInjector.createReportService();

    assertThat(reportService).isNotNull();
  }

  @Test
  public void whenCreatingReportResource_thenReturnIt() {
    ReportResource reportResource = reportInjector.createReportResource();

    assertThat(reportResource).isNotNull();
  }
}
