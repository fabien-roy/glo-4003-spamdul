package ca.ulaval.glo4003.api.parking;

import static org.mockito.Matchers.eq;

import ca.ulaval.glo4003.api.parking.dto.ParkingStickerDto;
import ca.ulaval.glo4003.domain.parking.ParkingService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ParkingResourceImplementationTest {
  @Mock private ParkingService parkingService;
  @Mock private ParkingStickerDto parkingStickerDto;

  private ParkingResource parkingResource;

  @Before
  public void setUp() {
    parkingResource = new ParkingResourceImplementation(parkingService);
  }

  @Test
  public void whenAddParkingSticker_thenAddParkingStickerToService() {
    parkingResource.addParkingSticker(parkingStickerDto);

    Mockito.verify(parkingService).addParkingSticker(eq(parkingStickerDto));
  }
}
