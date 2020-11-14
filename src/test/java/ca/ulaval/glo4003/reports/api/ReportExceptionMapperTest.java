package ca.ulaval.glo4003.reports.api;

import static com.google.common.truth.Truth.assertThat;

import ca.ulaval.glo4003.reports.exceptions.InvalidReportScopeException;
import ca.ulaval.glo4003.reports.exceptions.ReportException;
import javax.ws.rs.core.Response;
import org.junit.Before;
import org.junit.Test;

public class ReportExceptionMapperTest {

  private ReportExceptionMapper reportExceptionMapper;

  @Before
  public void setUp() {
    reportExceptionMapper = new ReportExceptionMapper();
  }

  @Test
  public void givenInvalidReportScopeException_whenResponding_thenStatusIsBadRequest() {
    ReportException invalidReportScopeException = new InvalidReportScopeException();

    Response response = reportExceptionMapper.toResponse(invalidReportScopeException);

    assertThat(response.getStatus()).isEqualTo(Response.Status.BAD_REQUEST.getStatusCode());
  }
}
