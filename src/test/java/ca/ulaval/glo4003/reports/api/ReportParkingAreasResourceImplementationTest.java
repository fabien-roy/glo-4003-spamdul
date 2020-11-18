package ca.ulaval.glo4003.reports.api;

import static ca.ulaval.glo4003.reports.helpers.ReportEventMother.createReportEventType;
import static ca.ulaval.glo4003.reports.helpers.ReportPeriodDtoBuilder.aReportPeriodDto;
import static ca.ulaval.glo4003.reports.helpers.ReportScopeMother.createYear;
import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.verify;

import ca.ulaval.glo4003.reports.services.ReportParkingAreasService;
import javax.ws.rs.core.Response;
import org.eclipse.jetty.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ReportParkingAreasResourceImplementationTest {

  private final int year = createYear();
  private final String reportPeriod = aReportPeriodDto().build().period;
  private final String reportEventType = createReportEventType().toString();
  @Mock private ReportParkingAreasService reportParkingAreasService;

  private ReportParkingAreasResource reportParkingAreasResourceImplementation;

  @Before
  public void setUp() {
    reportParkingAreasResourceImplementation =
        new ReportParkingAreasResourceImplementation(reportParkingAreasService);
  }

  @Test
  public void whenGettingParkingAreas_thenGetParkingAreasReport() {
    reportParkingAreasResourceImplementation.getParkingAreas(reportEventType, reportPeriod);

    verify(reportParkingAreasService).getReports(reportEventType, reportPeriod);
  }

  @Test
  public void whenGettingParkingAreas_thenRespondOkStatus() {
    Response response =
        reportParkingAreasResourceImplementation.getParkingAreas(reportEventType, reportPeriod);
    int respondedStatus = response.getStatus();

    assertThat(respondedStatus).isEqualTo(HttpStatus.OK_200);
  }
}
