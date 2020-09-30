package ca.ulaval.glo4003.parkings.api;

import ca.ulaval.glo4003.parkings.exceptions.*;
import com.google.common.truth.Truth;
import javax.ws.rs.core.Response;
import org.junit.Before;
import org.junit.Test;

public class ParkingExceptionMapperTest {

  private ParkingExceptionMapper parkingExceptionMapper;

  @Before
  public void setUp() {
    parkingExceptionMapper = new ParkingExceptionMapper();
  }

  @Test
  public void givenInvalidParkingAreaCodeException_whenResponding_thenStatusIsBadRequest() {
    ParkingException parkingException = new InvalidParkingAreaCodeException();

    Response response = parkingExceptionMapper.toResponse(parkingException);

    Truth.assertThat(response.getStatus()).isEqualTo(Response.Status.BAD_REQUEST.getStatusCode());
  }

  @Test
  public void givenInvalidReceptionMethodException_whenResponding_thenStatusIsBadRequest() {
    ParkingException parkingException = new InvalidReceptionMethodException();

    Response response = parkingExceptionMapper.toResponse(parkingException);

    Truth.assertThat(response.getStatus()).isEqualTo(Response.Status.BAD_REQUEST.getStatusCode());
  }

  @Test
  public void givenMissingPostalCodeException_whenResponding_thenStatusIsBadRequest() {
    ParkingException parkingException = new MissingPostalCodeException();

    Response response = parkingExceptionMapper.toResponse(parkingException);

    Truth.assertThat(response.getStatus()).isEqualTo(Response.Status.BAD_REQUEST.getStatusCode());
  }

  @Test
  public void givenMissingEmailException_whenResponding_thenStatusIsBadRequest() {
    ParkingException parkingException = new MissingEmailException();

    Response response = parkingExceptionMapper.toResponse(parkingException);

    Truth.assertThat(response.getStatus()).isEqualTo(Response.Status.BAD_REQUEST.getStatusCode());
  }

  @Test
  public void givenNotFoundParkingAreaException_whenResponding_thenStatusIsNotFound() {
    ParkingException parkingException = new NotFoundParkingAreaException();

    Response response = parkingExceptionMapper.toResponse(parkingException);

    Truth.assertThat(response.getStatus()).isEqualTo(Response.Status.NOT_FOUND.getStatusCode());
  }

  @Test
  public void givenNotFoundParkingStickerException_whenResponding_thenStatusIsNotFound() {
    ParkingException parkingException = new NotFoundParkingStickerException();

    Response response = parkingExceptionMapper.toResponse(parkingException);

    Truth.assertThat(response.getStatus()).isEqualTo(Response.Status.NOT_FOUND.getStatusCode());
  }
}
