package ca.ulaval.glo4003.api.car;

import ca.ulaval.glo4003.domain.car.exceptions.CarException;
import ca.ulaval.glo4003.domain.car.exceptions.InvalidCarYearException;
import ca.ulaval.glo4003.domain.car.exceptions.InvalidLicensePlateException;
import com.github.javafaker.Faker;
import com.google.common.truth.Truth;
import javax.ws.rs.core.Response;
import org.junit.Before;
import org.junit.Test;

public class CarExceptionMapperTest {

  private static final int CAR_YEAR = Faker.instance().number().randomDigit();

  private CarExceptionMapper carExceptionMapper;

  @Before
  public void setUp() {
    carExceptionMapper = new CarExceptionMapper();
  }

  @Test
  public void givenInvalidCarYearException_whenResponding_thenStatusIsBadRequest() {
    CarException carException = new InvalidCarYearException(CAR_YEAR);

    Response response = carExceptionMapper.toResponse(carException);

    Truth.assertThat(response.getStatus()).isEqualTo(Response.Status.BAD_REQUEST.getStatusCode());
  }

  @Test
  public void givenInvalidLicensePlateException_whenResponding_thenStatusIsBadRequest() {
    CarException carException = new InvalidLicensePlateException();

    Response response = carExceptionMapper.toResponse(carException);

    Truth.assertThat(response.getStatus()).isEqualTo(Response.Status.BAD_REQUEST.getStatusCode());
  }
}
