package ca.ulaval.glo4003.domain.parking;

import ca.ulaval.glo4003.api.parking.dto.ParkingStickerDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ParkingServiceTest {
  @Mock private ParkingStickerDto parkingStickerDto;
  @Mock private ParkingStickerAssembler parkingStickerAssembler;

  private ParkingService parkingService;

  @Before
  public void setUp() {
    parkingService = new ParkingService();
  }

  @Test
  public void whenAddParkingSticker_thenGetAccount() {
    // TODO
  }

  @Test
  public void whenAddParkingSticker_thenGetParkingArea() {
    // TODO
  }

  @Test
  public void whenAddParkingSticker_thenCreateParkingSticker() {
    // TODO
  }

  @Test
  public void whenAddParkingSticker_thenAddParkingStickerToAccount() {
    // TODO
  }

  @Test
  public void whenAddParkingSticker_thenSaveAccount() {
    // TODO
  }
}
