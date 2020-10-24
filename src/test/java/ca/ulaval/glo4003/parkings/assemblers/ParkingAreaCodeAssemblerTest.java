package ca.ulaval.glo4003.parkings.assemblers;

import static ca.ulaval.glo4003.parkings.helpers.ParkingAreaMother.createParkingAreaCode;
import static com.google.common.truth.Truth.assertThat;

import ca.ulaval.glo4003.parkings.domain.ParkingAreaCode;
import ca.ulaval.glo4003.parkings.exceptions.InvalidParkingAreaCodeException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ParkingAreaCodeAssemblerTest {
  private final ParkingAreaCode PARKING_AREA_CODE = createParkingAreaCode();

  private ParkingAreaCodeAssembler parkingAreaCodeAssembler;

  @Before
  public void setUp() {
    parkingAreaCodeAssembler = new ParkingAreaCodeAssembler();
  }

  @Test
  public void whenAssembling_thenReturnParkingAreaCode() {
    ParkingAreaCode parkingAreaCode =
        parkingAreaCodeAssembler.assemble(PARKING_AREA_CODE.toString());

    assertThat(parkingAreaCode).isEqualTo(PARKING_AREA_CODE);
  }

  @Test(expected = InvalidParkingAreaCodeException.class)
  public void givenNullParkingAreaCode_whenAssembling_thenThrowInvalidParkingAreaCodeException() {
    parkingAreaCodeAssembler.assemble(null);
  }
}
