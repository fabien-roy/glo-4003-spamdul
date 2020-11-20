package ca.ulaval.glo4003.reports.api;

import static ca.ulaval.glo4003.reports.helpers.ReportPeriodDtoBuilder.aReportPeriodDto;
import static ca.ulaval.glo4003.reports.helpers.ReportScopeMother.createYear;
import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.when;

import ca.ulaval.glo4003.reports.api.dto.ReportPeriodDto;
import ca.ulaval.glo4003.reports.domain.ReportEventType;
import ca.ulaval.glo4003.reports.services.ReportProfitService;
import java.util.Collections;
import java.util.List;
import javax.ws.rs.core.Response;
import org.eclipse.jetty.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ReportProfitResourceImplementationTest {

  @Mock private ReportProfitService reportProfitService;

  private ReportProfitResource reportProfitResource;

  private final int year = createYear();
  private final ReportPeriodDto reportPeriodDtoForParkingStickerProfits =
      aReportPeriodDto().build();
  private final ReportPeriodDto reportPeriodDtoForAccessPassProfits = aReportPeriodDto().build();
  private final ReportPeriodDto reportPeriodDtoForAccessPassByConsumptionTypeProfits =
      aReportPeriodDto().build();
  private final ReportPeriodDto reportPeriodDtoForOffenseProfits = aReportPeriodDto().build();

  @Before
  public void setUp() {
    reportProfitResource = new ReportProfitResourceImplementation(reportProfitService);

    when(reportProfitService.getAllProfits(ReportEventType.BILL_PAID_FOR_PARKING_STICKER, year))
        .thenReturn(Collections.singletonList(reportPeriodDtoForParkingStickerProfits));
    when(reportProfitService.getAllProfits(ReportEventType.BILL_PAID_FOR_ACCESS_PASS, year, false))
        .thenReturn(Collections.singletonList(reportPeriodDtoForAccessPassProfits));
    when(reportProfitService.getAllProfits(ReportEventType.BILL_PAID_FOR_ACCESS_PASS, year, true))
        .thenReturn(
            Collections.singletonList(reportPeriodDtoForAccessPassByConsumptionTypeProfits));
    when(reportProfitService.getAllProfits(ReportEventType.BILL_PAID_FOR_OFFENSE, year))
        .thenReturn(Collections.singletonList(reportPeriodDtoForOffenseProfits));
  }

  @Test
  public void whenGettingParkingStickerProfit_thenRespondPeriods() {
    Response response = reportProfitResource.getParkingStickerProfits(year);
    List<ReportPeriodDto> respondedDtos = (List<ReportPeriodDto>) response.getEntity();

    assertThat(respondedDtos).hasSize(1);
    assertThat(respondedDtos.get(0)).isSameInstanceAs(reportPeriodDtoForParkingStickerProfits);
  }

  @Test
  public void whenGettingParkingStickerProfit_thenRespondOkStatus() {
    Response response = reportProfitResource.getParkingStickerProfits(year);
    int respondedStatus = response.getStatus();

    assertThat(respondedStatus).isEqualTo(HttpStatus.OK_200);
  }

  @Test
  public void givenNotByConsumptionType_whenGettingAccessPassProfit_thenRespondPeriods() {
    Response response = reportProfitResource.getAccessPassProfits(year, "false");
    List<ReportPeriodDto> respondedDtos = (List<ReportPeriodDto>) response.getEntity();

    assertThat(respondedDtos).hasSize(1);
    assertThat(respondedDtos.get(0)).isSameInstanceAs(reportPeriodDtoForAccessPassProfits);
  }

  @Test
  public void givenNotByConsumptionType_whenGettingAccessPassProfit_thenRespondOkStatus() {
    Response response = reportProfitResource.getAccessPassProfits(year, "false");
    int respondedStatus = response.getStatus();

    assertThat(respondedStatus).isEqualTo(HttpStatus.OK_200);
  }

  @Test
  public void givenByConsumptionType_whenGettingAccessPassProfit_thenRespondPeriods() {
    Response response = reportProfitResource.getAccessPassProfits(year, "true");
    List<ReportPeriodDto> respondedDtos = (List<ReportPeriodDto>) response.getEntity();

    assertThat(respondedDtos).hasSize(1);
    assertThat(respondedDtos.get(0))
        .isSameInstanceAs(reportPeriodDtoForAccessPassByConsumptionTypeProfits);
  }

  @Test
  public void givenByConsumptionType_whenGettingAccessPassProfit_thenRespondOkStatus() {
    Response response = reportProfitResource.getAccessPassProfits(year, "true");
    int respondedStatus = response.getStatus();

    assertThat(respondedStatus).isEqualTo(HttpStatus.OK_200);
  }

  @Test
  public void whenGettingOffenseProfit_thenRespondPeriods() {
    Response response = reportProfitResource.getOffenseProfits(year);
    List<ReportPeriodDto> respondedDtos = (List<ReportPeriodDto>) response.getEntity();

    assertThat(respondedDtos).hasSize(1);
    assertThat(respondedDtos.get(0)).isSameInstanceAs(reportPeriodDtoForOffenseProfits);
  }

  @Test
  public void whenGettingOffenseProfit_thenRespondOkStatus() {
    Response response = reportProfitResource.getOffenseProfits(year);
    int respondedStatus = response.getStatus();

    assertThat(respondedStatus).isEqualTo(HttpStatus.OK_200);
  }
}
