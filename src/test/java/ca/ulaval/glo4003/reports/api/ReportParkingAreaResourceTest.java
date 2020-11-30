package ca.ulaval.glo4003.reports.api;

import static ca.ulaval.glo4003.reports.helpers.ReportEventMother.createReportEventType;
import static ca.ulaval.glo4003.reports.helpers.ReportPeriodDtoBuilder.aReportPeriodDto;
import static ca.ulaval.glo4003.reports.helpers.ReportScopeMother.createYear;
import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.verify;

import ca.ulaval.glo4003.reports.services.ReportParkingAreaService;
import javax.ws.rs.core.Response;
import org.eclipse.jetty.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ReportParkingAreaResourceTest {

  private final int year = createYear();
  private final String reportPeriod = aReportPeriodDto().build().period;
  private final String reportEventType = createReportEventType().toString();
  @Mock private ReportParkingAreaService reportParkingAreaService;

  private ReportParkingAreaResource reportParkingAreaResourceImplementation;

  @Before
  public void setUp() {
    reportParkingAreaResourceImplementation =
        new ReportParkingAreaResource(reportParkingAreaService);
  }

  @Test
  public void whenGettingParkingAreas_thenGetParkingAreasReport() {
    reportParkingAreaResourceImplementation.getParkingAreas(reportEventType, reportPeriod);

    verify(reportParkingAreaService).getAllParkingAreaReports(reportEventType, reportPeriod);
  }

  @Test
  public void whenGettingParkingAreas_thenRespondOkStatus() {
    Response response =
        reportParkingAreaResourceImplementation.getParkingAreas(reportEventType, reportPeriod);
    int respondedStatus = response.getStatus();

    assertThat(respondedStatus).isEqualTo(HttpStatus.OK_200);
  }
}
