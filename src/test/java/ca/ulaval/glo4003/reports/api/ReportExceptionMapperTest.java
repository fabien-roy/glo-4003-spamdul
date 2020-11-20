package ca.ulaval.glo4003.reports.api;

import static com.google.common.truth.Truth.assertThat;

import ca.ulaval.glo4003.reports.exceptions.InvalidReportTypeException;
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
  public void givenInvalidReportTypeException_whenResponding_thenStatusIsBadRequest() {
    ReportException invalidReportTypeException = new InvalidReportTypeException();

    Response response = reportExceptionMapper.toResponse(invalidReportTypeException);

    assertThat(response.getStatus()).isEqualTo(Response.Status.BAD_REQUEST.getStatusCode());
  }
}
