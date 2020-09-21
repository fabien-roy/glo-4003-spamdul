package ca.ulaval.glo4003.domain.parking;

import static ca.ulaval.glo4003.domain.parking.helpers.ParkingStickerBuilder.aParkingSticker;
import static org.mockito.Matchers.eq;

import ca.ulaval.glo4003.api.parking.dto.ParkingStickerDto;
import ca.ulaval.glo4003.domain.account.AccountRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ParkingServiceTest {
  @Mock private ParkingStickerDto parkingStickerDto;
  @Mock private ParkingStickerAssembler parkingStickerAssembler;
  @Mock private AccountRepository accountRepository;
  @Mock private ParkingAreaRepository parkingAreaRepository;

  private ParkingSticker parkingSticker;

  private ParkingService parkingService;

  @Before
  public void setUp() {
    parkingService =
        new ParkingService(parkingStickerAssembler, accountRepository, parkingAreaRepository);
    parkingSticker = aParkingSticker().build();

    BDDMockito.given(parkingStickerAssembler.create(parkingStickerDto)).willReturn(parkingSticker);
  }

  @Test
  public void whenAddParkingSticker_thenGetAccount() {
    parkingService.addParkingSticker(parkingStickerDto);

    Mockito.verify(accountRepository).findById(eq(parkingSticker.getAccountId()));
  }

  @Test
  public void whenAddParkingSticker_thenGetParkingArea() {
    parkingService.addParkingSticker(parkingStickerDto);

    Mockito.verify(parkingAreaRepository).findByCode(eq(parkingSticker.getParkingAreaCode()));
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
