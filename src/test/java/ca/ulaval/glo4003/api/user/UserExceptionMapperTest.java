package ca.ulaval.glo4003.api.user;

import static org.junit.Assert.*;

import ca.ulaval.glo4003.domain.user.exception.InvalidBirthDateException;
import ca.ulaval.glo4003.domain.user.exception.InvalidNameException;
import ca.ulaval.glo4003.domain.user.exception.InvalidSexException;
import ca.ulaval.glo4003.domain.user.exception.UserException;
import com.google.common.truth.Truth;
import javax.ws.rs.core.Response;
import org.junit.Before;
import org.junit.Test;

public class UserExceptionMapperTest {

  private UserExceptionMapper userExceptionMapper;

  @Before
  public void setUp() {
    userExceptionMapper = new UserExceptionMapper();
  }

  @Test
  public void givenInvalidBirthDateException_whenResponding_thenStatusIsBadRequest() {
    UserException userException = new InvalidBirthDateException();

    Response response = userExceptionMapper.toResponse(userException);

    Truth.assertThat(response.getStatus()).isEqualTo(Response.Status.BAD_REQUEST.getStatusCode());
  }

  @Test
  public void givenInvalidNameException_whenResponding_thenStatusIsBadRequest() {
    UserException userException = new InvalidNameException();

    Response response = userExceptionMapper.toResponse(userException);

    Truth.assertThat(response.getStatus()).isEqualTo(Response.Status.BAD_REQUEST.getStatusCode());
  }

  @Test
  public void givenInvalidSexException_whenResponding_thenStatusIsBadRequest() {
    UserException userException = new InvalidSexException();

    Response response = userExceptionMapper.toResponse(userException);

    Truth.assertThat(response.getStatus()).isEqualTo(Response.Status.BAD_REQUEST.getStatusCode());
  }
}